package com.util;

import com.util.ConnectionPool;
import java.sql.*;

public class Calculate{
    ConnectionPool pool = ConnectionPool.getInstance();
    Connection conn;
    Statement stmt;
    PreparedStatement ptmt = null;
    ResultSet rs;

    float[] a = new float[16];
    float[] b = new float[16];
    float[] c = new float[16];
    float[] tWeb = new float[16];
    float[] iTotal = new float[16];
    float[] s = new float[16];
    float[] w = new float[16];
    /*float areaFl1 = new float[1];
    float[] areaFl2 = new float[1];
    float[] areaWeb = new float[1];
    float[] totalArea = new float[1];*/
    int i = 0;
    float areaFl1, areaFl2, areaWeb, totalArea, dWeb, IoxFl, IoxWeb, IoxTotal ,d, totalAreaFl, c1, wFl, wWeb;

    public static void main(String args[]){
        new Calculate();
    }

    public Calculate(){
        getData();
        putData();
    }

    void getData(){
        try{
            conn = pool.getConnection();
            stmt = conn.createStatement();//ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery("select * from tjiwoodflange");
            while (rs.next()) {
                tWeb[i] = rs.getFloat(5);
                c[i] = rs.getFloat(6);
                a[i] = rs.getFloat(7);
                b[i] = rs.getFloat(8);
                calc();
                //System.out.println("TotalArea:"+ totalArea);
                i++;
                //rs.updateFloat(3,totalArea); // updates the NAME column of row 5 to be AINSWORTH
                //rs.updateRow();
            }
            rs.close();
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                pool.setConnection(conn);
            }catch(Exception e){
                System.out.println("during setConnection:");
                e.printStackTrace();
            }
        }
    }
    void putData(){
        try{
            conn = pool.getConnection();
            System.out.println(a.length);
            for (int i1=0; i1<a.length; i1++) {
                String query = "update tjiwoodflange set S="+s[i1]+" , I="+iTotal[i1]+", W="+w[i1]+" where depth ="+c[i1]+" and a ="+a[i1]+" and b="+b[i1];
                Statement _stmt = conn.createStatement();
                _stmt.executeUpdate(query);
                conn.commit();
                _stmt.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                pool.setConnection(conn);
            }catch(Exception e){
                System.out.println("during setConnection:");
                e.printStackTrace();
            }
        }
    }
    /*void putData1(){
        try{
            conn = pool.getConnection();
            String query = "update tjiwoodflange set S=? , I=?, W=? where depth =? and a =? and b=?";
            ptmt = conn.prepareStatement(query);
            System.out.println(a.length);
            for (int i1=0; i1<a.length; i1++) {
                ptmt.setFloat(1, s[i1]);
                ptmt.setFloat(2, iTotal[i1]);
                ptmt.setFloat(3, w[i1]);
                ptmt.setFloat(4, c[i1]);
                ptmt.setFloat(5, a[i1]);
                ptmt.setFloat(6, b[i1]);
                ptmt.addBatch();
                System.out.println("row added " + i1);
                //ptmt.executeUpdate();
                //conn.commit();
                //ptmt.clearParameters();
            }
            int _rows[] = ptmt.executeBatch();
            System.out.println("Rows Enter into WiShape table:" + _rows.length);
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                pool.setConnection(conn);
            }catch(Exception e){
                System.out.println("during setConnection:");
                e.printStackTrace();
            }
        }
    } */

    /*void parse(String pDesc){
        a = Integer.parseInt(pDesc.substring(0,1));
        b = Integer.parseInt(pDesc.substring(2,1));
    }*/

    void calc(){
        areaFl1 = a[i] * b[i];
        System.out.println("areaFl1:"+areaFl1);
        areaFl2 = a[i] * b[i];
        System.out.println("areaFl2:"+areaFl2);
        areaWeb = (c[i] - (2*a[i])) * tWeb[i];
        System.out.println("areaWeb:"+areaWeb);
        totalArea = areaFl1 + areaFl2 + areaWeb;
        System.out.println("totalArea:"+ totalArea);
        IoxFl = 2*b[i]*a[i]*a[i]*a[i]/12;
        System.out.println("IoxFl:"+ IoxFl);
        dWeb = c[i] - (2 * a[i]);
        System.out.println("dWeb:"+ dWeb);
        IoxWeb = tWeb[i] * dWeb * dWeb * dWeb / 12;
        System.out.println("IoxWeb:"+ IoxWeb);
        IoxTotal = IoxFl + IoxWeb;
        System.out.println("IoxTotal:"+ IoxTotal);
        d = (c[i] - a[i]) / 2;
        System.out.println("d:"+ d);
        totalAreaFl = 2*a[i]*b[i]*d*d;
        System.out.println("dtotalAreaFl:"+ totalAreaFl);
        iTotal[i] = IoxTotal + totalAreaFl;
        System.out.println("iTotal:"+ iTotal[i]);
        c1 = c[i] / 2;
        System.out.println("c1:"+ c1);
        s[i] = iTotal[i] / c1;
        System.out.println("s[i]:"+ s[i]);
        wFl = ((areaFl1 + areaFl2) / 144) * 36;
        System.out.println("wFl:"+ wFl);
        wWeb = areaWeb / 144 * 42;
        System.out.println("wWeb:"+ wWeb);
        w[i] = wFl + wWeb;
        System.out.println("w[i]:"+ w[i]);
    }
}

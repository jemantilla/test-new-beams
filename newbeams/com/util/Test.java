package com.util;

import java.sql.*;

public class Test{
    static String param;
    public static void main(String [] args){
        param = args[0];
        new Test();
    }

    public Test(){
        System.out.println("valid Str :" + Utility.checkValidChar(param));
        //testQry();
    }
    void testQry(){
        Connection conn = null;
        try{
            Object [] _args = new Object[1];
            /*_args [0] = "'rajiv'";
            _args[1] = "'mehar'";*/
            String _qryStr = "update profile set FirstName= 'First" + 
                "', LastName= 'Last', Email= 'NEmail()"+
                "', Address1= 'Address1()', City= 'City()" +
                "', State= 'State()', Zip= 'Zip()" +
                "', WorkPhoneArea= 'Are', WorkPhoneNum= 'WorkNum" +
                "', CellPhoneArea= 'Cel', CellPhoneNum= 'CellNum' where username = 'User'";
            _args[0] = "'First" + "','" +
            "Last" + "','" +
            "Email()" + "','" +
            "Address1()" + "','" +
            "City()" + "','" +
            "State()" + "','" +
            "Zip()" + "','" +
            "Wor" + "','" +
            "WorkNum()" + "','" +
            "Cel" + "','" +
            "CellNum()'";
           // String _qryStr = Messages.getMsgStr(Configuration.UPADATE_SQL , _args);
            _qryStr = "select userName, password from profile where email = 'shah_ravi@hotmail.com'";
                System.out.println("_qryStr:" + _qryStr);
                //SQLHandler sqlHandler = new SQLHandler();
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                String url = "jdbc:odbc:newbeams";
                conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement();
                //stmt.executeUpdate(_qryStr);
                ResultSet rs = stmt.executeQuery(_qryStr);
                while (rs.next()) {
                    System.out.println(rs.getString("userName"));
                    System.out.println(rs.getString("password"));
                }
                System.out.println("_qryStr:" + _qryStr);
                conn.commit();
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                try{
                    conn.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
    }
}

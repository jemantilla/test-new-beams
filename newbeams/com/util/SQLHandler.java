package com.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.HashMap;

public class SQLHandler extends GenericService{

    String query;
    ConnectionPool pool = ConnectionPool.getInstance();
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement ptmt = null;
    ArrayList rowArray = new ArrayList();
    Vector projectDetail = new Vector();
    HashMap projects = new HashMap();
    ResultSet rs = null;
    
    public boolean login(Profile pProfile) throws SQLException{
        boolean _found = false;
        try{
            conn = pool.getConnection();
            stmt = conn.createStatement();
            String _qry ="select * from Profile where UserName='"+pProfile.getUserName()+"' and PassWord='"+pProfile.getPassWord()+"'";
            setLogDebug("Query :"+ _qry);
            rs = stmt.executeQuery(_qry);
            System.out.println(" 1");
            while (rs.next()) {
                pProfile.setFirstName(rs.getString(3));
                pProfile.setLastName(rs.getString(4));
                pProfile.setEmail(rs.getString(5));
                pProfile.setAddress1(rs.getString(6));
                pProfile.setCity(rs.getString(7));
                pProfile.setState(rs.getString(8));
                pProfile.setZip(rs.getString(9));
                pProfile.setWorkPhoneNum(rs.getString(10));
                pProfile.setCellPhoneNum(rs.getString(11));
                pProfile.setValidUser(true);
                _found = true;
            }
            setLogDebug("profile found");
        }catch (SQLException e){
            throw  e;
        }finally{
            try{
                rs.close();
                stmt.close();
                pool.setConnection(conn);
            }catch (SQLException e1){
                throw e1;
            }
        }
        return _found;
    }
    
    public HashMap getProjects(String pUserName) throws SQLException{
        projects.clear();
        try{
            conn = pool.getConnection();
            stmt = conn.createStatement();
            query ="select * from Project where UserName='"+pUserName+"'";
            setLogDebug("Query :"+ query);
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Project _project = new Project();
                _project.setProjectName(rs.getString(2));
                _project.setStreet(rs.getString(3));
                _project.setCity(rs.getString(4));
                _project.setState(rs.getString(5));
                _project.setZip(rs.getString(6));
                projects.put(_project.getProjectName() , _project);
            }
        }catch (SQLException e){
            throw  e;
        }finally{
            try{
                rs.close();
                stmt.close();
                pool.setConnection(conn);
            }catch (SQLException e1){
                throw e1;
            }
        }
        return projects;
    }
    // Retrive all beams detail for pUserName and pProjectName
    public Vector getProjectDetail(String pUserName, String pProjectName) throws SQLException{
        projectDetail.clear();
        try{
            conn = pool.getConnection();
            stmt = conn.createStatement();
            query ="select * from ProjectDetail where UserName='"+pUserName+"' and ProjectName='"+pProjectName+"'" ;
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                ProjectDetail _projectDetail = new ProjectDetail();
                _projectDetail.setProjectName(pProjectName);
                _projectDetail.setStatus(rs.getString(3));
                _projectDetail.setBeamSize(rs.getString(4));
                _projectDetail.setSpan(rs.getDouble(5));
                _projectDetail.setDate(rs.getString(6));
                _projectDetail.setLoad(rs.getDouble(7));
                _projectDetail.setDeflection(rs.getDouble(8));
                _projectDetail.setMaterial(rs.getString(9));
                _projectDetail.setTension(rs.getFloat(10));
                _projectDetail.setCSArea(rs.getFloat(11));
                _projectDetail.setRafterSize(rs.getString(12));
                projectDetail.addElement(_projectDetail);
            }
            setLogDebug("Query is:" + query + "-Rows Found from ProjectDetail table:" + projectDetail.size());
        }catch (SQLException e){
            throw  e;
        }finally{
            try{
                rs.close();
                stmt.close();
                pool.setConnection(conn);
            }catch (SQLException e1){
                throw e1;
            }
        }
        return projectDetail;
    }
    
    public int insertProject(Project pProject , String pUserName) throws SQLException{
        int inserted = 0;
        try{
            conn = pool.getConnection();
            // Add Project in Project Table
            query = "insert into Project values(?,?,?,?,?,?)";
            ptmt = conn.prepareStatement(query);
            ptmt.setString(1, pUserName);
            ptmt.setString(2, pProject.getProjectName());
            ptmt.setString(3, pProject.getStreet());
            ptmt.setString(4, pProject.getCity());
            ptmt.setString(5, pProject.getState());
            ptmt.setString(6, pProject.getZip());
            inserted = ptmt.executeUpdate();
            setLogDebug("Query is:" + query + "-Rows Enter into Project table:" + inserted);
            conn.commit();
        }catch (SQLException e){
            try{
                conn.rollback();
            }catch(SQLException e1){
                if (loggingError) {
                    setLogError("during rollback from insertProject");
                    e1.printStackTrace();
                }
            }
            throw  e;
        }finally{
            try{
                ptmt.close();
                pool.setConnection(conn);
            }catch (SQLException e1){
                throw e1;
            }
        }
        return inserted;
    }
    
    public int updateProject(Project pProject, String pUserName) throws SQLException{
        int updated = 0;
        try{
            conn = pool.getConnection();
            // update Project in Project Table
            query = "update project set UserName='"+pUserName+"', Name='"+pProject.getProjectName()+
                "', Address1='"+pProject.getStreet()+"', city='"+pProject.getCity()+"', State='"+pProject.getState()+
                "', Zip='"+pProject.getZip()+"' where UserName='"+pUserName+"' and name='"+pProject.getPreviousName()+"'";
            stmt = conn.createStatement();
            setLogDebug("Query :"+ query);
            updated = stmt.executeUpdate(query);
            setLogDebug("Query is:" + query);
            setLogDebug("Rows Enter updated Project table:" + updated);
            //update projectName in projectDetail table
            query = "update projectDetail set UserName='"+pUserName+"', ProjectName='"+pProject.getProjectName()+
                "' where UserName='"+pUserName+"' and ProjectName='"+pProject.getPreviousName()+"'";
            int updatedDetailRow = stmt.executeUpdate(query);
            conn.commit();
            setLogDebug("Query is:" + query);
            setLogDebug("Rows Enter updated ProjectDetail table:" + updatedDetailRow);
        }catch (SQLException e){
            try{
                conn.rollback();
            }catch(SQLException e1){
                if (loggingError) {
                    setLogError("during rollback from updateProject");
                    e1.printStackTrace();
                }
            }
            throw  e;
        }finally{
            try{
                stmt.close();
                pool.setConnection(conn);
            }catch (SQLException e1){
                throw e1;
            }
        }
        return updated;
    }
    
    public int insertBeam(Design pDesign, String pProjectName, String pUserName, String pDescription, double pDeflection) throws SQLException{
        int inserted = 0;
        try{
            conn = pool.getConnection();
            // Add Project in ProjectDetail Table
            java.util.Date _now = new java.util.Date();
            query = "insert into ProjectDetail (UserName, ProjectName, Status, BeamSize, span, "+
                                                "BeamDate, BeamLoad, Deflection, Material) values(?,?,?,?,?,?,?,?,?)";
            ptmt = conn.prepareStatement(query);
            ptmt.setString(1, pUserName);
            ptmt.setString(2, pProjectName);
            ptmt.setString(3, pDesign.getStatus());
            ptmt.setString(4, pDescription);
            ptmt.setDouble(5, pDesign.getSpan());
            ptmt.setString(6, Utility.format(_now));
            ptmt.setDouble(7, Double.parseDouble(pDesign.getLoad()));
            ptmt.setDouble(8, pDeflection);
            ptmt.setString(9, HashValues.getMaterialValue(pDesign.getMaterial()));
            inserted = ptmt.executeUpdate();
            setLogDebug("Query is:" + query + "-Rows Enter into ProjectDetail table:" + inserted);
            conn.commit();
        }catch (SQLException e){
            try{
                conn.rollback();
            }catch(SQLException e1){
                if (loggingError) {
                    setLogError("during rollback from insertBeam");
                    e1.printStackTrace();
                }
            }
            throw  e;
        }finally{
            try{
                ptmt.close();
                pool.setConnection(conn);
            }catch (SQLException e1){
                throw e1;
            }
        }
        return inserted;
    }
    
    public int insertAFrame(Design pDesign, String pProjectName, String pUserName, String pDescription) throws SQLException{
        int inserted = 0;
        try{
            conn = pool.getConnection();
            // Add Project in ProjectDetail Table
            java.util.Date _now = new java.util.Date();
            query = "insert into ProjectDetail (UserName, ProjectName, Status, BeamDate, Material, Tension, "+
                                                "CSArea, RafterSize) values(?,?,?,?,?,?,?,?)";
            ptmt = conn.prepareStatement(query);
            ptmt.setString(1, pUserName);
            ptmt.setString(2, pProjectName);
            ptmt.setString(3, pDesign.getStatus());
            ptmt.setString(4, Utility.format(_now));
            ptmt.setString(5, HashValues.getMaterialValue(pDesign.getMaterial()));
            ptmt.setDouble(6, pDesign.getT());
            ptmt.setDouble(7, pDesign.getArea());
            ptmt.setString(8, pDescription);
            inserted = ptmt.executeUpdate();
            setLogDebug("Query is:" + query + "-Rows Enter into ProjectDetail table:" + inserted);
            conn.commit();
        }catch (SQLException e){
            try{
                conn.rollback();
            }catch(SQLException e1){
                if (loggingError) {
                    setLogError("during rollback from insertBeamForAFrame");
                    e1.printStackTrace();
                }
            }
            throw  e;
        }finally{
            try{
                ptmt.close();
                pool.setConnection(conn);
            }catch (SQLException e1){
                throw e1;
            }
        }
        return inserted;
    }

    public void getLastProject(String pUserName , Project pProject) throws SQLException{
        rowArray.clear();
        try{
            conn = pool.getConnection();
            stmt = conn.createStatement();
            query = "select Name, Address1, City, State, Zip from Project p , ProjectDetail "+
                "where Name =  ProjectName and p.UserName = '"+pUserName+"' and  BeamDate = "+
                "(select  max(BeamDate) from ProjectDetail where UserName = '"+pUserName+"')";
            setLogDebug("from getLastProject :"+ query);
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                pProject.setProjectName(rs.getString(1));
                pProject.setStreet(rs.getString(2));
                pProject.setCity(rs.getString(3));
                pProject.setState(rs.getString(4));
                pProject.setZip(rs.getString(5));
            }
        }catch (SQLException e){
            throw  e;
        }finally{
            try{
                rs.close();
                stmt.close();
                pool.setConnection(conn);
            }catch (SQLException e1){
                throw e1;
            }
        }
    }

    public ArrayList getValuesByDis(String pQryStr) throws SQLException{
        rowArray.clear();
        try{
            conn = pool.getConnection();
            stmt = conn.createStatement();
            setLogDebug("from getValuesByDis :"+ pQryStr);
            rs = stmt.executeQuery(pQryStr);
            int _rowCount = 0;
            while (rs.next()) {
                //setLogDebug("in rs");
                String [] _colArray = new String[4];
                for (int i=0; i<4; i++) {
                    _colArray[i] = rs.getString(i+1);
                    //setLogDebug("i"+i);
                }
                rowArray.add(_rowCount,_colArray);
                _rowCount++;
            }
        }catch (SQLException e){
            throw  e;
        }finally{
            try{
                rs.close();
                stmt.close();
                pool.setConnection(conn);
            }catch (SQLException e1){
                throw e1;
            }
        }
        return rowArray;
    }

    public ArrayList executeQuery(String pQryStr, int pColumns) throws SQLException{
        ArrayList _rowArray = new ArrayList();
        try{
            conn = pool.getConnection();
            stmt = conn.createStatement();
            setLogDebug("Query :"+ pQryStr);
            rs = stmt.executeQuery(pQryStr);
            int _rowCount = 0;
            while (rs.next()) {
                setLogDebug("in rs");
                String [] _colArray = new String[pColumns];
                for (int i=0; i<pColumns; i++) {
                    _colArray[i] = rs.getString(i+1);
                    //setLogDebug("i"+i);
                }
                _rowArray.add(_rowCount,_colArray);
                _rowCount++;
            }
        }catch (SQLException e){
            throw  e;
        }finally{
            try{
                rs.close();
                stmt.close();
                pool.setConnection(conn);
            }catch (SQLException e1){
                throw e1;
            }
        }
        return _rowArray;
    }

    public ArrayList executeQuery(String pQryStr, int pColumns, int pRow) throws SQLException{
        rowArray.clear();
        try{
            conn = pool.getConnection();
            stmt = conn.createStatement();
            setLogDebug("Query :"+ pQryStr);
            rs = stmt.executeQuery(pQryStr);
            int _rowCount = 0;
            while (rs.next()) {
                String [] _colArray = new String[pColumns];
                for (int i=0; i<pColumns; i++) {
                    _colArray[i] = rs.getString(i+1);
                }
                rowArray.add(_rowCount,_colArray);
                _rowCount++;
                if (_rowCount >= pRow) {
                    break;
                }
            }
            rs.close();
            stmt.close();
        }catch (SQLException e){
            throw  e;
        }finally{
            if (conn != null) {
                pool.setConnection(conn);
            }
        }
        return rowArray;
    }

    public String[] executeQueryRow(String pQryStr, int pColumns) throws SQLException{
        String [] _colArray = null;
        try{
            conn = pool.getConnection();
            stmt = conn.createStatement();
            setLogDebug("Query :"+ pQryStr);
            rs = stmt.executeQuery(pQryStr);
            while (rs.next()) {
                _colArray = new String[pColumns];
                for (int i=0; i<pColumns; i++) {
                    _colArray[i] = rs.getString(i+1);
                }
            }
        }catch (SQLException e){
            throw  e;
        }finally{
            try{
                rs.close();
                stmt.close();
                pool.setConnection(conn);
            }catch (SQLException e1){
                throw e1;
            }
        }
        return _colArray;
    }

    public int executeUpdate(String pQryStr) throws SQLException{
        int _rowCount = 0;
        try{
            conn = pool.getConnection();
            stmt = conn.createStatement();
            setLogDebug("Query :"+ pQryStr);
            _rowCount = stmt.executeUpdate(pQryStr);
            conn.commit();
        }catch (SQLException e){
            throw  e;
        }finally{
            try{
                stmt.close();
                pool.setConnection(conn);
            }catch (SQLException e1){
                throw e1;
            }
        }
        return _rowCount;
    }
}


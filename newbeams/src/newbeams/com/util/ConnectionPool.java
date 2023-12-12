package com.util;

import java.sql.*;
import java.util.*;

public class ConnectionPool {
  private static Hashtable connections = new Hashtable();
  private static int increment=5, TotalConnection=0, FreeConnection=0, TotalRequest=0;
  private static String DBURL = Configuration.DBURL, DRIVER = Configuration.DRIVER;
  private static String user = Configuration.USER_NAME, password = Configuration.PASSWORD;

  private ConnectionPool() {
      System.out.println("before class.forName");
      try{
          Class.forName(Configuration.DRIVER);
          System.out.println("before conneciton creation");
          
          // Put our pool of Connections in the Hashtable
          // The FALSE value indicates they're unused
          for(int i=0; i<5; i++) {
              connections.put(DriverManager.getConnection(Configuration.DBURL,user,password),Boolean.FALSE);
              TotalConnection++;
              FreeConnection++;
              System.out.println("after conneciton: free and Total:" + FreeConnection +"-"+ TotalConnection);
          }
      }catch (Exception e){
          System.out.println("during Creating Connection :" + e);
      }
  }

  private static ConnectionPool instance = new ConnectionPool();

  public static ConnectionPool getInstance(){
      return instance;
  }

  public Connection getConnection() throws SQLException {
    Connection con = null;

    Enumeration cons = connections.keys();

    synchronized (connections) {
      while(cons.hasMoreElements()) {
        con = (Connection)cons.nextElement();

        Boolean b = (Boolean)connections.get(con);
        if (b == Boolean.FALSE) {
          // So we found an unused connection.
          // Test its integrity with a quick setAutoCommit(true) call.
          // For production use, more testing should be performed,
          // such as executing a simple query.
          try {
            con.setAutoCommit(false);
          }
          catch(SQLException e) {
            // Problem with the connection, replace it.
            con = DriverManager.getConnection(Configuration.DBURL);
          }
          // Update the Hashtable to show this one's taken
          connections.put(con, Boolean.TRUE);
          FreeConnection--;
          TotalRequest++;
          System.out.println("in getconnection: free: " + FreeConnection +" TotalRequest-"+ TotalRequest);
          // Return the connection
          return con;
        }
      }
    }
  
    // If we get here, there were no free connections.
    // We've got to make more.
    for(int i = 0; i < increment; i++) {
      connections.put(DriverManager.getConnection(Configuration.DBURL),Boolean.FALSE);
      TotalConnection++;
      FreeConnection++;
      System.out.println("At Increment conneciton: " + i);
    }	
 
    // Recurse to get one of the new connections.
    return getConnection();
  }

  public void setConnection(Connection returned) {
      Connection con;
      Enumeration cons = connections.keys();
      while (cons.hasMoreElements()) {
          con = (Connection)cons.nextElement();
          if (con == returned) {
              connections.put(con, Boolean.FALSE);
              FreeConnection++;
              //System.out.println("in getconneciton FreeConnection:" + FreeConnection );
              break;
          }
      }
      System.out.println("in setconnection: free: " + FreeConnection);
  }

  synchronized public int getTotalConnection(){
      return TotalConnection;
  }

  synchronized public int getFreeConnection(){
      return FreeConnection;
  }

  synchronized public int getTotalRequest(){
      return TotalRequest;
  }
}

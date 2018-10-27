package com.example.franzi.rentrapp;
import java.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
//blala
public class Database {

    private java.sql.Connection con = null;
    private final String url = "";
    private final String serverName = "";
    private final String portNumber = "";
    private final String databaseName = "";
    private final String userName = "";
    private final String password = "";
    private final String selectMethod = "cursor";


    public Database(){};

    private String getConnectionURL(){
        return url+serverName+":"+portNumber+";databaseName="+databaseName+ ";selectMethod="+selectMethod+";";
    }

    private java.sql.Connection getConnection(){
        try {
            Class.forName("");
            con = java.sql.DriverManager.getConnection(getConnectionURL(), userName, password);
            if(con!=null){
                System.out.println("Connection Successful");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error Trace in getConnection() : "+e.getMessage());
        }
        return con;
    }
    /*
    public int getCloumnCount (String systemStatus){
        try {
            con = this.getConnection();
            if (con != null) {
                ResultSet rs = con.createStatement().executeQuery("Select * from Antwort where Systemstatus = " + systemStatus);
                ResultSetMetaData rsmd = rs.getMetaData();
                int cols = rsmd.getColumnCount();
                closeConnection();
            } else System.out.println("Error: No active Connection");
        } catch(Exception e){
            e.printStackTrace();

        }
    }
    */
    private void closeConnection(){
        try{
            if(con!=null){
                con.close();
                con = null;
            }
        } catch(Exception e){
                e.printStackTrace();
            }
        }
}




package com.example.ordertaking.database;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectionHelper {
    Connection con;
    String ip="192.168.98.132",dbname="androidDB",user="sa",pwd="raja2000";


//192.168.43.7
    @SuppressLint("NewApi")
    public Connection connectionclass(){

        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        Connection connection=null;
        String ConnectionURL=null;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL= "jdbc:jtds:sqlserver://"+ip+":1433;databaseName="+dbname+";user="+user+";password="+pwd+"; ";
            connection= DriverManager.getConnection(ConnectionURL);

        } catch (Exception ex) {
            Log.e("Error",ex.getMessage());
        }
        return connection;

    }
}

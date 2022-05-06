package com.company.m03uf5pr01.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static Connection instance;

    private ConnectionDB(){};

    public static Connection getInstance() throws SQLException {
        if(instance == null) {
            String url = "jdbc:mysql://localhost:3306/crudproducts";
            String usr = "root";
            String pwd = "";
            instance = DriverManager.getConnection(url, usr, pwd);
        }
        return instance;
    }

    public static void closeConnection() throws SQLException {
        if(instance != null){
            instance.close();
        }
    }
}

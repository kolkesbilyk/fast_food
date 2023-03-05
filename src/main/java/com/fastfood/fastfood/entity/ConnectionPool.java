package com.fastfood.fastfood.entity;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.inject.Inject;

import com.fastfood.fastfood.config.ConfigService;

public final class ConnectionPool {

    private static ConnectionPool instance;

    @Inject
    private ConfigService config;

    private ConnectionPool() {

    }

    public static ConnectionPool getInstance(){
        if (instance == null){
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() {
        System.out.println("Start create connection......");
        Connection connection = null;
        try {
            if (config != null) {
                System.out.println(config.getProperties());
                Class.forName(config.getProperties().getProperty("db_driver"));
                connection = DriverManager.getConnection(config.getProperties().getProperty("db_host") + config.getProperties().getProperty("db_name"),
                    config.getProperties().getProperty("db_login"),
                    config.getProperties().getProperty("db_password"));
            }else {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + "fast_food",
                    "postgres",
                    "postgres");
                System.out.println("Opened connection to db");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error connection to db" + e);
            e.printStackTrace();
        }
        return connection;
    }
}

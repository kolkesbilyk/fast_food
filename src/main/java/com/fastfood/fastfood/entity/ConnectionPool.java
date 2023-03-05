package com.fastfood.fastfood.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLTransientException;

import javax.inject.Inject;

import com.fastfood.fastfood.config.ConfigService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public final class ConnectionPool {

    private static volatile HikariDataSource hikariDataSource;
    public static final int IDLE_TIMEOUT = 30000;
    public static final int CONNECTION_TIMEOUT = 500;

    @Inject
    private ConfigService config;

    private ConnectionPool() {

    }

    public static Connection getConn() throws SQLException {
        return getConn(true, IDLE_TIMEOUT);
    }

    public static Connection getConn(boolean autoCommit, int milliSeconds) throws SQLException{
        try{
            if (hikariDataSource == null){
                hikariDataSource = getDataSource();
            }
            Connection conn = hikariDataSource.getConnection();
            conn.setNetworkTimeout(null, milliSeconds);
            if (!autoCommit){
                conn.setAutoCommit(false);
            }
            return conn;
        }catch (SQLTransientException e){
            throw e;
        }catch (Exception e){
            if (hikariDataSource != null){
                try{
                    hikariDataSource.close();
                }catch (Exception exception){
                    e.printStackTrace();
                }
            }
            hikariDataSource = null;
            e.printStackTrace();
            throw e;
        }
    }

    private synchronized static HikariDataSource getDataSource(){
        if (hikariDataSource != null){
            return hikariDataSource;
        }
        HikariConfig jdbcConfig = new HikariConfig();
        jdbcConfig.setPoolName("BACK");
        jdbcConfig.setIdleTimeout(IDLE_TIMEOUT);
        jdbcConfig.setConnectionTimeout(CONNECTION_TIMEOUT);
        jdbcConfig.setMaximumPoolSize(25);
        jdbcConfig.setMinimumIdle(2);
        jdbcConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/fast_food");
        jdbcConfig.setUsername("postgres");
        jdbcConfig.setPassword("postgres");
        HikariDataSource dataSource = new HikariDataSource(jdbcConfig);
        System.out.println("New Hikari connection pool created");
        return dataSource;
    }
}

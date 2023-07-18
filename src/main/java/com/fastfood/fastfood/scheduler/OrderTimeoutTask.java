package com.fastfood.fastfood.scheduler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.fastfood.fastfood.entity.ConnectionPool;

@Startup
@Singleton
public class OrderTimeoutTask {

    public static final String INFO = "order_timeout_task";

    private final AtomicBoolean busy = new AtomicBoolean(false);

    @Schedule(hour = "*", minute = "*", persistent = false, info = INFO)
    public void cancelOrder(){
        Thread.currentThread().setName(INFO);
        System.out.println("schedule working");

        if (!busy.compareAndSet(false, true)) {
            System.out.println("Already working");
            return;
        }

        String sql = "SELECT * FROM back.order_cancel_by_timeout();";

        try(Connection connection = ConnectionPool.getConn();
            Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                if (resultSet.next() && resultSet.getString(1) != null) {
                    System.out.println("Order with id " + resultSet.getString(1) + " was canceled by timeout");
                }
            }
        }catch (SQLException e){
            System.err.println("Error canceling orders " + e.getMessage());
        }finally {
            System.out.println("schedule cancel working");
            busy.set(false);
        }
    }
}

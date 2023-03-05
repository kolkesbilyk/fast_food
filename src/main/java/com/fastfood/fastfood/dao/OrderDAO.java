package com.fastfood.fastfood.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.fastfood.fastfood.entity.ConnectionPool;
import com.fastfood.fastfood.entity.Dish;
import com.fastfood.fastfood.entity.Order;

public class OrderDAO implements Serializable {

    @Inject
    private DishDAO dishDAO;

    public void saveOrderDishes(Order order){
        String sql = "INSERT INTO back.orders (total_sum, dop_info) VALUES (?, ?) RETURNING id, created";
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setDouble(1, order.getTotalSum());
            ps.setString(2, order.getDopInfo() == null ? "order without info" : order.getDopInfo());
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    order.setId(rs.getLong("id"));
                    order.setCreated(rs.getTimestamp("created"));
                }
            }
            saveOrderDishes(connection, order);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void saveOrderDishes(Connection connection, Order order) throws SQLException {
        String sql = "INSERT INTO back.order_dishes (order_id, dish_id) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (Dish dish : order.getDishes()) {
                ps.setLong(1, order.getId());
                ps.setInt(2, dish.getId());
                ps.executeUpdate();
            }
        }
    }

    public List<Order> getOrders(){
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT DISTINCT o.id FROM back.order_dishes od, back.orders o WHERE o.id = od.order_id AND o.ready_time IS NULL";
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            Statement ps = connection.createStatement()){
            try(ResultSet rs = ps.executeQuery(sql)){
                while (rs.next()){
                    orders.add(getOrderById(rs.getLong("id")));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return orders;
    }

    public Order getOrderById(long id){
        List<Dish> dishes = new ArrayList<>();
        String sql = "SELECT o.*, od.dish_id\n"
            + "from back.order_dishes od, back.orders o\n"
            + "WHERE o.id = ? AND\n"
            + "      o.id = od.order_id AND"
            + "      o.changed IS NULL ;";
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setLong(1, id);
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()) {
                        Order order = loadOrderFromResultSet(rs);
                        while (rs.next()) {
                            dishes.add(dishDAO.getDishById(connection, rs.getInt("dish_id")));
                        }
                        order.setDishes(dishes);
                        return order;
                    }
                }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private Order loadOrderFromResultSet(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("id"));
        order.setTotalSum(rs.getDouble("total_sum"));
        order.setCreated(rs.getTimestamp("created"));
        order.setChanged(rs.getTimestamp("changed"));
        order.setInWork(rs.getBoolean("in_work"));
        order.setWorker(rs.getInt("worker"));
        order.setReady(rs.getTimestamp("ready_time"));
        order.setDopInfo(rs.getString("dop_info"));
        return order;
    }
}

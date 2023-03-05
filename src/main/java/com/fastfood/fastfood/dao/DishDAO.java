package com.fastfood.fastfood.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fastfood.fastfood.entity.ConnectionPool;
import com.fastfood.fastfood.entity.Dish;

public class DishDAO implements Serializable {

    public List<Dish> getDishesByMenuId(int menuId){
        List<Dish> dishes = new ArrayList<>();
        String sql = "SELECT * from back.dishes WHERE id_menu = ?";
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, menuId);
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    dishes.add(loadFromResultSet(rs));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dishes;
    }

    public Dish getDishById(int id) throws SQLException {
        try(Connection connection = ConnectionPool.getInstance().getConnection()){
            return getDishById(connection, id);
        }
    }

    public Dish getDishById(Connection connection, int id){
        String sql = "SELECT * from back.dishes WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return loadFromResultSet(rs);
                }else {
                    throw new IllegalArgumentException("not found dish with id = " + id);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Dish> getAllDishes(){
        List<Dish> dishes = new ArrayList<>();
        String sql = "SELECT * from back.dishes";
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    dishes.add(loadFromResultSet(rs));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dishes;
    }

    private Dish loadFromResultSet(ResultSet rs) throws SQLException {
        Dish dish = new Dish();
        dish.setId(rs.getInt("id"));
        dish.setName(rs.getString("name"));
        dish.setMenuId(rs.getInt("id_menu"));
        dish.setPrize(rs.getDouble("prize"));
        dish.setCreated(rs.getTimestamp("created"));
        dish.setDeactivated(rs.getTimestamp("deactivated"));
        dish.setImage(rs.getString("image"));
        dish.setDescription(rs.getString("description"));
        return dish;
    }
}

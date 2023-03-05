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
import com.fastfood.fastfood.entity.Menu;

public class MenuDAO implements Serializable {

    @Inject
    private DishDAO dishDAO;

    public List<Menu> getMenuList(){
        List<Menu> menuList = new ArrayList<>();
        String sql = "SELECT * from back.dir_menu;";
        try(Connection connection = ConnectionPool.getConn();
            Statement ps = connection.createStatement()){
            try(ResultSet rs = ps.executeQuery(sql)){
                while (rs.next()){
                    Menu menu = new Menu();
                    int menuId = rs.getInt("id");
                    menu.setId(menuId);
                    menu.setName(rs.getString("name"));
                    menu.setImage(rs.getString("image"));
                    menu.setCreated(rs.getTimestamp("created"));
                    menu.setDeactivated(rs.getTimestamp("deactivated"));
                    menu.setDescription(rs.getString("description"));
                    menu.setDishes(dishDAO.getDishesByMenuId(menuId));
                    menuList.add(menu);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return menuList;
    }
}

package com.fastfood.fastfood.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fastfood.fastfood.entity.ConnectionPool;
import com.fastfood.fastfood.entity.Customer;
import com.fastfood.fastfood.exception.AuthException;
import com.fastfood.fastfood.exception.CustomerAuthException;
import com.fastfood.fastfood.exception.DaoException;

public class CustomerDAO implements Serializable {

    public Customer getCustomerByLoginAndPassword(String login, String password) throws AuthException, DaoException {
        String sql = "SELECT * from back.customer WHERE login = ? AND password = ? AND deactivated IS NULL;";
        try(Connection connection = ConnectionPool.getConn();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, login);
            ps.setString(2, password);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return loadFromResultSet(rs);
                }
                throw new CustomerAuthException(login, password);
            }
        }catch (SQLException e){
            throw new DaoException("Error load customer with login: " + login, e);
        }
    }

    private Customer loadFromResultSet(ResultSet rs) throws SQLException{
        Customer customer = new Customer();
        customer.setId(rs.getInt("id"));
        customer.setName("name");
        customer.setLogin("login");
        customer.setPassword("password");
        customer.setCreated(rs.getTimestamp("created"));
        customer.setDeactivated(rs.getTimestamp("deactivated"));
        return customer;
    }
}

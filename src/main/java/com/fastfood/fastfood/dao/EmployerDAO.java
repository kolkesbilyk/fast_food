package com.fastfood.fastfood.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fastfood.fastfood.entity.ConnectionPool;
import com.fastfood.fastfood.entity.Employer;
import com.fastfood.fastfood.entity.Employer.Role;
import com.fastfood.fastfood.exception.AuthException;
import com.fastfood.fastfood.exception.DaoException;

public class EmployerDAO implements Serializable {

    public Employer getEmployerByLoginAndPassword(String login, String password) throws AuthException, DaoException {
        String sql = "SELECT em.*, dr.name as role from back.employer_roles er\n"
            + "    LEFT OUTER JOIN back.dir_roles dr ON dr.id = er.role_id\n"
            + "    LEFT OUTER JOIN back.employer em ON er.employer_id = em.id\n"
            + "WHERE em.login = ?\n"
            + "  AND em.password = ?\n"
            + "  AND em.deactivated IS NULL\n"
            + "  AND dr.deactivated IS NULL;";
        try(Connection connection = ConnectionPool.getConn();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, login);
            ps.setString(2, password);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return loadFromResultSet(rs);
                }
                throw new AuthException(login, password);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DaoException("Employer", e);
        }
    }

    private Employer loadFromResultSet(ResultSet rs) throws SQLException {
        Employer employer = new Employer();
        employer.setId(rs.getInt("id"));
        employer.setRole(Role.valueOf(rs.getString("role")));
        employer.setLogin(rs.getString("login"));
        employer.setFirstName(rs.getString("first_name"));
        employer.setLastName(rs.getString("last_name"));
        employer.setEmail(rs.getString("email"));
        employer.setCreated(rs.getTimestamp("created"));
        employer.setDeactivated(rs.getTimestamp("deactivated"));
        employer.setAuthor(rs.getInt("author"));
        employer.setParentId(rs.getInt("parent_id"));
        return employer;
    }
}

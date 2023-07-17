package com.fastfood.fastfood.view;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.fastfood.fastfood.dao.EmployerDAO;
import com.fastfood.fastfood.entity.Employer;
import com.fastfood.fastfood.exception.AuthException;
import com.fastfood.fastfood.exception.DaoException;
import com.fastfood.fastfood.utils.Util;

@Named
@ViewScoped
public class LoginView implements Serializable {

    @Inject
    private EmployerDAO employerDAO;

    private String login;
    private String password;

    public void login(){
        try {
            Employer employer = employerDAO.getEmployerByLoginAndPassword(login, password);
            Util.redirect(employer.getRole().getWorkSpace());
        }catch (AuthException e){
            System.err.println("auth exception");
        }catch (DaoException e){
            System.err.println("dao exception");
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.fastfood.fastfood.view;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.fastfood.fastfood.auth.AuthQualifier;
import com.fastfood.fastfood.auth.AuthType;
import com.fastfood.fastfood.entity.Employer;
import com.fastfood.fastfood.exception.AuthException;
import com.fastfood.fastfood.exception.DaoException;
import com.fastfood.fastfood.service.AuthService;
import com.fastfood.fastfood.utils.Util;

@Named(value = "loginBean")
@ViewScoped
public class LoginView implements Serializable {

    @Inject
    @AuthQualifier(AuthType.EMPLOYER)
    private AuthService authService;

    private String login;
    private String password;

    public void login(){
        try {
            Employer employer = (Employer) authService.getByLoginAndPassword(login, password);
            Util.redirect(employer.getRole().getWorkSpace());
        }catch (AuthException e){
            System.err.println("auth exception, cause: " + e);
        }catch (DaoException e){
            System.err.println("dao exception " + e);
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

package com.fastfood.fastfood.service;

import javax.inject.Inject;

import com.fastfood.fastfood.auth.AuthQualifier;
import com.fastfood.fastfood.auth.AuthType;
import com.fastfood.fastfood.config.ConfigService;
import com.fastfood.fastfood.dao.EmployerDAO;
import com.fastfood.fastfood.entity.AuthUser;
import com.fastfood.fastfood.exception.AuthException;
import com.fastfood.fastfood.exception.DaoException;
import com.fastfood.fastfood.utils.Util;
import com.fastfood.fastfood.utils.Util.Algorithm;

@AuthQualifier(AuthType.EMPLOYER)
public class EmployerAuthService implements AuthService{

    private final EmployerDAO employerDAO;
    private final ConfigService configService;

    @Inject
    public EmployerAuthService(EmployerDAO employerDAO, ConfigService configService) {
        this.employerDAO = employerDAO;
        this.configService = configService;
    }

    @Override
    public AuthUser getByLoginAndPassword(String login, String password) throws AuthException, DaoException {
        String secretKey = configService.getOrDefault("", "auth", "ui_secret_key");
        String hmacPassword = Util.hmac(password, secretKey, Algorithm.HmacSHA512);
        return employerDAO.getEmployerByLoginAndPassword(login, hmacPassword);
    }
}

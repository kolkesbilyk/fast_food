package com.fastfood.fastfood.service;

import javax.inject.Inject;

import com.fastfood.fastfood.auth.AuthQualifier;
import com.fastfood.fastfood.auth.AuthType;
import com.fastfood.fastfood.config.ConfigService;
import com.fastfood.fastfood.dao.CustomerDAO;
import com.fastfood.fastfood.entity.AuthUser;
import com.fastfood.fastfood.exception.AuthException;
import com.fastfood.fastfood.exception.DaoException;
import com.fastfood.fastfood.utils.Util;
import com.fastfood.fastfood.utils.Util.Algorithm;

@AuthQualifier(AuthType.CUSTOMER)
public class CustomerAuthService implements AuthService{

    private final CustomerDAO customerDAO;
    private final ConfigService configService;

    @Inject
    public CustomerAuthService(CustomerDAO customerDAO, ConfigService configService) {
        this.customerDAO = customerDAO;
        this.configService = configService;
    }

    @Override
    public AuthUser getByLoginAndPassword(String login, String password) throws AuthException, DaoException {
        String secretKey = configService.getOrDefault("", "auth", "api_secret_key");
        String hmacPassword = Util.hmac(password, secretKey, Algorithm.HmacSHA512);
        return customerDAO.getCustomerByLoginAndPassword(login, hmacPassword);
    }
}

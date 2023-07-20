package com.fastfood.fastfood.service;

import java.io.Serializable;

import com.fastfood.fastfood.entity.AuthUser;
import com.fastfood.fastfood.exception.AuthException;
import com.fastfood.fastfood.exception.DaoException;

public interface AuthService extends Serializable {

    AuthUser getByLoginAndPassword(String login, String password) throws AuthException, DaoException;

}

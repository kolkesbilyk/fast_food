package com.fastfood.fastfood.exception;

public class CustomerAuthException extends AuthException{

    private static final String EXCEPTION_MESSAGE = "Not fount customer with login: %s, and password: %s";

    public CustomerAuthException(String login, String password) {
        super(String.format(EXCEPTION_MESSAGE, login, password));
    }
}

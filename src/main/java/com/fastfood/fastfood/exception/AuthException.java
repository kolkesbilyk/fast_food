package com.fastfood.fastfood.exception;

public class AuthException extends Exception{

    private static final String EXCEPTION_MESSAGE = "Not found employer with login: %s and password: %s";
    public AuthException(String login, String password) {
        super(String.format(EXCEPTION_MESSAGE, login, password));
    }
}

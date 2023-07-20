package com.fastfood.fastfood.exception;

public class EmployerAuthException extends AuthException{

    private static final String EXCEPTION_MESSAGE = "Not fount employer with login: %s, and password: %s";

    public EmployerAuthException(String login, String password) {
        super(String.format(EXCEPTION_MESSAGE, login, password));
    }
}

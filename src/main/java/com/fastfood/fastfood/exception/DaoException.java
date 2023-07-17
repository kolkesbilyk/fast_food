package com.fastfood.fastfood.exception;

public class DaoException extends Exception{

    private static final String EXCEPTION_MESSAGE = "Error load %s from data base";

    public DaoException(String entity, Throwable cause) {
        super(String.format(EXCEPTION_MESSAGE, entity), cause);
    }
}

package com.ezequieljuliano.bookmark.infrastructure.exceptions;

public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }

}

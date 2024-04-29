package com.muslimov.vlad.authsecurityjwt.exception.model;

public class BadRequestException extends RuntimeException {

    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }
}

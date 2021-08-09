package com.example.demo.exceptions;

public class BadRequestException extends BaseException{


    public BadRequestException(String errorCode, String message) {
        super(errorCode, message);
    }

    public BadRequestException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }
}

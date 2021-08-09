package com.example.demo.exceptions;

public class NotFoundException extends BaseException{

    public NotFoundException(String errorCode, String message) {
        super(errorCode, message);
    }
}

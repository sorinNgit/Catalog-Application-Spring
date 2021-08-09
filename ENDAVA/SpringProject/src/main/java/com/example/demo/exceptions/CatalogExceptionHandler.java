package com.example.demo.exceptions;

import com.example.demo.dto.ErrorDto;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpTimeoutException;
import java.util.List;

//@ControllerAdvice
//@ResponseBody
@RestControllerAdvice
public class CatalogExceptionHandler {
    //handler pentru exceptia not found

    private Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDto handleNotFound(Exception exception){
        BaseException baseException = (BaseException) exception;

        logger.warn("Not found exception {}", exception);
        return new ErrorDto(baseException.getErrorCode(),baseException.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorDto handleRequestValidation(Exception exception){
        MethodArgumentNotValidException validationException = (MethodArgumentNotValidException) exception;
        List<ObjectError> errors = validationException.getBindingResult().getAllErrors();
        StringBuilder message = new StringBuilder();
        for(ObjectError error : errors){
            message.append(error.getDefaultMessage());
            message.append(";");
        }

        return new ErrorDto("failed.validation.for.request.body", message.toString(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDto handleBadRequest(Exception exception){
        BaseException baseException = (BaseException) exception;
        logger.warn("Bad request exception {}",exception);

        return new ErrorDto(baseException.getErrorCode(), baseException.getMessage(),HttpStatus.BAD_REQUEST.value());
    }


    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleException(Exception exception){
        logger.error("An error has ocurred");
        return new ErrorDto("Internal server error","An error has ocurred", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }


}

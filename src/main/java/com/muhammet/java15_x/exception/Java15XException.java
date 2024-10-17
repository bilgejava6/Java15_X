package com.muhammet.java15_x.exception;

import lombok.Getter;

@Getter
public class Java15XException extends RuntimeException{
    private ErrorType errorType;
    public Java15XException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}

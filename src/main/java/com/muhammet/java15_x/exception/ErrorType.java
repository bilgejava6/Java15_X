package com.muhammet.java15_x.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
    INTERNAL_SERVER_ERROR(500,"Sunucuda beklenmeyen bir hata oldu. Lütfen tekrar deneyin",HttpStatus.INTERNAL_SERVER_ERROR),
    VALIDATION_ERROR(400,"girilen parametreler hatalıdır. Lütfen kontrol ederek tekrar deneyimn.", HttpStatus.BAD_REQUEST),
    FOLLOW_USERID_FOLLOWINGID_NOTFOUND(5001, "userId ya da followingId yanlış girilmiştir.", HttpStatus.BAD_REQUEST);

    int code;
    String message;
    HttpStatus httpStatus;
}

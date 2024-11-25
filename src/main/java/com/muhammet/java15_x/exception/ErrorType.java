package com.muhammet.java15_x.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
    INTERNAL_SERVER_ERROR(500,"Sunucuda beklenmeyen bir hata oldu. Lütfen tekrar deneyin",HttpStatus.INTERNAL_SERVER_ERROR),
    VALIDATION_ERROR(400,"girilen parametreler hatalıdır. Lütfen kontrol ederek tekrar deneyimn.", HttpStatus.BAD_REQUEST),
    PASSWORD_ERROR(6001, "girilen şifreler uyuşmamaktadır",HttpStatus.BAD_REQUEST),
    NOTFOUND_USER(6003,"kullanıcı bulunamadı", HttpStatus.NOT_FOUND),
    POST_NOTFOUND(6004,"ilgili postid ye ait post bulunamadı", HttpStatus.NOT_FOUND),

    INVALID_TOKEN(9001,"geçersiz token bilgisi",HttpStatus.BAD_REQUEST),
    INVALID_USERNAME_OR_PASSWORD(6002,"Kullanıcı adı ya da şifre hatalıdır",HttpStatus.BAD_REQUEST),
    FOLLOW_USERID_FOLLOWINGID_NOTFOUND(5001, "userId ya da followingId yanlış girilmiştir.", HttpStatus.BAD_REQUEST);

    int code;
    String message;
    HttpStatus httpStatus;
}

package com.muhammet.java15_x.controller;

import com.muhammet.java15_x.dto.request.DologinRequestDto;
import com.muhammet.java15_x.dto.request.RegisterRequestDto;
import com.muhammet.java15_x.dto.response.BaseResponse;
import com.muhammet.java15_x.exception.ErrorType;
import com.muhammet.java15_x.exception.Java15XException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UserControllerTest {
    @Autowired
    private UserController userController;

    @Test
    void exceptionRegisterInvalidValuesRepassword() {
        RegisterRequestDto dto = new RegisterRequestDto(
                "demet","Aa12345","Aa12","muhammet@gmail.com"
        );
      Java15XException exception = Assertions.assertThrows(Java15XException.class, ()->userController.register(dto));
      Assertions.assertEquals(ErrorType.PASSWORD_ERROR.getMessage(), exception.getMessage());
    }

    @Test
    void successRegister() {
        RegisterRequestDto dto = new RegisterRequestDto(
                "muhammet","Aa123456**","Aa123456**","muhammet@gmail.com"
        );
       BaseResponse<Boolean> base =  userController.register(dto).getBody();
       Assertions.assertTrue(base.getSuccess());
    }

    @Test
    void successLogin(){
        DologinRequestDto dto = new DologinRequestDto("muhammet","Aa123456**");
        BaseResponse<String> baseResponse =  userController.doLogin(dto).getBody();
        Assertions.assertTrue(baseResponse.getSuccess());
        String token = baseResponse.getData();
        Assertions.assertNotNull(token);
        Assertions.assertNotEquals("", token);
    }

    @Test
    void exceptionLoginInvalidUserNameOrPassword(){
        DologinRequestDto dto  = new DologinRequestDto("muhammet","Aa123456");
        Java15XException exception = Assertions.assertThrows(Java15XException.class,()-> userController.doLogin(dto));
        Assertions.assertEquals(ErrorType.INVALID_USERNAME_OR_PASSWORD.getMessage(),exception.getMessage());
    }
}
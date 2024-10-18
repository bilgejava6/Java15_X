package com.muhammet.java15_x.controller;

import com.muhammet.java15_x.dto.request.DologinRequestDto;
import com.muhammet.java15_x.dto.request.RegisterRequestDto;
import com.muhammet.java15_x.dto.response.BaseResponse;
import com.muhammet.java15_x.entity.User;
import com.muhammet.java15_x.exception.ErrorType;
import com.muhammet.java15_x.exception.Java15XException;
import com.muhammet.java15_x.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.muhammet.java15_x.constant.RestApis.*;
@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(REGISTER)
    public ResponseEntity<BaseResponse<Boolean>> register(@RequestBody @Valid RegisterRequestDto dto){
        if(!dto.password().equals(dto.rePassword()))
            throw new Java15XException(ErrorType.PASSWORD_ERROR);
        userService.register(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .code(200)
                        .data(true)
                        .message("Üyelik başarı ile oluşturuldu")
                        .success(true)
                .build());
    }

    @PostMapping(DOLOGIN)
    public ResponseEntity<BaseResponse<String>> doLogin(@RequestBody @Valid DologinRequestDto dto){
        String token = userService.doLogin(dto);
        return ResponseEntity.ok(BaseResponse.<String>builder()
                        .success(true)
                        .message("Giriş başarılı")
                        .data(token)
                        .code(200)
                .build());
    }

    @GetMapping(GETPROFILE)
    public ResponseEntity<BaseResponse<User>> getProfile(String token){
        return  ResponseEntity.ok(
                BaseResponse.<User>builder()
                        .code(200)
                        .data(userService.getProfile(token))
                        .message("profil bilgisi getirildi.")
                        .success(true)
                        .build()
        );
    }

}

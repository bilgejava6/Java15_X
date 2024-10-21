package com.muhammet.java15_x.controller;

import com.muhammet.java15_x.dto.request.NewPostRequestDto;
import com.muhammet.java15_x.dto.response.BaseResponse;
import com.muhammet.java15_x.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.muhammet.java15_x.constant.RestApis.*;
@RestController
@RequestMapping(POST)
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping(NEWPOST)
    public ResponseEntity<BaseResponse<Boolean>> newPost(@RequestBody @Valid NewPostRequestDto dto){
        postService.newPost(dto);
        return ResponseEntity.ok(
                BaseResponse.<Boolean>builder()
                        .message("yeni post oluşturuldu")
                        .success(true)
                        .data(true)
                        .code(200)
                        .build()
        );
    }
}

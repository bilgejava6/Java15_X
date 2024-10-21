package com.muhammet.java15_x.controller;

import com.muhammet.java15_x.dto.request.NewPostRequestDto;
import com.muhammet.java15_x.dto.response.AllPostsResponseDto;
import com.muhammet.java15_x.dto.response.BaseResponse;
import com.muhammet.java15_x.entity.Post;
import com.muhammet.java15_x.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(GETALLMYPOSTS)
    public ResponseEntity<BaseResponse<List<Post>>> getAllMyPosts(String token){
        return ResponseEntity.ok(
          BaseResponse.<List<Post>>builder()
                  .code(200)
                  .data(postService.getAllMyPosts(token))
                  .success(true)
                  .message("tüm postlar getirildi.")
                  .build()
        );
    }

    @GetMapping(GETALLPOSTS)
    public ResponseEntity<BaseResponse<List<AllPostsResponseDto>>> getAllPosts(String token){
        return ResponseEntity.ok(
                BaseResponse.<List<AllPostsResponseDto>>builder()
                        .code(200)
                        .data(postService.getAllPosts(token))
                        .success(true)
                        .message("tüm postlar getirildi.")
                        .build()
        );
    }

}

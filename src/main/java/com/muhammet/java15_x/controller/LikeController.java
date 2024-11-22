package com.muhammet.java15_x.controller;

import com.muhammet.java15_x.dto.request.LikeRequestDto;
import com.muhammet.java15_x.dto.response.BaseResponse;
import com.muhammet.java15_x.entity.Post;
import com.muhammet.java15_x.exception.ErrorType;
import com.muhammet.java15_x.exception.Java15XException;
import com.muhammet.java15_x.service.LikeService;
import com.muhammet.java15_x.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.muhammet.java15_x.constant.RestApis.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(LIKE)
@CrossOrigin("*")
public class LikeController {
    private final LikeService likeService;
    private final PostService postService;

    @PostMapping(ADDLIKE)
    public ResponseEntity<BaseResponse<Boolean>> addLike(@RequestBody @Valid LikeRequestDto dto){
         boolean existsPost = postService.existsPostById(dto.postId());
         if(!existsPost) throw new Java15XException(ErrorType.VALIDATION_ERROR);
         boolean isAddLike = likeService.addLike(dto);
         if(isAddLike){
             postService.addLike(dto.postId());
         }
         return  ResponseEntity.ok(BaseResponse.<Boolean>builder()
                         .message("Ok.")
                         .success(true)
                         .data(true)
                         .code(200)
                 .build());
    }

    @PostMapping(UNLIKE)
    public ResponseEntity<BaseResponse<Boolean>> unLike(@RequestBody @Valid LikeRequestDto dto){
        boolean existsPost = postService.existsPostById(dto.postId());
        if(!existsPost) throw new Java15XException(ErrorType.VALIDATION_ERROR);
        boolean isUnLike = likeService.unLike(dto);
        if(isUnLike){
            postService.unLike(dto.postId());
        }
        return  ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .message("Ok.")
                .success(true)
                .data(true)
                .code(200)
                .build());
    }
}

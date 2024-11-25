package com.muhammet.java15_x.controller;

import com.muhammet.java15_x.dto.request.NewCommentRequestDto;
import com.muhammet.java15_x.dto.response.BaseResponse;
import com.muhammet.java15_x.entity.Comment;
import com.muhammet.java15_x.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.muhammet.java15_x.constant.RestApis.*;
@RestController
@RequestMapping(COMMENT)
@RequiredArgsConstructor
@CrossOrigin("*")
public class CommentController {
    private final CommentService commentService;

    @PostMapping(ADDCOMMENT)
    public ResponseEntity<BaseResponse<Boolean>> addComment(@RequestBody @Valid NewCommentRequestDto dto){
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .data(commentService.addComment(dto))
                        .message("Ok")
                        .success(true)
                        .code(200)
                .build());
    }

    @GetMapping(GETALLCOMMENTBYPOSTID)
    public ResponseEntity<BaseResponse<List<Comment>>> findAllCommentByPosyId(String token, Long postId){
        return ResponseEntity.ok(
                BaseResponse.<List<Comment>>builder()
                        .code(200)
                        .success(true)
                        .message("Ok")
                        .data(commentService.getAllCommentByPostId(postId, token))
                        .build()
        );
    }

}


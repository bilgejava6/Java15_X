package com.muhammet.java15_x.service;

import com.muhammet.java15_x.dto.request.NewCommentRequestDto;
import com.muhammet.java15_x.entity.Comment;
import com.muhammet.java15_x.entity.CommentState;
import com.muhammet.java15_x.entity.Like;
import com.muhammet.java15_x.exception.ErrorType;
import com.muhammet.java15_x.exception.Java15XException;
import com.muhammet.java15_x.repository.CommentRepository;
import com.muhammet.java15_x.utility.JwtManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final JwtManager jwtManager;

    public boolean addComment(NewCommentRequestDto dto) {
        Optional<Long> userdId = jwtManager.validateToken(dto.token());
        if(userdId.isEmpty()) throw new Java15XException(ErrorType.INVALID_TOKEN);
        // postid kontrol yapılsın, controller katmanında
        commentRepository.save(Comment.builder()
                        .comment(dto.comment())
                        .date(System.currentTimeMillis())
                        .postId(dto.postId())
                        .userId(userdId.get())
                        .state(CommentState.ACTIVE)
                .build());
        return true;
    }

    public List<Comment> getAllCommentByPostId(Long postId, String token){
        Optional<Long> userdId = jwtManager.validateToken(token);
        if(userdId.isEmpty()) throw new Java15XException(ErrorType.INVALID_TOKEN);
        return commentRepository.findAllByPostId(postId);
    }
}

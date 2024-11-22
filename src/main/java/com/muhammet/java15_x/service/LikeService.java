package com.muhammet.java15_x.service;

import com.muhammet.java15_x.dto.request.LikeRequestDto;
import com.muhammet.java15_x.entity.Like;
import com.muhammet.java15_x.entity.LikeState;
import com.muhammet.java15_x.exception.ErrorType;
import com.muhammet.java15_x.exception.Java15XException;
import com.muhammet.java15_x.repository.LikeRepository;
import com.muhammet.java15_x.utility.JwtManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository repository;
    private final JwtManager jwtManager;
    public boolean addLike(LikeRequestDto dto){
        Optional<Long> userId =  jwtManager.validateToken(dto.token());
        if(userId.isEmpty()) throw new Java15XException(ErrorType.INVALID_TOKEN);
        Optional<Like> likeOptional = repository.findOptionalByUserIdAndPostId(userId.get(), dto.postId());
        Like like;
        if(likeOptional.isPresent()){
               like = likeOptional.get();
               if(like.getState() == LikeState.LIKED)   return false;
               like.setState(LikeState.LIKED);
        }else{
            like = new Like();
            like.setPostId(dto.postId());
            like.setUserId(userId.get());
            like.setState(LikeState.LIKED);
        }
        repository.save(like);
        return true;
    }
    public boolean unLike(LikeRequestDto dto){
        Optional<Long> userId =  jwtManager.validateToken(dto.token());
        if(userId.isEmpty()) throw new Java15XException(ErrorType.INVALID_TOKEN);
        Optional<Like> likeOptional = repository.findOptionalByUserIdAndPostId(userId.get(), dto.postId());
        if (likeOptional.isEmpty()) return false;
        Like like = likeOptional.get();
        if(like.getState() == LikeState.UNLIKED)   return false;
        like.setState(LikeState.UNLIKED);
        repository.save(like);
        return true;
    }
}

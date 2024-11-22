package com.muhammet.java15_x.service;

import com.muhammet.java15_x.dto.request.NewPostRequestDto;
import com.muhammet.java15_x.dto.response.AllPostsResponseDto;
import com.muhammet.java15_x.dto.response.BaseResponse;
import com.muhammet.java15_x.entity.*;
import com.muhammet.java15_x.exception.ErrorType;
import com.muhammet.java15_x.exception.Java15XException;
import com.muhammet.java15_x.mapper.PostMapper;
import com.muhammet.java15_x.repository.PostRepository;
import com.muhammet.java15_x.utility.JwtManager;
import com.muhammet.java15_x.views.VwUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final LikeService likeService;
    private final JwtManager jwtManager;

    public void newPost(NewPostRequestDto dto) {
       Optional<Long> userId =  jwtManager.validateToken(dto.token());
       if(userId.isEmpty()) throw new Java15XException(ErrorType.INVALID_TOKEN);
       Post post = PostMapper.INSTANCE.fromNewPostDto(dto, userId.get());
//        Post post = Post.builder()
//                .userId(userId.get())
//                .comment(dto.comment())
//                .imageUrl(dto.imageUrl())
//                .commentCount(0)
//                .date(System.currentTimeMillis())
//                .likeCount(0)
//                .state(PostState.ACTIVE)
//                .viewCount(0)
//                .build();
        postRepository.save(post);
    }


    public List<Post> getAllMyPosts(String token){
        Optional<Long> userId = jwtManager.validateToken(token);
        if(userId.isEmpty()) throw new Java15XException(ErrorType.INVALID_TOKEN);
        return postRepository.findAllByUserId(userId.get());
    }

    public List<AllPostsResponseDto> getAllPosts(String token){
        Long start = System.nanoTime();
        Optional<Long> userId = jwtManager.validateToken(token);
        if(userId.isEmpty()) throw new Java15XException(ErrorType.INVALID_TOKEN);
        Long postListStart = System.nanoTime();
        List<Post> postList = postRepository.findTop100ByOrderByDateDesc();//0-3ms
        Long postListEnd = System.nanoTime();
        log.info("db post request...: "+ (postListEnd-postListStart)+"ns");
        /**
         * postları kısıtlayın. mesela date e göre son altılmış 10 post
         * post listesinin içerisinden userid lerin listesini çıkartın. List<Long> userids
         * kullanıcıların listesini Map<Long, User> userList
         */
        /**
         * Diyelim 3.000.000.000 işlem gücü -> 100 data
         * 30.000.000 saniye de
         * 30.000 -> 1/30.000ms de 100 datayı işleyebilir.
         */
        List<Long> userIds = postList.stream().map(Post::getUserId).toList(); // ms altındadır.
        Long userListStart = System.nanoTime();
        Map<Long,VwUser> userList = userService.findAllByIds(userIds);// 0-2ms -> 10.000
        Long userListEnd = System.nanoTime();
        log.info("db user request...: "+ (userListEnd-userListStart)+"ns");
        List<Long> postIds = postList.stream().map(Post::getId).toList(); // post listesi içinden postların id lerini çekiyoruz
        List<Like> postsLikeList = likeService.getLikeByPostId(postIds); // id leri verilen postların like listesini buluyoruz.
        List<AllPostsResponseDto> result = new ArrayList<>();
        postList.forEach(p->{
            VwUser user = userList.get(p.getUserId());
            // userId post listesini çekmek için token gönderen kullanıcıyı ifade eder.
            boolean isLike= false;
            Optional<Like> findLike = postsLikeList.stream()
                    .filter(x-> x.getUserId().equals(userId.get()) && x.getPostId().equals(p.getId())).findAny();
            /**
             * Eğer DB de kayıtlı bir like var ise, bunun liked olduğunfan emin ol ve
             * bu bilgiyi isLike a aktar. Eğer liked ise true, değil ise false
             */
            if(findLike.isPresent())
                isLike = findLike.get().getState().equals(LikeState.LIKED);
            AllPostsResponseDto newDto = PostMapper.INSTANCE.fromPostAndUser(p,user.userName(),user.name(),user.avatar(), isLike);
            result.add(newDto);
        }); // 0-1ms
        Long end = System.nanoTime();
        log.info("getAllPosts took " + (end - start)  + "ns");
        log.info("getAllPosts took " + (end - start) / 1_000_000  + "ms");
        return result;
    }


    public boolean existsPostById(Long postId) {
       return postRepository.existsById(postId);
    }

    public void addLike(Long postId) {
        Optional<Post> post =  postRepository.findById(postId);
        if(post.isPresent()) {
            Post editPost = post.get();
            editPost.setLikeCount(editPost.getLikeCount()+1);
            postRepository.save(editPost);
        }
    }

    public void unLike(Long postId) {
        Optional<Post> post =  postRepository.findById(postId);
        if(post.isPresent()) {
            Post editPost = post.get();
            int unlikeCount = editPost.getLikeCount() >0 ? editPost.getLikeCount()-1 : 0;
            editPost.setLikeCount(unlikeCount);
            postRepository.save(editPost);
        }
    }
}

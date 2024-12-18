package com.muhammet.java15_x.repository;

import com.muhammet.java15_x.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByUserId(Long userId);

    List<Post> findTop100ByOrderByDateDesc();
}

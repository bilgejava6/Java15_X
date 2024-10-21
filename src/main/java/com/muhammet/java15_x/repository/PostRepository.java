package com.muhammet.java15_x.repository;

import com.muhammet.java15_x.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}

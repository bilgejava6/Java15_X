package com.muhammet.java15_x.repository;

import com.muhammet.java15_x.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}

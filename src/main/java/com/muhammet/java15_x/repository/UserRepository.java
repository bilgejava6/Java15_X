package com.muhammet.java15_x.repository;

import com.muhammet.java15_x.entity.User;
import com.muhammet.java15_x.views.VwUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findOptionalByUserNameAndPassword(String userName, String password);

    @Query("select new com.muhammet.java15_x.views.VwUser(u.id,u.userName,u.name,u.avatar) from User u where u.id in(?1)")
    List<VwUser> findAllByUserIds(List<Long> userIds);

    Optional<User> findOptionalByUserName(String userName);
}

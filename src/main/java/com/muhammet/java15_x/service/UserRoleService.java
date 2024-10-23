package com.muhammet.java15_x.service;

import com.muhammet.java15_x.entity.UserRole;
import com.muhammet.java15_x.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public void saveUserRole(UserRole userRole) {
        userRoleRepository.save(userRole);
    }

    public List<UserRole> getAllUserRoleByUserId(Long userId) {
        return userRoleRepository.findByUserId(userId);
    }
}

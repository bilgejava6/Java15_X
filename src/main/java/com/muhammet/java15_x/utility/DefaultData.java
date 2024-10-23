package com.muhammet.java15_x.utility;

import com.muhammet.java15_x.entity.UserRole;
import com.muhammet.java15_x.service.UserRoleService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultData {
    private final UserRoleService userRoleService;
   // @PostConstruct
    public void init(){
        UserRole userRole = UserRole.builder()
                .role("AHMETAMCA")
                .userId(1L)
                .build();
        userRoleService.saveUserRole(userRole);
    }
}

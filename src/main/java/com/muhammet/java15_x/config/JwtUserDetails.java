package com.muhammet.java15_x.config;

import com.muhammet.java15_x.entity.User;
import com.muhammet.java15_x.entity.UserRole;
import com.muhammet.java15_x.service.UserRoleService;
import com.muhammet.java15_x.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

@Service
@RequiredArgsConstructor
public class JwtUserDetails implements UserDetailsService {

    private final UserService userService;
    private final UserRoleService userRoleService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails getUserById(Long userId){
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isEmpty()) return null;
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<UserRole> userRoles = userRoleService.getAllUserRoleByUserId(userId);
        userRoles.forEach(userRole -> authorities.add(new SimpleGrantedAuthority(userRole.getRole())));
//        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        authorities.add(new SimpleGrantedAuthority("AHMET_AMCA_ROLU"));
//        authorities.add(new SimpleGrantedAuthority("NASILSIN"));
//        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        return org.springframework.security.core.userdetails.User.builder()
                .username(userOptional.get().getUserName())
                .password(userOptional.get().getPassword())
                .accountExpired(false)
                .accountLocked(false)
                .authorities(authorities)
                .build();
    }
}

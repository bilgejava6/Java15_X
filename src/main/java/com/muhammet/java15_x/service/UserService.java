package com.muhammet.java15_x.service;

import com.muhammet.java15_x.dto.request.DologinRequestDto;
import com.muhammet.java15_x.dto.request.RegisterRequestDto;
import com.muhammet.java15_x.entity.ProfileState;
import com.muhammet.java15_x.entity.User;
import com.muhammet.java15_x.mapper.UserMapper;
import com.muhammet.java15_x.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;
    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public void register(RegisterRequestDto dto) {
        User user = UserMapper.INSTANCE.fromRegisterDto(dto);
        user.setCreatedDate(System.currentTimeMillis());
        user.setMemberDate(System.currentTimeMillis());
        user.setModifiedDate(System.currentTimeMillis());
        user.setState(ProfileState.ACTIVE);
        repository.save(user);
    }

    public String doLogin(DologinRequestDto dto) {
        return "";
    }
}

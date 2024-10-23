package com.muhammet.java15_x.service;

import com.muhammet.java15_x.dto.request.DologinRequestDto;
import com.muhammet.java15_x.dto.request.RegisterRequestDto;
import com.muhammet.java15_x.entity.ProfileState;
import com.muhammet.java15_x.entity.User;
import com.muhammet.java15_x.exception.ErrorType;
import com.muhammet.java15_x.exception.Java15XException;
import com.muhammet.java15_x.mapper.UserMapper;
import com.muhammet.java15_x.repository.UserRepository;
import com.muhammet.java15_x.utility.JwtManager;
import com.muhammet.java15_x.views.VwUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final JwtManager jwtManager;
//    public UserService(UserRepository repository, JwtManager jwtManager){
//        this.repository = repository;
//        this.jwtManager = jwtManager;
//    }

    public void register(RegisterRequestDto dto) {
        User user = UserMapper.INSTANCE.fromRegisterDto(dto);
        user.setCreatedDate(System.currentTimeMillis());
        user.setMemberDate(System.currentTimeMillis());
        user.setModifiedDate(System.currentTimeMillis());
        user.setState(ProfileState.ACTIVE);
        repository.save(user);
    }

    public String doLogin(DologinRequestDto dto) {
        // repository e bu kullanıcıadı ve şifre ile bir user var mı diye soruyoruz.
        Optional<User> userOptional = repository.findOptionalByUserNameAndPassword(dto.userName(),dto.password());
        if (userOptional.isEmpty())
            throw new Java15XException(ErrorType.INVALID_USERNAME_OR_PASSWORD);
        String token = jwtManager.createToken(userOptional.get().getId());
        return token;
    }

    public User getProfile(String token) {
      Optional<Long> userId =  jwtManager.validateToken(token);
      if(userId.isEmpty()) // 1. aşama token kontrolü yapılarak sağlanır.
          throw new Java15XException(ErrorType.INVALID_TOKEN);
      Optional<User> userOptional = repository.findById(userId.get());
      if(userOptional.isEmpty()) // 2. aşama böyle bir kullanıcı var mıdır? kontrolü sağlanır.
          throw new Java15XException(ErrorType.NOTFOUND_USER);
      return userOptional.get();
    }


    public Map<Long,VwUser> findAllByIds(List<Long> userIds) {
        return repository.findAllByUserIds(userIds).stream()
                .collect(Collectors.toMap(VwUser::id, vwUser -> vwUser));
    }

    public Optional<User> findById(Long userId) {
        return repository.findById(userId);
    }
}

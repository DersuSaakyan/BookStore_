package com.inconcept.demo.service;

import com.inconcept.demo.persistence.entity.AuthorEntity;
import com.inconcept.demo.persistence.entity.UserEntity;
import com.inconcept.demo.persistence.entity.enums.UserEnum;
import com.inconcept.demo.persistence.repository.UserRepository;
import com.inconcept.demo.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto registerUser(UserEntity userEntity) throws Exception {
        if (userRepository.existsUserByEmail(userEntity.getEmail())) {
            throw new Exception("Email is busy");
        }
        userEntity.setPassword(new BCryptPasswordEncoder().encode(userEntity.getPassword()));
        userEntity.setStatus("ACTIVE");
        userEntity.setUserEnum(UserEnum.USER);
        userRepository.save(userEntity);
        return  UserDto.castEntityToDo(userEntity);
    }
}

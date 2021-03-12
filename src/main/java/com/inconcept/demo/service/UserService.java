package com.inconcept.demo.service;

import com.inconcept.demo.persistence.entity.UserEntity;
import com.inconcept.demo.persistence.entity.enums.UserEnum;
import com.inconcept.demo.persistence.repository.UserRepository;
import com.inconcept.demo.service.dto.UserDto;
import com.inconcept.demo.service.model.ContentQuery;
import com.inconcept.demo.service.model.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ContentQuery<UserWrapper> getUsers(Integer size, Integer page, String sortBy) {

        size = size == null ? 20 : size;
        page = page == null ? 0 : page;
        sortBy = sortBy == null ? "firstName" : sortBy;

        Page<UserWrapper> userDtoPage = userRepository.findAllWithPage(PageRequest.of(page, size, Sort.by(sortBy)));
        return new ContentQuery<UserWrapper>(userDtoPage.getTotalPages(), userDtoPage.getContent());

    }


    public UserDto registerUser(UserEntity userEntity) throws Exception {
        if (userRepository.existsUserByEmail(userEntity.getEmail())) {
            throw new Exception("Email is busy");
        }
        userEntity.setPassword(new BCryptPasswordEncoder().encode(userEntity.getPassword()));
        userEntity.setStatus("ACTIVE");
        userEntity.setUserEnum(UserEnum.USER);
        userRepository.save(userEntity);
        return UserDto.castEntityToDo(userEntity);
    }

    public UserDto getUser(Long id) throws Exception {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new Exception(String.format("User which id %d not found", id)));
        return UserDto.castEntityToDo(userEntity);
    }
}

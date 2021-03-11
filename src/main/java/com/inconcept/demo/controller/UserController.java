package com.inconcept.demo.controller;


import com.inconcept.demo.persistence.entity.UserEntity;
import com.inconcept.demo.service.UserService;
import com.inconcept.demo.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserDto> registerUser(@RequestBody UserEntity userEntity) throws Exception {
        if (userEntity.getFirstName() == null || userEntity.getLastName() == null || userEntity.getEmail() == null || userEntity.getPassword() == null) {
            throw new Exception("Fill required fields");
        }
        UserDto userDto = userService.registerUser(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }



}

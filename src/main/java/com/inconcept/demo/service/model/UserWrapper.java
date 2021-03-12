package com.inconcept.demo.service.model;

import com.inconcept.demo.persistence.entity.UserEntity;
import com.inconcept.demo.persistence.entity.enums.UserEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class UserWrapper {
    private String firstName;
    private String lastName;
    private String email;
    private String status;
    private UserEnum userEnum;

    public UserWrapper(String firstName, String lastName, String email, String status, UserEnum userEnum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
        this.userEnum = userEnum;
    }
}

package com.inconcept.demo.service.dto;


import com.inconcept.demo.persistence.entity.BookEntity;
import com.inconcept.demo.persistence.entity.RateEntity;
import com.inconcept.demo.persistence.entity.UserEntity;
import com.inconcept.demo.persistence.entity.enums.UserEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String status;
    private UserEnum userEnum;
    private List<String> ratesBooks;


    public static UserDto castEntityToDo(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        UserDto userDto = UserDto.builder()
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .status(userEntity.getStatus())
                .userEnum(userEntity.getUserEnum())
                .build();
        List<RateEntity> rateEntities = userEntity.getListUserRates();
        if (!CollectionUtils.isEmpty(rateEntities)) {
            userDto.setRatesBooks(rateEntities.stream().map(RateEntity::getBook).map(BookEntity::getTitle).collect(Collectors.toList()));
        }
        return userDto;
    }
}

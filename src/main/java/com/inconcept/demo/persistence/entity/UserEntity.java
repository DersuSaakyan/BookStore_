package com.inconcept.demo.persistence.entity;


import com.inconcept.demo.persistence.entity.enums.UserEnum;
import com.inconcept.demo.service.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String firstName;

    @Column(name = "surname", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "hash_pass", nullable = false)
    private String password;

    @Column
    private String status;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserEnum userEnum;

    @OneToMany(mappedBy = "user", targetEntity = RateEntity.class, fetch = FetchType.LAZY)
    private List<RateEntity> listUserRates;

    @OneToMany(mappedBy = "user", targetEntity = CollectionsEntity.class, fetch = FetchType.LAZY)
    private List<CollectionsEntity> collectionsEntityList;


}

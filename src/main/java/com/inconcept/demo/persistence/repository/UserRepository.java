package com.inconcept.demo.persistence.repository;

import com.inconcept.demo.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsUserByEmail(String str);
}

package com.inconcept.demo.persistence.repository;

import com.inconcept.demo.persistence.entity.UserEntity;
import com.inconcept.demo.service.dto.UserDto;
import com.inconcept.demo.service.model.UserWrapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsUserByEmail(String str);

    @Query("SELECT new com.inconcept.demo.service.model.UserWrapper(u.firstName,u.lastName,u.email,u.status,u.userEnum)  from UserEntity u")
    Page<UserWrapper> findAllWithPage(Pageable pageable);
}

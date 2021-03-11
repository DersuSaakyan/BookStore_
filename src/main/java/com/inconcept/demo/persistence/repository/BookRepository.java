package com.inconcept.demo.persistence.repository;

import com.inconcept.demo.persistence.entity.BookEntity;
import com.inconcept.demo.persistence.entity.RateEntity;
import com.inconcept.demo.service.dto.BookDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

//    @Query("select  new com.inconcept.demo.service.dto.BookDtoBuilder(b.title) from BookEntity b")
//    BookDto findOneBookEntitiesById(Long id);
    BookEntity findOneById(Long id);
}

package com.inconcept.demo.service.model;

import com.inconcept.demo.persistence.entity.AuthorEntity;
import com.inconcept.demo.persistence.entity.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookWrapper {
    private String title;
    private String desc;
    private String publishDate;
    private Double avgRating;
    private Set<String> authors;

    public BookWrapper(String title, String desc, String publishDate, Double avgRating) {
        this.title = title;
        this.desc = desc;
        this.publishDate = publishDate;
        this.avgRating = avgRating;
    }

    public BookWrapper(BookEntity bookEntity) {
        this.title = bookEntity.getTitle();
        this.desc = bookEntity.getDesc();
        this.publishDate = bookEntity.getPublishDate();
        if (!CollectionUtils.isEmpty(bookEntity.getBookAuthors())) {
            this.authors = bookEntity.getBookAuthors().stream().map(AuthorEntity::getFullName).collect(Collectors.toSet());
        }
    }

}

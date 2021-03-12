package com.inconcept.demo.service.dto;


import com.inconcept.demo.persistence.entity.AuthorEntity;
import com.inconcept.demo.persistence.entity.BookEntity;
import com.inconcept.demo.persistence.entity.RateEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private String title;
    private String desc;
    private String publishDate;
    private Double price;
    private Double avgRating;
    private Set<String> bookAuthors;


    public static Double getAvgRatingBook(List<RateEntity> rateEntityList) {
        if (!CollectionUtils.isEmpty(rateEntityList)) {
            double temp = rateEntityList.stream().map(RateEntity::getRate).mapToDouble(Double::doubleValue).sum();
            return temp / rateEntityList.size();
        }
        return null;
    }

//    public BookDto(BookEntity bookEntity) {
//        this.title = bookEntity.getTitle();
//        this.desc = bookEntity.getDesc();
//        this.publishDate = bookEntity.getPublishDate();
//        this.avgRating = getAvgRatingBook(bookEntity.getListBookRates());
//        Set<AuthorEntity> authorEntitySet = bookEntity.getBookAuthors();
//        if (!CollectionUtils.isEmpty(authorEntitySet)) {
//            this.bookAuthors=bookEntity.getBookAuthors().stream().map(AuthorEntity::getFullName).collect(Collectors.toSet());
//        }
//    }

    public static BookDto castEntityToDo(BookEntity bookEntity) {
        if (bookEntity == null) {
            return null;
        }
        BookDto bookDto = BookDto.builder()
                .title(bookEntity.getTitle())
                .desc(bookEntity.getDesc())
                .price(bookEntity.getPrice())
                .publishDate(bookEntity.getPublishDate())
                .avgRating(BookDto.getAvgRatingBook(bookEntity.getListBookRates()))
                .build();
        Set<AuthorEntity> authorEntitySet = bookEntity.getBookAuthors();
        if (!CollectionUtils.isEmpty(authorEntitySet)) {
            bookDto.setBookAuthors(bookEntity.getBookAuthors().stream().map(AuthorEntity::getFullName).collect(Collectors.toSet()));
        }
        return bookDto;
    }
}

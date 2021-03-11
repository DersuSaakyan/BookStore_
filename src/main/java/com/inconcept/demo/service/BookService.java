package com.inconcept.demo.service;


import com.inconcept.demo.persistence.entity.BookEntity;
import com.inconcept.demo.persistence.repository.BookRepository;
import com.inconcept.demo.service.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDto addBook(BookEntity bookEntity) {
        bookRepository.save(bookEntity);
        return BookDto.castEntityToDo(bookEntity);
    }

    public Double getBookRate(Long bookId) {
        BookEntity bookEntity = bookRepository.findOneById(bookId);
        BookDto bookDto = BookDto.castEntityToDo(bookEntity);
        return bookDto.getAvgRating();
    }

    public BookDto getBook(Long id) {
        BookEntity bookEntity = bookRepository.findOneById(id);
        return BookDto.castEntityToDo(bookEntity);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public BookDto updateBook(Long id, BookDto bookDto) {
        return null;
    }
}

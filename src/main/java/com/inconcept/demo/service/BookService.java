package com.inconcept.demo.service;


import com.inconcept.demo.persistence.entity.BookEntity;
import com.inconcept.demo.persistence.repository.BookRepository;
import com.inconcept.demo.service.dto.BookDto;
import com.inconcept.demo.service.model.BookWrapper;
import com.inconcept.demo.service.model.ContentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public ContentQuery<BookWrapper> getBooks(Integer size, Integer page, String sortBy) {
        size = size == null ? 20 : size;
        page = page == null ? 0 : page;
        sortBy = sortBy == null ? "title" : sortBy;

        Page<BookWrapper> bookDtoPage = bookRepository.findAllWithPage(PageRequest.of(page, size, Sort.by(sortBy)));
        return new ContentQuery<BookWrapper>(bookDtoPage.getTotalPages(), bookDtoPage.getContent());
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

    public BookDto getBook(Long id) throws Exception {
        BookEntity bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new Exception(String.format("Book which id %d not found", id)));
        ;

        return BookDto.castEntityToDo(bookEntity);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public BookDto updateBook(Long id, BookDto bookDto) throws Exception {
        BookEntity bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new Exception(String.format("Book which id %d not found", id)));
        ;

        if (bookDto.getTitle() != null) {
            bookEntity.setTitle(bookDto.getTitle());
        }
        if (bookDto.getDesc() != null) {
            bookEntity.setDesc(bookDto.getDesc());
        }
        if (bookDto.getPublishDate() != null) {
            bookEntity.setPublishDate(bookDto.getPublishDate());
        }
        if (bookDto.getPrice() != null) {
            bookEntity.setPrice(bookDto.getPrice());
        }
        bookEntity = bookRepository.save(bookEntity);
        return BookDto.castEntityToDo(bookEntity);
    }
}

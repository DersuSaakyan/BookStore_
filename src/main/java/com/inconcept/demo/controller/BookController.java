package com.inconcept.demo.controller;


import com.inconcept.demo.persistence.entity.BookEntity;
import com.inconcept.demo.service.BookService;
import com.inconcept.demo.service.dto.BookDto;
import com.inconcept.demo.service.model.BookWrapper;
import com.inconcept.demo.service.model.ContentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public ContentQuery<BookWrapper> getBooks(@RequestParam(required = false) Integer size, @RequestParam(required = false) Integer page,
                                              @RequestParam(required = false) String sortBy) {
        return bookService.getBooks(size, page, sortBy);
    }

    @PostMapping()
    public ResponseEntity<BookDto> addBook(@RequestBody BookEntity bookEntity) throws Exception {
        if (bookEntity.getTitle() == null) {
            throw new Exception("Fill required fields");
        }
        BookDto bookDto = bookService.addBook(bookEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable() Long id) throws Exception {
        return ResponseEntity.ok(bookService.getBook(id));
    }


    @GetMapping("/rate")
    public Double getBookRating(@RequestParam("bookId") Long id) {
        return bookService.getBookRate(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable() Long id, @RequestBody BookDto bookDto) throws Exception {
        BookDto dto = bookService.updateBook(id, bookDto);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }


    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
    }
}

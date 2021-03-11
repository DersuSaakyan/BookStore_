package com.inconcept.demo.controller;


import com.inconcept.demo.persistence.entity.BookEntity;
import com.inconcept.demo.service.BookService;
import com.inconcept.demo.service.dto.BookDto;
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

    @PostMapping()
    public ResponseEntity<BookDto> addBook(@RequestBody BookEntity bookEntity) throws Exception {
        if (bookEntity.getTitle() == null) {
            throw new Exception("Fill required fields");
        }
        BookDto bookDto = bookService.addBook(bookEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable() Long id) {
        return ResponseEntity.ok(bookService.getBook(id));
    }


    @GetMapping("/rate")
    public Double getBookRating(@RequestParam("bookId") Long id) {
        return bookService.getBookRate(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable() Long id,@RequestBody BookDto bookDto){

        return null;
    }


    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
    }
}

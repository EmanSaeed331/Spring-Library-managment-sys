package com.example.demo.controller;

import com.example.demo.model.BookModel;
import com.example.demo.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/books")

public class BookController {

    @Autowired
    private BookService bookService;
    @GetMapping
    public ResponseEntity<List<BookModel>> getAllBooks() {
        List<BookModel> books =  bookService.getAllBooks();
        return ResponseEntity.ok(books);

    }

    @Operation(summary = "Get a book by its id")

    @GetMapping("/{id}")
    public ResponseEntity<BookModel> getBookById(@PathVariable Long id) {
        Optional<BookModel> bookOptional = bookService.getBookById(id);
        if (bookOptional.isPresent()) {
            return ResponseEntity.ok(bookOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<BookModel> addBook(@Valid @RequestBody BookModel book) {
       BookModel createdBook =   bookService.createBook(book);
       return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookModel> updateBook(@PathVariable Long id ,@Valid @RequestBody BookModel book) {
        BookModel updatedBook = bookService.updateBook(id,book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
         bookService.deleteBook(id);
         return ResponseEntity.noContent().build();
    }


}

package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.logging.LoggingAspect;
import com.example.demo.model.BookModel;
import com.example.demo.repository.BookRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepo bookRepo;
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Autowired
    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Transactional
    public BookModel createBook(BookModel book) {
        logger.info("Book is Created ");
        return bookRepo.save(book);
    }

    @Cacheable(value = "books", key = "#id")
    public Optional<BookModel> getBookById(Long id) {
        logger.info("Book is Retrieved ");
        return bookRepo.findById(id);
    }

    @Cacheable("books")
    public List<BookModel> getAllBooks() {
        logger.info("Books are Retrieved ");
        return bookRepo.findAll();
    }

    @Transactional
    public BookModel updateBook(Long id, BookModel book) {
        if (!bookRepo.existsById(id)) {
            logger.error("book is not exist");
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        book.setId(id);
        logger.info("Book is Updated ");
        return bookRepo.save(book);
    }
    @Transactional
    public void deleteBook(Long id) {
        if (!bookRepo.existsById(id)) {

            logger.error("book is not exist");
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        logger.info("Book is Deleted ");
        bookRepo.deleteById(id);
    }
}

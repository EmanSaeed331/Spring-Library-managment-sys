package com.example.demo.service;

import com.example.demo.logging.LoggingAspect;
import com.example.demo.model.BookModel;
import com.example.demo.model.BorrowModel;
import com.example.demo.model.PatronModel;
import com.example.demo.repository.BookRepo;
import com.example.demo.repository.BorrowRepo;
import com.example.demo.repository.PatronRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class BorrowServiceImp implements BorrowService{
    private BookRepo bookRepository;
    private  PatronRepo patronRepository;
    private  BorrowRepo borrowRepository;
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);


    @Autowired
    public void BorrowServiceImpl(BookRepo bookRepository, PatronRepo patronRepository, BorrowRepo borrowRepository) {
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
        this.borrowRepository = borrowRepository;
    }
    @Override
    @Transactional
    public void borrowBook(Long bookId, Long patronId) {
        BookModel book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + bookId));
        PatronModel patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new IllegalArgumentException("Patron not found with id: " + patronId));
        logger.info("Borrowing book " + bookId + " from patron " + patronId);
        if (book.isAvailable()) {
            BorrowModel borrow = new BorrowModel();
            borrow.setBook(book);
            borrow.setPatron(patron);
            borrow.setBorrowingDate(new Date());
            borrow.setReturned(false);
            borrowRepository.save(borrow);
            book.setAvailable(false);
            bookRepository.save(book);
            logger.info("Borrowed book: " + book);
        } else {
            logger.error("Book is not available");
            throw new IllegalStateException("Book is not available for borrowing");
        }
    }

    @Override
    @Transactional
    public void returnBook(Long bookId, Long patronId) {
        BorrowModel borrow = borrowRepository.findByBookIdAndPatronIdAndReturnedFalse(bookId, patronId)
                .orElseThrow(() -> new IllegalArgumentException("No active borrowing found for bookId: " + bookId + " and patronId: " + patronId));
        borrow.setReturnDate(new Date());
        borrow.setReturned(true);
        borrowRepository.save(borrow);

        BookModel book = borrow.getBook();
        book.setAvailable(true);
        bookRepository.save(book);
        logger.info("Returned book: " + book);
    }
}

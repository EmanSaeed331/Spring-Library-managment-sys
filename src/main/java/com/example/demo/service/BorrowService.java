package com.example.demo.service;

public interface BorrowService {
    void borrowBook(Long bookId, Long patronId);
    void returnBook(Long bookId, Long patronId);
}

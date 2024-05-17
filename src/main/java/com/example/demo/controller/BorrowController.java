package com.example.demo.controller;

import com.example.demo.service.BorrowService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class BorrowController {
    @Autowired
    private BorrowService borrowService;
    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<String> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        borrowService.borrowBook(bookId,patronId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Book Borrowed Successfully");
    }
    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
       borrowService.returnBook(bookId,patronId);
       return ResponseEntity.ok("Book Returned Successfully");
    }
}

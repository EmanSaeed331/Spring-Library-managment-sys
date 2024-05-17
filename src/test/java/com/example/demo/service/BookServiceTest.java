package com.example.demo.service;

import com.example.demo.model.BookModel;
import com.example.demo.model.BorrowModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookServiceTest {
    @Test
    public void testCreateBook() {
        Long id = 1L;
        String title = "Test Title";
        String author = "Test Author";
        String isbn = "1234567890";
        String publicationYear = "2022";
        List<BorrowModel> borrowingRecords = new ArrayList<>();


        BookModel book = new BookModel(id, title, author, isbn, publicationYear, true, borrowingRecords);

        assertEquals(id, book.getId());
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(isbn, book.getIsbn());
        assertEquals(publicationYear, book.getPublicationYear());
        assertTrue(book.isAvailable());
    }

    @Test
    public void testUpdateBook() {
        Long id = 1L;
        String title = "Updated Title";
        String author = "Updated Author";
        String isbn = "0987654321";
        String publicationYear = "2023";
        List<BorrowModel> borrowingRecords = new ArrayList<>();

        BookModel book = new BookModel(id, "test title", "author", "3434", "3903", true, borrowingRecords);

        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setPublicationYear(publicationYear);

        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(isbn, book.getIsbn());
        assertEquals(publicationYear, book.getPublicationYear());
    }

}

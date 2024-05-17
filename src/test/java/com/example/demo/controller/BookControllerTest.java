package com.example.demo.controller;


import com.example.demo.model.BookModel;
import com.example.demo.model.BorrowModel;
import com.example.demo.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(BookController.class)
@AutoConfigureMockMvc
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void testGetBookById() throws Exception {
        Long bookId = 1L;
        List<BorrowModel> borrowingRecords = new ArrayList<>();
        BookModel book = new BookModel(bookId, "Test title", "Test author", "1234567890", "2022", true, borrowingRecords);

        when(bookService.getBookById(bookId)).thenReturn(Optional.of(book));

        mockMvc.perform(get("/api/books/{id}", bookId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllBooks() throws Exception {
        List<BookModel> books = new ArrayList<>();
        BookModel book1 = new BookModel(1L, "Test title 1", "Test author 1", "1234567890", "2022", true, new ArrayList<>());
        BookModel book2 = new BookModel(2L, "Test title 2", "Test author 2", "0987654321", "2021", true, new ArrayList<>());
        books.add(book1);
        books.add(book2);

        when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddBook() throws Exception {
        BookModel book = new BookModel(null, "Test title", "Test author", "1234567890", "2022", true, new ArrayList<>());

        when(bookService.createBook(book)).thenReturn(book);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateBook() throws Exception {
        Long bookId = 1L;
        BookModel book = new BookModel(bookId, "Updated title", "Updated author", "1234567890", "2022", true, new ArrayList<>());
        BookModel bookToUpdated = new BookModel(bookId, "Updated title", "Updated author", "1234567890", "2022", true, new ArrayList<>());

        when(bookService.updateBook(eq(bookId), any(BookModel.class))).thenReturn(book);

        mockMvc.perform(put("/api/books/{id}", bookId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(bookToUpdated)));
    }
}
package com.example.demo.model;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name="Books")

public class BookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "title")
    @NotBlank
    private String title;

    @Column(name = "author")
    @NotBlank
    private String author;

    @NotNull
    @Column(name = "isbn")
    private String isbn;

    @NotNull
    @Column(name = "publicationYear")
    private String publicationYear;

    @Column(name = "available")
    private boolean available;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BorrowModel> borrowingRecords;


    public BookModel(Long id, String title, String author, String isbn, String publicationYear, boolean available, List<BorrowModel> borrowingRecords) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.available = available;
        this.borrowingRecords = borrowingRecords;
    }

    public BookModel() {

    }

    public List<BorrowModel> getBorrowingRecords() {
        return borrowingRecords;
    }

    public void setBorrowingRecords(List<BorrowModel> borrowingRecords) {
        this.borrowingRecords = borrowingRecords;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

package com.example.demo.model;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name ="Borrowing")

public class BorrowModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name="borrowing_date" , nullable = false)
    private Date borrowingDate;

    @Column(name="return_date")
    private Date returnDate;

    @Column(name="returned")
    private boolean returned;

    @ManyToOne
    @JoinColumn(name="book_id" , nullable = false)
    private BookModel book;

    @ManyToOne
    @JoinColumn(name="patron_id" , nullable = false)
    private PatronModel patron;

    public BorrowModel(Long id, PatronModel patron, BookModel book, boolean returned, Date returnDate, Date borrowingDate) {
        this.id = id;
        this.patron = patron;
        this.book = book;
        this.returned = returned;
        this.returnDate = returnDate;
        this.borrowingDate = borrowingDate;
    }

    public BorrowModel() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(Date borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public BookModel getBook() {
        return book;
    }

    public void setBook(BookModel book) {
        this.book = book;
    }

    public PatronModel getPatron() {
        return patron;
    }

    public void setPatron(PatronModel patron) {
        this.patron = patron;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}



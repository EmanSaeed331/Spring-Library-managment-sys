package com.example.demo.model;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;


@Entity
@Table(name="Patrons")

public class PatronModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="patron_id")
    private Long id;

    @Column(name="name")
    @NotNull
    private String name;

    @Column(name="contactInfo")
    @NotNull
    private String contactInfo;

    @OneToMany(mappedBy = "patron", cascade = CascadeType.ALL)
    private List<BorrowModel> borrowingRecords;

    public PatronModel(Long id, String name, String contactInfo, List<BorrowModel> borrowingRecords) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.borrowingRecords = borrowingRecords;
    }

    public PatronModel() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<BorrowModel> getBorrowingRecords() {
        return borrowingRecords;
    }

    public void setBorrowingRecords(List<BorrowModel> borrowingRecords) {
        this.borrowingRecords = borrowingRecords;
    }
}

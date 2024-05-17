# Library Management System
## Table of Contents
1. [Introduction](#introduction)
2. [Prerequisites](#prerequisites)
3. [Setup](#setup)
4. [Running the Application](#running-the-application)
5. [API Endpoints](#api-endpoints)


## Introduction
This document provides a comprehensive guide to setting up, running, and interacting with the application. The application is a library management system that allows users to manage books and patrons, as well as borrow and return books.

## Prerequisites
Before you begin, ensure you have the following installed:
- Java 17
- Apache Maven
- Git

## Setup
1. **Clone the repository:**
    ```bash
    git clone https://github.com/EmanSaeed331/Spring-Library-managment-sys
    cd your-repository
    ```


## Running the Application
1. **Build the application:**
    ```bash
    mvn clean install
    ```

2. **Run the application:**
    ```bash
    mvn spring-boot:run
    ```

   The application will start on `http://localhost:8080`.
## API Endpoints
### Books
- **Get Book by ID**
    - **Endpoint:** `GET /api/books/{id}`
    - **Response:**
        ```json
        {
            "id": 1,
            "title": "Test title",
            "author": "Test author",
            "isbn": "1234567890",
            "publishedDate": "2022",
            "available": true,
            "borrowingRecords": []
        }
        ```

- **Get All Books**
    - **Endpoint:** `GET /api/books`
    - **Response:** Array of books.

- **Add Book**
    - **Endpoint:** `POST /api/books`
    - **Request Body:**
        ```json
        {
            "title": "New Book",
            "author": "Author Name",
            "isbn": "0987654321",
            "publishedDate": "2023",
            "available": true
        }
        ```
    - **Response:** 201 Created

- **Update Book**
    - **Endpoint:** `PUT /api/books/{id}`
    - **Request Body:** Same as Add Book
    - **Response:** 200 OK

### Patrons
- **Get Patron by ID**
    - **Endpoint:** `GET /api/patrons/{id}`
    - **Response:**
        ```json
        {
            "id": 1,
            "name": "Patron Name",
            "email": "patron@example.com",
            "borrowedBooks": []
        }
        ```

- **Get All Patrons**
    - **Endpoint:** `GET /api/patrons`
    - **Response:** Array of patrons.

- **Add Patron**
    - **Endpoint:** `POST /api/patrons`
    - **Request Body:**
        ```json
        {
            "name": "New Patron",
            "email": "newpatron@example.com"
        }
        ```
    - **Response:** 201 Created

- **Update Patron**
    - **Endpoint:** `PUT /api/patrons/{id}`
    - **Request Body:** Same as Add Patron
    - **Response:** 200 OK

### Borrowing
- **Borrow Book**
    - **Endpoint:** `POST /borrow/{bookId}/patron/{patronId}`
    - **Response:** 201 Created, "Book Borrowed Successfully"

- **Return Book**
    - **Endpoint:** `PUT /return/{bookId}/patron/{patronId}`
    - **Response:** 200 OK, "Book Returned Successfully"
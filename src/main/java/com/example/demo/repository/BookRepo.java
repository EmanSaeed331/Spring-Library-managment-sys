package com.example.demo.repository;

import com.example.demo.model.BookModel;
import org.springframework.data.repository.ListCrudRepository;

public interface BookRepo extends ListCrudRepository<BookModel, Long> {
}

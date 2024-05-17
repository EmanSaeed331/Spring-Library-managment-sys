package com.example.demo.repository;

import com.example.demo.model.BorrowModel;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface BorrowRepo extends ListCrudRepository<BorrowModel,Long> {
    Optional<BorrowModel> findByBookIdAndPatronIdAndReturnedFalse(Long bookId, Long patronId);

}

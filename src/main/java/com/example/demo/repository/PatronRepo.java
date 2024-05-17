package com.example.demo.repository;

import com.example.demo.model.PatronModel;
import org.springframework.data.repository.ListCrudRepository;

public interface PatronRepo extends ListCrudRepository<PatronModel,Long> {
}

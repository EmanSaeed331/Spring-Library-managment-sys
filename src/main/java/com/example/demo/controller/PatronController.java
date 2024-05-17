package com.example.demo.controller;

import com.example.demo.model.PatronModel;
import com.example.demo.service.PatronService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("api/patrons")

public class PatronController {

    @Autowired
    private PatronService patronService;

    @GetMapping
    @ApiOperation("Get a greeting message")
    public ResponseEntity<List<PatronModel>> getPatrons() {
        List<PatronModel> books =  patronService.getAllPatrons();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatronModel> getPatronById(@PathVariable Long id) {
        PatronModel patron = patronService.getPatronById(id);
        return ResponseEntity.ok(patron);    }
    @PostMapping
    public ResponseEntity<PatronModel> createPatron(@Valid @RequestBody PatronModel patron) {
        PatronModel createdPatron = patronService.createPatron(patron);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatron);    }

    @PutMapping("/{id}")
    public ResponseEntity<PatronModel> updatePatron(@PathVariable Long id, @Valid @RequestBody PatronModel patron) {
        PatronModel updatedPatron = patronService.updatePatron(id, patron);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPatron);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        patronService.deletePatron(id);
        return ResponseEntity.noContent().build();
    }
}

package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.logging.LoggingAspect;
import com.example.demo.model.PatronModel;
import com.example.demo.repository.PatronRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {
    private final PatronRepo patronRepo;
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);


    @Autowired
    public PatronService(PatronRepo patronRepo) {
        this.patronRepo = patronRepo;
    }

    @Transactional
    public PatronModel createPatron(PatronModel patronModel) {
        logger.info("Creating a new patron");
        return patronRepo.save(patronModel);
    }

    @Transactional
    public PatronModel updatePatron(Long id, PatronModel patron) {
        if (!patronRepo.existsById(id)) {
            logger.error(" Patron with id " + id + " does not exist");
            throw new ResourceNotFoundException("Patron not found with id: " + id);
        }
        logger.info("Updating patron with id " + id);
        patron.setId(id);
        return patronRepo.save(patron);
    }

    @Transactional
    public void deletePatron(Long id) {
        if (!patronRepo.existsById(id)) {
            logger.error(" Patron with id " + id + " does not exist");
            throw new ResourceNotFoundException("Patron not found with id: " + id);
        }
        logger.info("Deleting patron with id " + id);
        patronRepo.deleteById(id);
    }

    @Cacheable("patrons")
    public List<PatronModel> getAllPatrons() {
        logger.info("Getting all patrons");
        return patronRepo.findAll();
    }

    @Cacheable(value = "patrons", key = "#id")
    public PatronModel getPatronById(Long id) {
        logger.info("Getting patron with id " + id);
        return patronRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found with id: " + id));
    }


}

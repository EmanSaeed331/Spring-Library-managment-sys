package com.example.demo.controller;

import com.example.demo.model.BorrowModel;
import com.example.demo.model.PatronModel;
import com.example.demo.service.PatronService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatronController.class)
@AutoConfigureMockMvc
public class PatronControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatronService patronService;

    @Test
    public void testGetAllPatrons() throws Exception {
        List<PatronModel> patrons = new ArrayList<>();
        List<BorrowModel> borrowingRecords = new ArrayList<>();

        patrons.add(new PatronModel(1L, "John Doe", "john@example.com", borrowingRecords));
        patrons.add(new PatronModel(2L, "Jane Smith", "jane@example.com", borrowingRecords));

        when(patronService.getAllPatrons()).thenReturn(patrons);

        mockMvc.perform(get("/api/patrons")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPatronById() throws Exception {
        Long patronId = 1L;
        List<BorrowModel> borrowingRecords = new ArrayList<>();

        PatronModel patron = new PatronModel(patronId, "John Doe", "john@example.com", borrowingRecords);

        when(patronService.getPatronById(patronId)).thenReturn(patron);

        mockMvc.perform(get("/api/patrons/{id}", patronId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddPatron() throws Exception {
        List<BorrowModel> borrowingRecords = new ArrayList<>();

        PatronModel patronToAdd = new PatronModel(null, "John Doe", "john@example.com", borrowingRecords);
        PatronModel patronAdded = new PatronModel(1L, "John Doe", "john@example.com", borrowingRecords);

        when(patronService.createPatron(patronToAdd)).thenReturn(patronAdded);

        mockMvc.perform(post("/api/patrons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(patronToAdd)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdatePatron() throws Exception {
        Long patronId = 1L;
        List<BorrowModel> borrowingRecords = new ArrayList<>();

        PatronModel patronToAdd = new PatronModel(patronId, "John Doe", "john@example.com", borrowingRecords);

        PatronModel patronToUpdate = new PatronModel(patronId, "Updated Name", "updated@example.com", borrowingRecords);

        when(patronService.updatePatron(eq(patronId), any(PatronModel.class))).thenReturn(patronToUpdate);


        mockMvc.perform(put("/api/patrons/{id}", patronId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(patronToUpdate)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePatron() throws Exception {
        Long patronId = 1L;

        mockMvc.perform(delete("/api/patrons/{id}", patronId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}

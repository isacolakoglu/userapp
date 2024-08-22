package com.example.userapp.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.userapp.dto.PersonDTO;
import com.example.userapp.repository.PersonRepository;
import com.example.userapp.services.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.util.List;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private PersonService personService;
    @MockBean
    private PersonRepository personRepository;
    @Test
    public void testGetAllPersons() throws Exception {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(1L);
        personDTO.setUsername("admin");
        personDTO.setEmail("admin@example.com");
        personDTO.setPassword("password123");
        personDTO.setFirstname("adminname");
        personDTO.setLastname("adminlastname");
        personDTO.setCity("ankara");
        personDTO.setRole("admin");
        personDTO.setBirthdate(LocalDate.of(1990, 1, 1));

        when(personService.getAll()).thenReturn(List.of(personDTO));

        mockMvc.perform(get("/api/person"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].username").value("admin"))
                .andExpect(jsonPath("$[0].email").value("admin@example.com"))
                .andExpect(jsonPath("$[0].password").value("password123"))
                .andExpect(jsonPath("$[0].firstname").value("adminname"))
                .andExpect(jsonPath("$[0].lastname").value("adminlastname"))
                .andExpect(jsonPath("$[0].city").value("ankara"))
                .andExpect(jsonPath("$[0].role").value("admin"))
                .andExpect(jsonPath("$[0].birthdate").value("1990-01-01"));
    }

    @Test
    public void testGetPersonById() throws Exception {
        Long personId = 1L;
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(personId);
        personDTO.setUsername("admin");
        personDTO.setEmail("admin@example.com");
        personDTO.setPassword("password123");
        personDTO.setFirstname("adminname");
        personDTO.setLastname("adminlastname");
        personDTO.setCity("ankara");
        personDTO.setRole("admin");
        personDTO.setBirthdate(LocalDate.of(1990, 1, 1));

        when(personService.getPersonById(personId)).thenReturn(personDTO);

        mockMvc.perform(get("/api/person/{id}", personId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.username").value("admin"))
                .andExpect(jsonPath("$.email").value("admin@example.com"))
                .andExpect(jsonPath("$.password").value("password123"))
                .andExpect(jsonPath("$.firstname").value("adminname"))
                .andExpect(jsonPath("$.lastname").value("adminlastname"))
                .andExpect(jsonPath("$.city").value("ankara"))
                .andExpect(jsonPath("$.role").value("admin"))
                .andExpect(jsonPath("$.birthdate").value("1990-01-01"));
    }

    @Test
    public void testCreatePerson() throws Exception {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(1L);
        personDTO.setUsername("moderator");
        personDTO.setEmail("moderator@example.com");
        personDTO.setPassword("password123");
        personDTO.setFirstname("moderator");
        personDTO.setLastname("moderator");
        personDTO.setCity("istanbul");
        personDTO.setRole("moderator");
        personDTO.setBirthdate(LocalDate.of(1990, 1, 1));

        when(personService.save(any(PersonDTO.class))).thenReturn(personDTO);

        mockMvc.perform(post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("moderator"))
                .andExpect(jsonPath("$.email").value("moderator@example.com"))
                .andExpect(jsonPath("$.password").value("password123"))
                .andExpect(jsonPath("$.firstname").value("moderator"))
                .andExpect(jsonPath("$.lastname").value("moderator"))
                .andExpect(jsonPath("$.city").value("istanbul"))
                .andExpect(jsonPath("$.role").value("moderator"))
                .andExpect(jsonPath("$.birthdate").value("1990-01-01"));
    }

    @Test
    public void testDeletePersonById() throws Exception {
        Long personId = 1L;
        mockMvc.perform(delete("/api/person/delete/{id}", personId)).andExpect(status().isOk());
    }
}


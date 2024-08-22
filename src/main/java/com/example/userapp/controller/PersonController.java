package com.example.userapp.controller;

import com.example.userapp.dto.PersonDTO;
import com.example.userapp.repository.PersonRepository;
import com.example.userapp.services.PersonService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import com.example.userapp.entity.Person;


@RestController
@RequestMapping("/api/person")
public class PersonController {
    private final PersonService personService;
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PersonController(PersonService personService, PersonRepository personRepository, ModelMapper modelMapper) {
        this.personService = personService;
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAllPersons() {
        List<PersonDTO> persons = personService.getAll();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable("id") Long id){
        PersonDTO personDTO = personService.getPersonById(id);
        return new ResponseEntity<>(personDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO personDTO) {
        PersonDTO savedPerson = personService.save(personDTO);
        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        personService.deleteById(id);
        return ResponseEntity.ok("Kişi Başarıyla Silindi");
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAllPerson() {
        personService.deleteAllPerson();
        return ResponseEntity.status(HttpStatus.OK).body("Tüm Kişiler Başarıyla Silindi.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable Long id, @RequestBody PersonDTO personDTO) {
        try {
            PersonDTO updatedPerson = personService.updatePerson(id, personDTO);
            return ResponseEntity.ok(updatedPerson);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/search")
    public List<PersonDTO> searchPersonByUsername(@RequestParam("username") String username) {
        return personService.findPersonsByUsername(username);
    }

    // typicode/json-server, /posts?start_date=2024-01-01&end_date=2024-01-31
    // Dual PathVariable ile denendi ancak çalıştırılamadı. Tarihlerin değeri sürekli farklı oluyorsa Param kullanılacak demektir.
    @GetMapping("/look")
    public ResponseEntity<List<PersonDTO>> searchPersonByDate(@RequestParam("start_date") String startDate, @RequestParam("end_date") String endDate)
    {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<PersonDTO> persons = personService.findPersonsByBirthdateRange(start, end);
        return ResponseEntity.ok(persons);
    }

}

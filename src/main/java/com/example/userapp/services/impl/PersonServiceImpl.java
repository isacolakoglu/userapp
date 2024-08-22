package com.example.userapp.services.impl;

import com.example.userapp.converter.PersonConverter;
import com.example.userapp.dto.PersonDTO;
import com.example.userapp.entity.Person;
import com.example.userapp.entity.Role;
import com.example.userapp.repository.PersonRepository;
import com.example.userapp.repository.RoleRepository;
import com.example.userapp.services.PersonService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;

    @Override
    public List<PersonDTO> getAll() {
        return personRepository.findAll()
                .stream()
                .map(PersonConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDTO getPersonById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aradığınız" + id + "bulunamadı."));
        return PersonConverter.convertToDTO(person);
    }

    @Override
    public void deleteById(Long id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Silmek istediğiniz" + id + "bulunamadı.");
        }
    }

    @Override
    public void deleteAllPerson(){
        personRepository.deleteAll();
    }

    @Override
    public PersonDTO save(PersonDTO personDTO) {
        Role role = roleRepository.findByRoleName(personDTO.getRole());
        if (role == null) {
            role = new Role();
            role.setRoleName(personDTO.getRole());
            roleRepository.save(role);
        }
        Person person = new Person();
        PersonConverter.convertToEntity(person, personDTO);
        person.setRole(role);
        Person savedPerson = personRepository.save(person);
        personDTO.setId(savedPerson.getId());
        return personDTO;
    }

    @Override
    public PersonDTO updatePerson(Long id, PersonDTO personDTO){
        Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Person not found with id: " + id));
        PersonConverter.convertToEntity(person, personDTO);
        if (personDTO.getRole() != null) {
            Role role = roleRepository.findByRoleName(personDTO.getRole());
            person.setRole(role);
        }
        Person updatedPerson = personRepository.save(person);
        return PersonConverter.convertToDTO(updatedPerson);
    }

    @Override
    public List<PersonDTO> findPersonsByBirthdateRange(LocalDate startDate, LocalDate endDate) {
        List<Person> persons = personRepository.findByBirthdateBetween(startDate, endDate);
        return persons.stream()
                .map(PersonConverter::convertToDTO)
                .toList();
    }

    @Override
    public List<PersonDTO> findPersonsByUsername(String username) {
        List<Person> persons = personRepository.findByUsername(username);
        return persons.stream().map(PersonConverter::convertToDTO).collect(Collectors.toList());
    }


}

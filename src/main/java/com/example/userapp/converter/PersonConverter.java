package com.example.userapp.converter;

import com.example.userapp.dto.PersonDTO;
import com.example.userapp.entity.Person;

public class PersonConverter {
    public static PersonDTO convertToDTO(Person person) {
        PersonDTO dto = new PersonDTO();
        dto.setId(person.getId());
        dto.setUsername(person.getUsername());
        dto.setEmail(person.getEmail());
        dto.setPassword(person.getPassword());
        dto.setFirstname(person.getFirstname());
        dto.setLastname(person.getLastname());
        dto.setCity(person.getCity());
        dto.setRole(person.getRole() != null ? person.getRole().getRoleName() : null);
        dto.setBirthdate(person.getBirthdate());
        return dto;
    }

    public static void convertToEntity(Person person, PersonDTO personDTO){
        person.setUsername(personDTO.getUsername());
        person.setEmail(personDTO.getEmail());
        person.setPassword(personDTO.getPassword());
        person.setFirstname(personDTO.getFirstname());
        person.setLastname(personDTO.getLastname());
        person.setCity(personDTO.getCity());
        person.setBirthdate(personDTO.getBirthdate());
    }
}

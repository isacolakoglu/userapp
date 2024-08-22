package com.example.userapp.services;
import com.example.userapp.dto.PersonDTO;
import com.example.userapp.entity.Person;
import com.example.userapp.entity.Role;
import com.example.userapp.repository.PersonRepository;
import com.example.userapp.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

@Service
public interface PersonService {
    PersonDTO save(PersonDTO personDTO);
    List<PersonDTO> getAll();
    void deleteById(Long id);
    void deleteAllPerson();
    PersonDTO getPersonById(Long id);
    PersonDTO updatePerson(Long id, PersonDTO personDTO);
    List<PersonDTO> findPersonsByBirthdateRange(LocalDate start, LocalDate end);
    List<PersonDTO> findPersonsByUsername(String username);
}

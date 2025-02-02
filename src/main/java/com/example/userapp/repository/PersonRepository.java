package com.example.userapp.repository;
import com.example.userapp.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByBirthdateBetween(LocalDate startDate, LocalDate endDate);
    List<Person> findByUsername (String username);

}

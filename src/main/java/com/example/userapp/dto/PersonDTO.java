package com.example.userapp.dto;

import com.example.userapp.entity.Role;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PersonDTO {

    private Long id;

    private String username;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String city;
    private String role;
    private LocalDate birthdate;
}

package com.example.userapp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor

public class RoleDTO {
    private Long id;
    private String roleName;

    public RoleDTO(Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }
}

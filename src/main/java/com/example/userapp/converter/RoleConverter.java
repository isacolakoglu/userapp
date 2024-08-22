package com.example.userapp.converter;
import com.example.userapp.dto.RoleDTO;
import com.example.userapp.entity.Role;

public class RoleConverter {
    public RoleDTO convertToDTO(Role role) {
        if (role == null) {
            return null;
        }
        return new RoleDTO(role.getId(), role.getRoleName());
    }
}

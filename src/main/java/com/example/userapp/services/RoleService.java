package com.example.userapp.services;

import com.example.userapp.dto.RoleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    List<RoleDTO> getAllRoles();
    void deleteByRoleName(String roleName);
}

package com.example.userapp.controller;

import com.example.userapp.dto.RoleDTO;
import com.example.userapp.entity.Role;
import com.example.userapp.repository.RoleRepository;
import com.example.userapp.services.RoleService;
import com.example.userapp.services.impl.RoleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/roles")
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public List<RoleDTO> getAllRoles() {
        return roleService.getAllRoles();
    }

    @DeleteMapping("/delete/{roleName}")
    public ResponseEntity<String> deleteRoleByName(@PathVariable String roleName){
        roleService.deleteByRoleName(roleName);
        return ResponseEntity.ok(roleName + " rolü başarıyla silindi.");
    }
}

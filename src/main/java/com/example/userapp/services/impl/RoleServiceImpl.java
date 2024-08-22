package com.example.userapp.services.impl;

import com.example.userapp.converter.PersonConverter;
import com.example.userapp.converter.RoleConverter;
import com.example.userapp.dto.RoleDTO;
import com.example.userapp.entity.Role;
import com.example.userapp.repository.RoleRepository;
import com.example.userapp.services.RoleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(role -> new RoleDTO(role.getId(), role.getRoleName())).collect(Collectors.toList());
    }

    @Override
    public void deleteByRoleName(String roleName) {
        String lowerCaseRoleName = roleName.toLowerCase();
        Role role = roleRepository.findByRoleName(lowerCaseRoleName);
        if(role == null){
            throw new EntityNotFoundException("Silmek istediğiniz " + roleName + "rolü bulunamadı.");
        }
        roleRepository.delete(role);
    }
}

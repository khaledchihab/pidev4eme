package com.example.PlateformeMobilite.Services;

import com.example.PlateformeMobilite.Entity.Role;
import com.example.PlateformeMobilite.Interfaces.IRoleService;
import com.example.PlateformeMobilite.Repository.RoleRepository;
import com.example.PlateformeMobilite.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class RoleService implements IRoleService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Override
    public List<Role> retrieveAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role retrieveRole(Long id) {
        return null;
    }

    @Override
    public Role retrieveRole(String id) {
        return null;
    }

    @Override
    public Role addRole(Role r) {
        return roleRepository.save(r);
    }

    @Override
    public Role updateRole(Role r) {
        return null;
    }

    @Override
    public void removeRole(Long id) {
        roleRepository.deleteById(id);

    }

    @Override
    public List<Role> findAllRole() {
        return null;
    }
}

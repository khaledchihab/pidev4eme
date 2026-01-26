package com.example.PlateformeMobilite.Repository;

import com.example.PlateformeMobilite.Entity.ERole;
import com.example.PlateformeMobilite.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
    public Role deleteById(int id);
}

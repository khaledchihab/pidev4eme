package com.example.PlateformeMobilite.Interfaces;

import com.example.PlateformeMobilite.Entity.Role;

import java.util.List;

public interface IRoleService {
    public List<Role> retrieveAllRoles();
    public Role retrieveRole(Long id);

    Role retrieveRole(String id);

    public Role addRole(Role r);
    public Role updateRole(Role r);
    public void removeRole(Long id);
    public List<Role> findAllRole() ;
}

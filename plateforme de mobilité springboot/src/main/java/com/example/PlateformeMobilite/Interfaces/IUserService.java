package com.example.PlateformeMobilite.Interfaces;

import com.example.PlateformeMobilite.Entity.User;

import java.util.List;

public interface IUserService {
    public List<User> retrieveAllUsers();
    public User retrieveUser(Long userID);
    public User addUser(User u);
    public User updateUser(Long id,User u);
    public void removeUser(Long userID);
    public List<Object[]> getUsersAndForms();


}

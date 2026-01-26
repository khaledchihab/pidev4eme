package com.example.PlateformeMobilite.Services;

import com.example.PlateformeMobilite.Entity.User;
import com.example.PlateformeMobilite.Interfaces.IUserService;
import com.example.PlateformeMobilite.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;

    private final PasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User retrieveUser(Long userID) {
        return userRepository.findById(userID).get();
    }

    @Override
    public User addUser(User u) {
        return userRepository.save(u);
    }

    @Override
    public User updateUser(Long id,User u) {
        return userRepository.save(u);
    }

    @Override
    public void removeUser(Long userID) {
        userRepository.deleteById(userID);

    }
    public String getEncodedPassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }
    public List<Object[]> getUsersAndForms() {
        return userRepository.findUsersAndForms();
    }

//    BusRepository Clr;



//    @Override
//    public void affecterUtilisateurClasse(Integer idUtilisateur, Integer codeClasse) {
//        Utilisateur u= Ur.findById(idUtilisateur).orElse(null);
//        Bus c=Clr.findById(codeClasse).orElse(null);
//        u.setClassee(c);
//    }

}

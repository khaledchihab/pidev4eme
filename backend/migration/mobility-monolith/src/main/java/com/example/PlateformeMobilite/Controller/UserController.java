package com.example.PlateformeMobilite.Controller;


import com.example.PlateformeMobilite.Entity.User;
import com.example.PlateformeMobilite.Interfaces.IUserService;
import com.example.PlateformeMobilite.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
//@NoArgsConstructor
@CrossOrigin(allowedHeaders = "*",origins = "*")

@RequestMapping("/Users")
public class UserController {

    private final IUserService iUserService;
    private final UserRepository ur;
    @PostMapping("/addUser")
    public User addUser(@RequestBody User u){
        return iUserService.addUser(u);
    }
    @GetMapping("/retrieve-all-users")
    public List<User> getUsers(){
        List<User> listUsers= iUserService.retrieveAllUsers();
        return listUsers;
    }
//    @GetMapping("/findByName/{username}")
//    public User findByName(@PathVariable("username") String username) throws Exception{
//        return iUserService.findByUsername(username);
//    }
//    @GetMapping("/findByEmail/{email}")
//    public User findByEmail(@PathVariable("email") String email) throws Exception{
//        return iUserService.findByEmail(email)  ;}
@GetMapping("/users/{userId}")
public User retrieveUserById(@PathVariable Long userId) {
    Optional<User> user = ur.findById(userId);
    if (user.isPresent()) {
        return user.get();
    } else {
        // Handle the case where the form with the given ID is not found
        throw new UserController.UserNotFoundException("User with ID " + userId + " not found");
    }
}
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    @ExceptionHandler(UserController.UserNotFoundException.class)
    public ResponseEntity<String> handleFormNotFoundException(UserController.UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @GetMapping("/users/test/{id}")
    public User retrieveUserById1(@PathVariable Long id) {
        return iUserService.retrieveUser(id);
    }
    @GetMapping("/filled-forms")
    public ResponseEntity<List<Object[]>> getUsersAndForms() {
        List<Object[]> usersAndForms = iUserService.getUsersAndForms();
        return ResponseEntity.ok(usersAndForms);
    }

    }

package org.example.authenticationservice.web;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")

@RestController
@RequestMapping("/api/test")

public class TestController {


    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/forCandidat")
    @PreAuthorize("hasAnyRole('STUDENT')")
    public String candidatAccess() {
        return "Candidat Content.";
    }

    @GetMapping("/forUniversity")
    @PreAuthorize("hasAnyRole('UNIVERSITY')")
    public String universityAccess() {
        return "University Board.";
    }

    @GetMapping("/forAdmin")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
    @GetMapping("/forStudent")
    @PreAuthorize("hasAnyRole('VISITEUR')")
    public String studentAccess() {
        return "STUDENT Board.";
    }






}
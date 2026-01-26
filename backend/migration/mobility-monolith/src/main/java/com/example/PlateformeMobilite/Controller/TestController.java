package com.example.PlateformeMobilite.Controller;

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
    @PreAuthorize("hasAuthority('CANDIDAT')")
    public String candidatAccess() {
        return "Candidat Content.";
    }

    @GetMapping("/forUniversity")
    @PreAuthorize("hasAuthority('UNIVERSITY')")
    public String universityAccess() {
        return "University Board.";
    }

    @GetMapping("/forAdmin")
    @PreAuthorize("hasAuthority('ADMININISTRATOR')")
    public String adminAccess() {
        return "Admin Board.";
    }
    @GetMapping("/forStudent")
    @PreAuthorize("hasAuthority('VISITEUR')")
    public String studentAccess() {
        return "STUDENT Board.";
    }






}
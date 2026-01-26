package org.example.authenticationservice.web;

import org.example.authenticationservice.service.AuthenticationService;
import org.example.authenticationservice.dto.LoginRequestDTO;
import org.example.authenticationservice.dto.LoginResponseDTO;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping("/authentication")
public class AuthenticationRestController {

    private final AuthenticationService authenticationService;

    public AuthenticationRestController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public LoginResponseDTO authenticate(@RequestBody LoginRequestDTO requestDTO) {
        return authenticationService.authenticate(requestDTO);
    }
}

package org.example.authenticationservice.service;

import org.example.authenticationservice.dto.LoginRequestDTO;
import org.example.authenticationservice.dto.LoginResponseDTO;

public interface AuthenticationService {

    LoginResponseDTO authenticate(LoginRequestDTO dto);
}

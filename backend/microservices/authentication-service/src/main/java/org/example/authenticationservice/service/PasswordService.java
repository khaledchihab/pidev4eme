package org.example.authenticationservice.service;

import org.example.authenticationservice.dto.ChangePasswordRequestDTO;

public interface PasswordService {

    void requestCodeToResetPassword(String email);

    void resetPassword(ChangePasswordRequestDTO dto);

}

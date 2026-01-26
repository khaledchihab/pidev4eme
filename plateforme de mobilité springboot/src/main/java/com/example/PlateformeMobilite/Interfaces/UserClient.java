package com.example.PlateformeMobilite.Interfaces;

import com.example.PlateformeMobilite.DTO.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "AUTHENTICATION-SERVICE"             // must match spring.application.name in auth service
)
public interface UserClient {

    @GetMapping("/mobility/users/get/{id}")
    UserResponseDTO getUserById(@PathVariable("id") String id);
}
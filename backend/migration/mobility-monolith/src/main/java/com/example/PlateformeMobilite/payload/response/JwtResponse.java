package com.example.PlateformeMobilite.payload.response;

import com.example.PlateformeMobilite.Entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type ="Bearer";
    private Long id;
    private String code;
    private String username;
    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String code, String username, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.code = code;
        this.username = username;
        this.roles = roles;
    }


}

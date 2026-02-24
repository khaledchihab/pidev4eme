package com.example.PlateformeMobilite.Controller;

// ============================================================================
// THIS CONTROLLER IS DISABLED.
// Authentication is handled exclusively by the authentication-service.
// The monolith only validates JWT tokens — it does not issue them.
// ============================================================================

// Keeping imports and class commented out to avoid compilation errors
// since AuthenticationManager and JwtUtils.generateJwtToken() have been removed
// from the monolith's security configuration.

/*
 * import com.example.PlateformeMobilite.Entity.ERole;
 * import com.example.PlateformeMobilite.Entity.Role;
 * import com.example.PlateformeMobilite.Entity.User;
 * import com.example.PlateformeMobilite.Repository.RoleRepository;
 * import com.example.PlateformeMobilite.Repository.UserRepository;
 * import com.example.PlateformeMobilite.Security.Services.UserDetailsImpl;
 * import com.example.PlateformeMobilite.Security.jwt.JwtUtils;
 * import com.example.PlateformeMobilite.payload.request.LoginRequest;
 * import com.example.PlateformeMobilite.payload.request.SignupRequest;
 * import com.example.PlateformeMobilite.payload.response.JwtResponse;
 * import com.example.PlateformeMobilite.payload.response.MessageResponse;
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.ResponseEntity;
 * import org.springframework.security.authentication.AuthenticationManager;
 * import org.springframework.security.authentication.
 * UsernamePasswordAuthenticationToken;
 * import org.springframework.security.core.Authentication;
 * import org.springframework.security.core.context.SecurityContextHolder;
 * import org.springframework.security.crypto.password.PasswordEncoder;
 * import org.springframework.web.bind.annotation.*;
 * 
 * import jakarta.validation.Valid;
 * import java.util.HashSet;
 * import java.util.List;
 * import java.util.Set;
 * import java.util.stream.Collectors;
 * 
 * @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600,
 * allowCredentials = "true")
 * 
 * @RestController
 * 
 * @RequestMapping("/api/auth")
 * public class AuthController {
 * // ... entire controller body disabled ...
 * }
 */

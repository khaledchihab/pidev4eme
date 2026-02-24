package com.example.PlateformeMobilite.Security.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * JWT filter that validates tokens issued by the auth-service.
 * Extracts username and roles directly from the JWT claims —
 * does NOT do a database lookup (monolith DB doesn't have auth-service users).
 */
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                DecodedJWT decodedJWT = jwtUtils.decodeToken(jwt);
                String username = decodedJWT.getSubject();

                // Extract roles directly from JWT claims (NOT from DB)
                List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
                if (roles == null) {
                    roles = Collections.emptyList();
                }

                // Build authorities with ROLE_ prefix for Spring Security
                List<SimpleGrantedAuthority> authorities = roles.stream()
                        .map(role -> {
                            // Avoid double-prefixing: if role already starts with ROLE_, use as-is
                            if (role.startsWith("ROLE_")) {
                                return new SimpleGrantedAuthority(role);
                            }
                            return new SimpleGrantedAuthority("ROLE_" + role);
                        })
                        .toList();

                UserDetails userDetails = new User(username, "", authorities);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
}

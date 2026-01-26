package com.example.PlateformeMobilite.Security.Services;

import com.example.PlateformeMobilite.Entity.User;

import com.example.PlateformeMobilite.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
     UserRepository userRepository;


    @Transactional
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
                //.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }
    @Transactional
    public org.springframework.security.core.userdetails.UserDetails loadUserByEmail(String email)  {
        User user = userRepository.findByEmail(email);

        //.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }
}

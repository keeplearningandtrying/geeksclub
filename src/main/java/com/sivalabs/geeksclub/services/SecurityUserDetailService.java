package com.sivalabs.geeksclub.services;

import com.sivalabs.geeksclub.entities.User;
import com.sivalabs.geeksclub.models.SecurityUser;
import com.sivalabs.geeksclub.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public SecurityUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(username);
        if(!userOptional.isPresent()){
            throw new UsernameNotFoundException("User email "+username+" not found");
        }
        return new SecurityUser(userOptional.get());
    }
}

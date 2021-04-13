package com.wander.corona.tracker.service;

import com.wander.corona.tracker.dto.UserRegistrationDto;
import com.wander.corona.tracker.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}

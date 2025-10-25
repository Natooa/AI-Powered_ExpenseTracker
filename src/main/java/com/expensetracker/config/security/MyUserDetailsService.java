package com.expensetracker.config.security;

import com.expensetracker.features.users.Users;
import com.expensetracker.features.users.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) {
        Users user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " username not found"));

        return new MyUserDetails(user);
    }
}

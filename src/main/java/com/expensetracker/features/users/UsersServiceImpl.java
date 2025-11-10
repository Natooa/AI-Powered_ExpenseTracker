package com.expensetracker.features.users;

import com.expensetracker.config.security.JWTService;
import com.expensetracker.features.users.base.Role;
import com.expensetracker.features.users.base.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    private final RoleRepository roleRepository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository, RoleRepository roleRepository) {
        this.usersRepository = usersRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Users register(Users users) {
        users.setPassword(encoder.encode(users.getPassword()));
        Role rolesUser = roleRepository.findByName("ROLE_USER");
        users.addRole(rolesUser);
        usersRepository.save(users);
        System.out.println(users.getUserRoles());
        return users;
    }

    @Override
    public String updateUsers(Users users) {
        return "test";
    }

    public String verify(Users users) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtService.generateTocken(users.getUsername());
        }
        return "fail";
    }
}

package com.expensetracker.features.users;

import com.expensetracker.features.users.base.Role;
import com.expensetracker.features.users.base.RoleRepository;
import com.expensetracker.features.users.base.UserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

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
}

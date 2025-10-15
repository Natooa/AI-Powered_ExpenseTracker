package com.expensetracker.features.users;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Users register(Users users) {
        users.setPassword(encoder.encode(users.getPassword()));
        return usersRepository.save(users);
    }

    @Override
    public String updateUsers(Users users) {
        return "test";
    }
}

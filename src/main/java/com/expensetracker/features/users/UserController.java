package com.expensetracker.features.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UsersMapper usersMapper;
    private final UsersServiceImpl usersServiceImpl;

    public UserController(UsersMapper usersMapper, UsersServiceImpl usersServiceImpl) {
        this.usersMapper = usersMapper;
        this.usersServiceImpl = usersServiceImpl;
    }

    @PostMapping("/register")
    public ResponseEntity<UsersDTO> createUsers(@RequestBody Users user){
        LOGGER.info("called createUsers");

        UsersDTO usersDTO = usersMapper.usersToUsersDTO(usersServiceImpl.register(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(usersDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users users){
        return usersServiceImpl.verify(users);
    }

}

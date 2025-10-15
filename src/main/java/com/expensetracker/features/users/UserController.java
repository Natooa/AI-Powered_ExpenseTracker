package com.expensetracker.features.users;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class UserController {
    @GetMapping("/welcome")
    public String welcome(){
        return "This is unprotected page";
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('USER')")
    public String pageForUsers() {
        return "This is page for only users";
    }

    @GetMapping("/admins")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String pageForAdmins() {
        return "This is page for only admins";
    }

    @GetMapping("/all")
    public String pageForAll() {
        return "This is page for all employees";
    }
}

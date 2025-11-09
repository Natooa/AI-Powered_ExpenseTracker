package com.expensetracker.features.users.base;

import jakarta.transaction.Transactional;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RoleInitializer {
    private final RoleRepository roleRepository;

    public RoleInitializer(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initRoles(){
        addRoleIfNotExists("ROLE_ADMIN");
        addRoleIfNotExists("ROLE_USER");
    }

    @Transactional
    public void addRoleIfNotExists(String rolesName){
        if(!roleRepository.existsByName(rolesName)) {
            Role role = new Role(rolesName);
            roleRepository.save(role);
        }
    }
}
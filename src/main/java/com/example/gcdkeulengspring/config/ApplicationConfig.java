package com.example.gcdkeulengspring.config;

import com.example.gcdkeulengspring.domain.AppUser;
import com.example.gcdkeulengspring.domain.Role;
import com.example.gcdkeulengspring.repo.RoleRepo;
import com.example.gcdkeulengspring.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ApplicationConfig implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    private final Date date = new Date();


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_USER");



        Role adminRole= roleRepo.findByRoleName("ROLE_ADMIN");
        AppUser appUser = new AppUser();
        appUser.setFirstName("AdminFirstName");
        appUser.setLastName("AdminLastName");
        appUser.setUserName("Admin");
        appUser.setPhoneNumber(23769955512L);
        appUser.setEmail("admin@gmail.com");
        appUser.setPassword("Admin123");
        appUser.setCreatedDate(date);
//        appUser.setRoles(adminRole);
        userRepo.save(appUser);
    }

    @Transactional
    Role createRoleIfNotFound(String role_admin) {
        Role role = roleRepo.findByRoleName(role_admin);
        if (role == null) {
            role = new Role(role_admin);
            roleRepo.save(role);
        }
        return role;
    }
}

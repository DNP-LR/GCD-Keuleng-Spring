package com.example.gcdkeulengspring.config;

import com.example.gcdkeulengspring.domain.AppUser;
import com.example.gcdkeulengspring.domain.Privilege;
import com.example.gcdkeulengspring.domain.Role;
import com.example.gcdkeulengspring.repo.PrivilegeRepo;
import com.example.gcdkeulengspring.repo.RoleRepo;
import com.example.gcdkeulengspring.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class ApplicationConfig implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PrivilegeRepo privilegeRepo;
    private final Date date = new Date();


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        Privilege deletePrivilege = createPrivilegeIfNotFound("DELETE_PRIVILEGE");
        Privilege updatePrivilege = createPrivilegeIfNotFound("UPDATE_PRIVILEGE");
        Privilege dropPrivilege = createPrivilegeIfNotFound("DROP_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege, deletePrivilege, updatePrivilege, dropPrivilege
        );
//        privilegeRepo.save(adminPrivileges);
        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_USER");


        Role adminRole = roleRepo.findByRoleName("ROLE_ADMIN");
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

    Privilege createPrivilegeIfNotFound(String privilegeType) {
        Privilege privilege = privilegeRepo.findByPrivilegeType(privilegeType);
        if (privilege == null) {
            privilege = new Privilege(privilegeType);
            privilegeRepo.save(privilege);
        }
        return privilege;
    }
}

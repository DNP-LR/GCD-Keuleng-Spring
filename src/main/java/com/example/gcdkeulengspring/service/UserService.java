package com.example.gcdkeulengspring.service;

import com.example.gcdkeulengspring.domain.AppUser;
import com.example.gcdkeulengspring.domain.Privilege;
import com.example.gcdkeulengspring.domain.Role;

import java.util.List;

public interface UserService {
    AppUser addUser(AppUser appUser);

    Role saveRole(Role role);

    Privilege savePrivilege(Privilege privilege);

    AppUser getUserById(Long userId);

    List<AppUser> getAllUsers();

    List<Role> getRoles();

    List<Privilege> getPrivilege();

    AppUser updateUser(AppUser appUser);

    void deleteUser(Long userId);

}

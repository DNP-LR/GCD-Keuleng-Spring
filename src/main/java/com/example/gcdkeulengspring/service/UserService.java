package com.example.gcdkeulengspring.service;

import com.example.gcdkeulengspring.domain.AppUser;
import java.util.List;

public interface UserService {
    AppUser addUser(AppUser appUser);

    AppUser getUserById(Long userId);

    List<AppUser> getAllUsers();

    AppUser updateUser(AppUser appUser);

    void deleteUser(Long userId);

}

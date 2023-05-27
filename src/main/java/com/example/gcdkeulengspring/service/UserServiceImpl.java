package com.example.gcdkeulengspring.service;

import com.example.gcdkeulengspring.domain.AppUser;
import com.example.gcdkeulengspring.repo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService  {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    private final Date date = new Date();

    @Override
    public AppUser addUser(AppUser appUser) {
        log.info("Saving new user {} to the database", appUser.getUserName());

        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setCreatedDate(date);
        return userRepo.save(appUser);
    }

    @Override
    public AppUser getUserById(Long userId) {
        Optional<AppUser> optionalUser = userRepo.findById(userId);
        return optionalUser.get();
    }

    @Override
    public List<AppUser> getAllUsers() {
        log.info("User List {} from the database");
        return userRepo.findAll();
    }

    @Override
    public AppUser updateUser(AppUser appUser) {
        log.info("Update user {} in the database", appUser.getUserName());
        AppUser existingAppUser = userRepo.findById(appUser.getId()).get();
        existingAppUser.setFirstName(appUser.getFirstName());
        existingAppUser.setLastName(appUser.getLastName());
        existingAppUser.setEmail(appUser.getEmail());
        AppUser updateAppUser = userRepo.save(existingAppUser);
        return updateAppUser;
    }

    @Override
    public void deleteUser(Long userId) {
        log.info("Delete User {} from the database", userId);
        userRepo.deleteById(userId);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return null;
//    }
}

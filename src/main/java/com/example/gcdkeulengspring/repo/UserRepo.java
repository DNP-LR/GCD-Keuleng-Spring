package com.example.gcdkeulengspring.repo;

import com.example.gcdkeulengspring.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<AppUser, Long> {
//    AppUser findByUserName(String userName);
//
//    AppUser findByEmail(String email);
//
//    AppUser existsByEmail(String email);
//
//    AppUser existsByUserName(String userName);
}

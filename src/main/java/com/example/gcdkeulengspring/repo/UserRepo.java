package com.example.gcdkeulengspring.repo;

import com.example.gcdkeulengspring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}

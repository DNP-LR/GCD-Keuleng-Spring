package com.example.gcdkeulengspring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gcdkeulengspring.domain.Role;

public interface RoleRepo  extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
}

package com.example.gcdkeulengspring.repo;

import com.example.gcdkeulengspring.domain.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepo extends JpaRepository<Privilege, Long> {
    Privilege findByPrivilegeType(String privilegeType);
}

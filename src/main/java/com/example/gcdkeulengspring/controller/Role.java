package com.example.gcdkeulengspring.controller;

import com.example.gcdkeulengspring.domain.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
    private Long id;
    private String roleName;

//    @JsonIgnore
//    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
//    private Collection<AppUser> appUsers;

    public Role(String roleName) {
        super();
        this.roleName = roleName;
    }
}

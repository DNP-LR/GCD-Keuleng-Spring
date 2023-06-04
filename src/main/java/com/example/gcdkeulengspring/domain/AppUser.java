package com.example.gcdkeulengspring.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //    @Column(nullable = false)
    private String firstName;
//    @Column(nullable = false)

    //    @NotNull(message = "First Name cannot be null")
    private String lastName;
    private String userName;

    private Long phoneNumber;
    //    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    private String profileImage;
    private String lastLoginDate;
    private Date createdDate;
    private boolean activated = true;
    private String resetKey = null;
    private Instant resetDate = null;


}
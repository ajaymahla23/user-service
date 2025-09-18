package com.eccomerce.us.model;

import com.eccomerce.us.dto.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class User {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", allocationSize = 1)
    private Long id;
    @Column(unique = true)
    private String userId;
    @Column(length = 40)
    private String name;
    @Column(length = 40)
    private String username;
    @Column(length = 40,unique = true)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void genterateUserId() {
        if (role != null && id != null) {
            this.userId = role.name().charAt(0) + "-" + String.format("%02d", id);
        }
    }
}

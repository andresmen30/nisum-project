package com.nisum.project.infrastructure.entity;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table(name = "User")
@Entity
@Data
@ToString
public class User {

    @Id
    @GeneratedValue
    private Long userId;
    private String name;
    private String email;
    private String password;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private UUID token;
    private boolean isActive;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Phone> phones;


}

package com.sila.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sila.utlis.enums.EnumRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String fullName;
    private String email;
    private String password;
    private EnumRole role;

    @OneToOne(mappedBy = "user")
    private Profile profile;
}

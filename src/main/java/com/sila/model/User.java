package com.sila.model;

import com.sila.utlis.enums.EnumRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String fullName;
    private String email;
    private String password;
    private EnumRole role;
}

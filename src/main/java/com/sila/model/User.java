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
    private Long Id;
    private String fullName;
    private String email;
    private String password;
    private EnumRole role;
    @JsonIgnore()
    @OneToMany(mappedBy = "poster",orphanRemoval = true,cascade = CascadeType.ALL)
    private Set<Post> posts;

}

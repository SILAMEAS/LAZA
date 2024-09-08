package com.sila.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String work;
    private String from;
    private String profile_image;
    private String lived;
    private String cover_image;
    @OneToOne( orphanRemoval = true,
            cascade = CascadeType.ALL)
    private User User;

}
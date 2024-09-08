package com.sila.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String title;
    private String content;
    private String description;
    @ManyToOne()
    private User poster;
    @OneToMany(mappedBy = "post",orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Reaction> reactions=new ArrayList<>();
}

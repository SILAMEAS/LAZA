package com.sila.model;

import com.sila.utlis.enums.EnumReaction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "reactions")
public class Reaction {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private EnumReaction reaction;
    @ManyToOne()
    private Post post;
    @OneToOne( orphanRemoval = true,
            cascade = CascadeType.ALL)
    private User user_reaction;
}

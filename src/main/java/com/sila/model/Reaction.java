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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id",referencedColumnName = "Id")
    private Post post;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_owner_id",referencedColumnName = "Id")
    private User owner;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_react_id",referencedColumnName = "Id")
    private User reactor;
}

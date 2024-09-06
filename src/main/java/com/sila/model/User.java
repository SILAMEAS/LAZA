package com.sila.model;

import com.sila.utlis.enums.USER_ROLE;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String profile;
    private String email;
    private String password;
    private USER_ROLE role = USER_ROLE.ROLE_USER;
    //  @JsonIgnore
//  @OneToMany(cascade = CascadeType.ALL,mappedBy ="customer")
//  private List<Order> orders=new ArrayList<>();
//  @ElementCollection
//  private List<RestaurantFavRes> favourites=new ArrayList<>();
//  @OneToMany(cascade = CascadeType.ALL,orphanRemoval=true,mappedBy ="owner")
//  private  List<Favorite> favourites = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval=true)
    private Set<Address> addresses = new HashSet<>();
}

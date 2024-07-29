package com.oppo.oppo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private String id;

    @Column
    private String fullName;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String phone;

    private String avatarUrl;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Customer customers;

//    @OneToMany(mappedBy = "user" )
//    @JsonIgnore
//    private List<Comments> comments;
//
//    @OneToMany(mappedBy = "user")
//    @JsonIgnore
//    private List<Favorites> favorite;
//
//    @OneToMany(mappedBy = "user")
//    @JsonIgnore
//    private Set<Rates> rates;

    @OneToMany(mappedBy = "user",cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIgnore
    private Set<User_Role> user_roles;
}
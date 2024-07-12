package com.oppo.oppo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private String id;

    @Column
    private String name;

    @Column
    private String phone;

    @Column
    private String email;

    @Column
    private String address;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private Set<Orders> orders;
}

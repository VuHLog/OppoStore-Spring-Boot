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
public class Deliverer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private String id;

    @Column
    private String name;

    @Column
    private String phone;

    @OneToMany(mappedBy = "deliverer")
    @JsonIgnore
    private Set<Orders> orders;
}

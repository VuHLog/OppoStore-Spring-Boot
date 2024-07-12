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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private String id;

    @Column
    private String name;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private Set<MobilePhone> mobilePhones;
}

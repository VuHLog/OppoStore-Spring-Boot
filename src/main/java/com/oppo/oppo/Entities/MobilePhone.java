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
public class MobilePhone {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private String id;

    @Column
    private String name;

    @Column
    private String frontCamera;

    @Column
    private String rearCamera;

    @Column
    private String CPU;

    @Column
    private int battery;

    @Column
    private String screen;

    @Column
    private String resolution;

    @Column
    private int RAM;

    @Column
    private String memoryStick;

    @Column
    private String sim;

    @Column
    private String operatingSystem;

    @Column
    private String size;

    @Column
    private String weight;

    @Column
    private int charger;

    @Column
    private String brand;

    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "mobilePhone",cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH}, orphanRemoval = true)
    @JsonIgnore
    private Set<Variants> variants;
}

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
    private int frontCamera;

    @Column
    private int rearCamera;

    @Column
    private String CPU;

    @Column
    private int battery;

    @Column
    private String screen;

    @Column
    private String resolution;

    @Column
    private String RAM;

    @Column
    private String memoryStickl;

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

    @OneToMany(mappedBy = "mobilePhone")
    @JsonIgnore
    private Set<Variants> variants;

    @OneToMany(mappedBy = "mobilePhone")
    @JsonIgnore
    private Set<OrderDetail> orderDetails;
}

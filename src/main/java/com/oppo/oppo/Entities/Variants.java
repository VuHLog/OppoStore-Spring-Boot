package com.oppo.oppo.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table
public class Variants {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private String id;

    @Column
    private int stock; //so luong ton kho

    @Column
    private int price;

    @Column
    private String image;

    @ManyToOne
    @JoinColumn(name = "mobilePhone_id")
    private MobilePhone mobilePhone;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Colors color;

    @ManyToOne
    @JoinColumn(name = "ROM_id")
    private ROM ROM;
}

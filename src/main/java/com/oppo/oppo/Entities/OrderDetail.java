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
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private String id;

    @Column
    private int quantity;

    @Column
    private int price;

    @ManyToOne
    @JoinColumn(name = "mobilePhone_id")
    private MobilePhone mobilePhone;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;
}

package com.oppo.oppo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private String id;

    @Column
    private Timestamp createdTime;

    @Column(name = "total_price")
    private int totalPrice;

    @Column
    private String note;

    @Column(name = "payment_method")
    private String paymentMethod;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "orders", cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH})
    @JsonIgnore
    private Set<OrderDetail> orderDetails;
}

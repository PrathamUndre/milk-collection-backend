package com.milkcollection.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "milk_collection")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MilkCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "farmer_id", nullable = false)
    private Farmer farmer;

    @Column(nullable = false, length = 20)
    private String date;

    @Column(nullable = false, length = 20)
    private String session;

    @Column(nullable = false, length = 20)
    private String time;

    @Column(name = "milk_type", length = 30)
    private String milkType;

    @Column(nullable = false, precision = 10, scale = 3)
    private BigDecimal quantity;

    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal fat;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal rate;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;
}

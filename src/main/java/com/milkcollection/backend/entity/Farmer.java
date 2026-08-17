package com.milkcollection.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "farmers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Farmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 10)
    private String mobile;

    @OneToMany(mappedBy = "farmer", fetch = FetchType.LAZY)
    @Builder.Default
    private List<MilkCollection> milkCollections = new ArrayList<>();
}

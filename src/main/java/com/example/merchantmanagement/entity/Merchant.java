package com.example.merchantmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@NotNull
@Data
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String shopName;
    @Min(10)
    @Max(10)
    private String phoneNumber;
    private String ownerName;
    @Column(unique = true)
    private String registrationNumber;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}

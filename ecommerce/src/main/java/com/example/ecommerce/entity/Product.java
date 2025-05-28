package com.example.ecommerce.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private String description;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Integer stockQuantity;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public enum Category {
        ELECTRONICS,
        CLOTHING,
        BOOKS,
        HOME_GARDEN,
        SPORTS,
        TOYS,
        BEAUTY,
        AUTOMOTIVE
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
}

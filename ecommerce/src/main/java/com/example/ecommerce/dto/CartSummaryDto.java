package com.example.ecommerce.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartSummaryDto {
    private List<CartItemDto> items;
    private BigDecimal subtotal;
    private BigDecimal discountAmount;
    private BigDecimal finalTotal;
    private String userProfile;
    private Integer discountPercentage;

}

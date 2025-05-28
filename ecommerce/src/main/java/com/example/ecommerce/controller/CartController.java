package com.example.ecommerce.controller;

import com.example.ecommerce.dto.AddToCartRequest;
import com.example.ecommerce.dto.CartSummaryDto;
import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<CartItem> addToCart(@PathVariable Long userId, 
                                            @Valid @RequestBody AddToCartRequest request) {
        try {
            CartItem item = cartService.addToCart(userId, request.getProductId(), request.getQuantity());
            return ResponseEntity.ok(item);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable Long userId,
                                                 @Valid @RequestBody AddToCartRequest request) {
        try {
            CartItem item = cartService.updateCartItem(userId, request.getProductId(), request.getQuantity());
            return ResponseEntity.ok(item);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/remove/{userId}/{productId}")
    public ResponseEntity<?> removeFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        try {
            cartService.removeFromCart(userId, productId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<?> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public List<CartItem> getCartItems(@PathVariable Long userId) {
        return cartService.getCartItems(userId);
    }

    @GetMapping("/summary/{userId}")
    public ResponseEntity<CartSummaryDto> getCartSummary(@PathVariable Long userId) {
        try {
            CartSummaryDto summary = cartService.getCartSummary(userId);
            return ResponseEntity.ok(summary);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}


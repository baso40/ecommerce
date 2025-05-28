package com.example.ecommerce.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String name;
	
	@Email
	@Column(unique = true)
	private String email;
	
	@Enumerated(EnumType.STRING)
	private UserProfile userprofile;
	
	@Column(name ="created_at")
	private LocalDateTime createdAt;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> cartItems = new ArrayList<>();
	
	public enum UserProfile {
	       REGULAR(0),
	       PREMIUM(10),
	       VIP(20),
	       GOLD(30);

	       private final int discountPercentage;

	       UserProfile(int discountPercentage) {
	           this.discountPercentage = discountPercentage;
	       }

	       public int getDiscountPercentage() {
	           return discountPercentage;
	       }
	   }
	 
	 @PrePersist
	   protected void onCreate() {
	       createdAt = LocalDateTime.now();
	   }
}

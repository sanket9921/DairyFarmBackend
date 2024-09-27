package com.dairyfarm.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SemenStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "donor_id", nullable = false)
    private Animal donor;  // Link to donor (bull)

    @Column(nullable = false)
    private Integer quantity;  // Number of doses available

    @Column(nullable = false)
    private LocalDate expiryDate;  // Expiration date of the semen stock

    // Getters and Setters
    
    public SemenStock() {
		// TODO Auto-generated constructor stub
	}

	public SemenStock(Long id, Animal donor, Integer quantity, LocalDate expiryDate) {
		super();
		this.id = id;
		this.donor = donor;
		this.quantity = quantity;
		this.expiryDate = expiryDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Animal getDonor() {
		return donor;
	}

	public void setDonor(Animal donor) {
		this.donor = donor;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
    
    
}

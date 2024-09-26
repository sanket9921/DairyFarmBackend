package com.dairyfarm.models;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Milk_Production")
public class MilkProduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productionId;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = true)
    private Animal animal;  // Nullable for whole farm entries

    @Column(name = "total_quantity", nullable = false)
    private BigDecimal totalQuantity;

    @Column(name = "production_date", nullable = false)
    private Date productionDate;

    private String notes;

    // Getters and Setters
    
    public MilkProduction() {
		// TODO Auto-generated constructor stub
	}

	public MilkProduction(Long productionId, Animal animal, BigDecimal totalQuantity, Date productionDate,
			String notes) {
		super();
		this.productionId = productionId;
		this.animal = animal;
		this.totalQuantity = totalQuantity;
		this.productionDate = productionDate;
		this.notes = notes;
	}

	public Long getProductionId() {
		return productionId;
	}

	public void setProductionId(Long productionId) {
		this.productionId = productionId;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public BigDecimal getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(BigDecimal totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
    
    
}

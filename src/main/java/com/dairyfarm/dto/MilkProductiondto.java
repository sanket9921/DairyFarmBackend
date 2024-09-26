package com.dairyfarm.dto;

import java.math.BigDecimal;
import java.util.Date;

public class MilkProductiondto {
	
	private long animalId;
	private BigDecimal totalQuantity;
	private Date productionDate;
	private String notes;
	
	public MilkProductiondto() {
		// TODO Auto-generated constructor stub
	}

	public MilkProductiondto(long animalId, BigDecimal totalQuantity, Date productionDate, String notes) {
		super();
		this.animalId = animalId;
		this.totalQuantity = totalQuantity;
		this.productionDate = productionDate;
		this.notes = notes;
	}

	public long getAnimalId() {
		return animalId;
	}

	public void setAnimalId(long animalId) {
		this.animalId = animalId;
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

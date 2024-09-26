package com.dairyfarm.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Milk_Quality")
public class MilkQuality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qualityId;

    @ManyToOne
    @JoinColumn(name = "production_id", nullable = false)
    private MilkProduction milkProduction;

    @Column(name = "fat_content")
    private Double fatContent;

    @Column(name = "protein_content")
    private Double proteinContent;

    @Column(name = "somatic_cell_count")
    private Integer somaticCellCount;

    // Getters and Setters
    
    public MilkQuality() {
		// TODO Auto-generated constructor stub
	}

	public MilkQuality(Long qualityId, MilkProduction milkProduction, Double fatContent, Double proteinContent,
			Integer somaticCellCount) {
		super();
		this.qualityId = qualityId;
		this.milkProduction = milkProduction;
		this.fatContent = fatContent;
		this.proteinContent = proteinContent;
		this.somaticCellCount = somaticCellCount;
	}

	public Long getQualityId() {
		return qualityId;
	}

	public void setQualityId(Long qualityId) {
		this.qualityId = qualityId;
	}

	public MilkProduction getMilkProduction() {
		return milkProduction;
	}

	public void setMilkProduction(MilkProduction milkProduction) {
		this.milkProduction = milkProduction;
	}

	public Double getFatContent() {
		return fatContent;
	}

	public void setFatContent(Double fatContent) {
		this.fatContent = fatContent;
	}

	public Double getProteinContent() {
		return proteinContent;
	}

	public void setProteinContent(Double proteinContent) {
		this.proteinContent = proteinContent;
	}

	public Integer getSomaticCellCount() {
		return somaticCellCount;
	}

	public void setSomaticCellCount(Integer somaticCellCount) {
		this.somaticCellCount = somaticCellCount;
	}
    
    
}

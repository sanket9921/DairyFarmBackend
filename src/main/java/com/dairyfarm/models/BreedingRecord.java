package com.dairyfarm.models;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "breeding_records")
public class BreedingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long breedingId;

    @ManyToOne
    @JoinColumn(name = "female_animal_id", nullable = false)
    private Animal femaleAnimal;

    @ManyToOne
    @JoinColumn(name = "male_animal_id", nullable = false)
    private Animal maleAnimal;

    @Column(nullable = false)
    private Date matingDate;

    private Date pregnancyCheckDate;

    @Enumerated(EnumType.STRING)
    private PregnancyStatus pregnancyStatus = PregnancyStatus.UNKNOWN;

    private Date birthDate;
    private Integer offspringCount = 0;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    // Getters and Setters
    public BreedingRecord() {
		// TODO Auto-generated constructor stub
	}
    

    public BreedingRecord(Long breedingId, Animal femaleAnimal, Animal maleAnimal, Date matingDate,
			Date pregnancyCheckDate, PregnancyStatus pregnancyStatus, Date birthDate, Integer offspringCount,
			Timestamp createdAt, Timestamp updatedAt) {
		super();
		this.breedingId = breedingId;
		this.femaleAnimal = femaleAnimal;
		this.maleAnimal = maleAnimal;
		this.matingDate = matingDate;
		this.pregnancyCheckDate = pregnancyCheckDate;
		this.pregnancyStatus = pregnancyStatus;
		this.birthDate = birthDate;
		this.offspringCount = offspringCount;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}


	public Long getBreedingId() {
		return breedingId;
	}


	public void setBreedingId(Long breedingId) {
		this.breedingId = breedingId;
	}


	public Animal getFemaleAnimal() {
		return femaleAnimal;
	}


	public void setFemaleAnimal(Animal femaleAnimal) {
		this.femaleAnimal = femaleAnimal;
	}


	public Animal getMaleAnimal() {
		return maleAnimal;
	}


	public void setMaleAnimal(Animal maleAnimal) {
		this.maleAnimal = maleAnimal;
	}


	public Date getMatingDate() {
		return matingDate;
	}


	public void setMatingDate(Date matingDate) {
		this.matingDate = matingDate;
	}


	public Date getPregnancyCheckDate() {
		return pregnancyCheckDate;
	}


	public void setPregnancyCheckDate(Date pregnancyCheckDate) {
		this.pregnancyCheckDate = pregnancyCheckDate;
	}


	public PregnancyStatus getPregnancyStatus() {
		return pregnancyStatus;
	}


	public void setPregnancyStatus(PregnancyStatus pregnancyStatus) {
		this.pregnancyStatus = pregnancyStatus;
	}


	public Date getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public Integer getOffspringCount() {
		return offspringCount;
	}


	public void setOffspringCount(Integer offspringCount) {
		this.offspringCount = offspringCount;
	}


	public Timestamp getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}


	public Timestamp getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}


	public enum PregnancyStatus {
        PREGNANT, NOT_PREGNANT, UNKNOWN
    }
}

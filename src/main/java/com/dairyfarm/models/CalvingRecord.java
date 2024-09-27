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
public class CalvingRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "breeding_record_id", nullable = false)
	private BreedingRecord breedingRecord; // Link to the corresponding breeding record

	@ManyToOne
	@JoinColumn(name = "calf_id", nullable = false)
	private Animal calf; // The calf born from this calving event

	@Column(nullable = false)
	private LocalDate calvingDate; // Date of calving

	@Column(nullable = true)
	private String complications; // If there were any complications during calving

	@Column(nullable = false)
	private String calfGender; // Male or Female

	@Column(nullable = false)
	private String calfHealthStatus; // Status at birth (Healthy, Sick, etc.)

	public CalvingRecord() {
		// TODO Auto-generated constructor stub
	}

	public CalvingRecord(Long id, BreedingRecord breedingRecord, Animal calf, LocalDate calvingDate,
			String complications, String calfGender, String calfHealthStatus) {
		super();
		this.id = id;
		this.breedingRecord = breedingRecord;
		this.calf = calf;
		this.calvingDate = calvingDate;
		this.complications = complications;
		this.calfGender = calfGender;
		this.calfHealthStatus = calfHealthStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BreedingRecord getBreedingRecord() {
		return breedingRecord;
	}

	public void setBreedingRecord(BreedingRecord breedingRecord) {
		this.breedingRecord = breedingRecord;
	}

	public Animal getCalf() {
		return calf;
	}

	public void setCalf(Animal calf) {
		this.calf = calf;
	}

	public LocalDate getCalvingDate() {
		return calvingDate;
	}

	public void setCalvingDate(LocalDate calvingDate) {
		this.calvingDate = calvingDate;
	}

	public String getComplications() {
		return complications;
	}

	public void setComplications(String complications) {
		this.complications = complications;
	}

	public String getCalfGender() {
		return calfGender;
	}

	public void setCalfGender(String calfGender) {
		this.calfGender = calfGender;
	}

	public String getCalfHealthStatus() {
		return calfHealthStatus;
	}

	public void setCalfHealthStatus(String calfHealthStatus) {
		this.calfHealthStatus = calfHealthStatus;
	}

	// Getters and Setters
}

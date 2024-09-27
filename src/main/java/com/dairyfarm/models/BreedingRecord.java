package com.dairyfarm.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class BreedingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;  // Link to Animal entity

    @Column(nullable = false)
    private LocalDate matingDate;  // Date of mating

    @ManyToOne
    @JoinColumn(name = "sire_id", nullable = true)
    private Animal sire;  // If AI, this can be null

    @Column(nullable = true)
    private String inseminationType; // "Natural" or "AI"

    @Column(nullable = true)
    private Boolean successStatus;  // If mating was successful

    @Column(nullable = true)
    private LocalDate pregnancyConfirmedDate;  // Date when pregnancy is confirmed

    @Column(nullable = true)
    private LocalDate dueDate;  // Automatically calculated when pregnancy is confirmed

    @OneToMany(mappedBy = "breedingRecord")
    private List<CalvingRecord> calvingRecords;  // Linked Calving Record

    // Getters and Setters
    
    public BreedingRecord() {
		// TODO Auto-generated constructor stub
	}
    
    

    public BreedingRecord(Long id, Animal animal, LocalDate matingDate, Animal sire, String inseminationType,
			Boolean successStatus, LocalDate pregnancyConfirmedDate, LocalDate dueDate,
			List<CalvingRecord> calvingRecords) {
		super();
		this.id = id;
		this.animal = animal;
		this.matingDate = matingDate;
		this.sire = sire;
		this.inseminationType = inseminationType;
		this.successStatus = successStatus;
		this.pregnancyConfirmedDate = pregnancyConfirmedDate;
		this.dueDate = dueDate;
		this.calvingRecords = calvingRecords;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Animal getAnimal() {
		return animal;
	}



	public void setAnimal(Animal animal) {
		this.animal = animal;
	}



	public LocalDate getMatingDate() {
		return matingDate;
	}



	public void setMatingDate(LocalDate matingDate) {
		this.matingDate = matingDate;
	}



	public Animal getSire() {
		return sire;
	}



	public void setSire(Animal sire) {
		this.sire = sire;
	}



	public String getInseminationType() {
		return inseminationType;
	}



	public void setInseminationType(String inseminationType) {
		this.inseminationType = inseminationType;
	}



	public Boolean getSuccessStatus() {
		return successStatus;
	}



	public void setSuccessStatus(Boolean successStatus) {
		this.successStatus = successStatus;
	}



	public LocalDate getPregnancyConfirmedDate() {
		return pregnancyConfirmedDate;
	}



	public void setPregnancyConfirmedDate(LocalDate pregnancyConfirmedDate) {
		this.pregnancyConfirmedDate = pregnancyConfirmedDate;
	}



	public LocalDate getDueDate() {
		return dueDate;
	}



	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}



	public List<CalvingRecord> getCalvingRecords() {
		return calvingRecords;
	}



	public void setCalvingRecords(List<CalvingRecord> calvingRecords) {
		this.calvingRecords = calvingRecords;
	}



	// Automatically calculate due date (for pregnancy tracking)
    public void calculateDueDate() {
        if (this.pregnancyConfirmedDate != null) {
            this.dueDate = this.pregnancyConfirmedDate.plusDays(280);  // Gestation period ~280 days
        }
    }
}

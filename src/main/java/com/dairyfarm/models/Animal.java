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
@Table(name = "animals")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Make sure User is set before saving Animal

    @Column(nullable = false, unique = true)
    private String tagNumber;

    @Column(nullable = false)
    private String breed;

    @Column(nullable = false)
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ACTIVE;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    public Animal() {
    }

    public Animal(Long id) {
        this.id = id;  // Only set the ID, without fetching the whole object
    }

    public Animal(Long id, User user, String tagNumber, String breed, Date dateOfBirth, Gender gender, String imageUrl,
			Status status, Timestamp createdAt, Timestamp updatedAt) {
		super();
		this.id = id;
		this.user = user;
		this.tagNumber = tagNumber;
		this.breed = breed;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.imageUrl = imageUrl;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}


	// Other setters/getters
    

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    
    public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTagNumber() {
		return tagNumber;
	}


	public void setTagNumber(String tagNumber) {
		this.tagNumber = tagNumber;
	}


	public String getBreed() {
		return breed;
	}


	public void setBreed(String breed) {
		this.breed = breed;
	}


	public Date getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public Gender getGender() {
		return gender;
	}


	public void setGender(Gender gender) {
		this.gender = gender;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
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


	

    public enum Gender {
        MALE, FEMALE
    }

    public enum Status {
        ACTIVE, INACTIVE, SOLD
    }
}

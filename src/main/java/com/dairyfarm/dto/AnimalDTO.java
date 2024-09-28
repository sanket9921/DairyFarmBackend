package com.dairyfarm.dto;

public class AnimalDTO {
 private Long animalId;
 private String tagNumber;

 public AnimalDTO(Long animalId, String tagNumber) {
     this.animalId = animalId;
     this.tagNumber = tagNumber;
 }

 public Long getAnimalId() {
     return animalId;
 }

 public void setAnimalId(Long animalId) {
     this.animalId = animalId;
 }

 public String getTagNumber() {
     return tagNumber;
 }

 public void setTagNumber(String tagNumber) {
     this.tagNumber = tagNumber;
 }
}

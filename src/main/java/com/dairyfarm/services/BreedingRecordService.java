package com.dairyfarm.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dairyfarm.exception.ResourceNotFoundException;
import com.dairyfarm.models.Animal;
import com.dairyfarm.models.BreedingRecord;
import com.dairyfarm.models.CalvingRecord;
import com.dairyfarm.repositories.AnimalRepository;
import com.dairyfarm.repositories.BreedingRecordRepository;
import com.dairyfarm.repositories.CalvingRecordRepository;
import com.dairyfarm.repositories.SemenStockRepository;

@Service
public class BreedingRecordService {

    @Autowired
    private BreedingRecordRepository breedingRecordRepository;

    @Autowired
    private SemenStockRepository semenStockRepository;

    @Autowired
    private CalvingRecordRepository calvingRecordRepository;
    
    @Autowired
    private AnimalRepository animalRepository;

    // Create a new breeding record (natural or AI)
    public BreedingRecord createBreedingRecord(Long animalId, LocalDate matingDate, Long sireId, String inseminationType) {
        BreedingRecord record = new BreedingRecord();
        record.setAnimal(new Animal(animalId));
        record.setMatingDate(matingDate);

        if (sireId != null) {
            record.setSire(new Animal(sireId));  // Natural mating
        }
        record.setInseminationType(inseminationType);

        return breedingRecordRepository.save(record);
    }

    // Confirm pregnancy and calculate due date
    public BreedingRecord confirmPregnancy(Long breedingRecordId, LocalDate confirmationDate) {
        BreedingRecord record = breedingRecordRepository.findById(breedingRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("BreedingRecord not found"));
        record.setPregnancyConfirmedDate(confirmationDate);
        record.calculateDueDate();  // Automatically set due date based on confirmation

        return breedingRecordRepository.save(record);
    }

    // List breeding records for an animal
    public List<BreedingRecord> getBreedingRecordsForAnimal(Long animalId) {
        return breedingRecordRepository.findByAnimalId(animalId);
    }

    // Get upcoming pregnancies for due date tracking
    public List<BreedingRecord> getUpcomingPregnancies(LocalDate fromDate, LocalDate toDate) {
        return breedingRecordRepository.findByDueDateBetween(fromDate, toDate);
    }

    // Register calving event and link it to breeding record
    public CalvingRecord registerCalving(Long breedingRecordId, LocalDate calvingDate, Long calfId, String calfGender, String healthStatus, String complications) {
        BreedingRecord breedingRecord = breedingRecordRepository.findById(breedingRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("BreedingRecord not found"));

        CalvingRecord calvingRecord = new CalvingRecord();
        calvingRecord.setBreedingRecord(breedingRecord);
        calvingRecord.setCalvingDate(calvingDate);
        calvingRecord.setCalf(new Animal(calfId));
        calvingRecord.setCalfGender(calfGender);
        calvingRecord.setCalfHealthStatus(healthStatus);
        calvingRecord.setComplications(complications);

        return calvingRecordRepository.save(calvingRecord);
    }
}

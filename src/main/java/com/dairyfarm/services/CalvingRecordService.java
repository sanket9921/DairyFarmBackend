package com.dairyfarm.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dairyfarm.exception.ResourceNotFoundException;
import com.dairyfarm.models.Animal;
import com.dairyfarm.models.BreedingRecord;
import com.dairyfarm.models.CalvingRecord;
import com.dairyfarm.repositories.BreedingRecordRepository;
import com.dairyfarm.repositories.CalvingRecordRepository;

@Service
public class CalvingRecordService {

    @Autowired
    private CalvingRecordRepository calvingRecordRepository;

    @Autowired
    private BreedingRecordRepository breedingRecordRepository;

    // Register a new calving event
    public CalvingRecord registerCalving(Long breedingRecordId, LocalDate calvingDate, Long calfId, String calfGender, String healthStatus, String complications) {
        // Fetch the corresponding breeding record
        BreedingRecord breedingRecord = breedingRecordRepository.findById(breedingRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("BreedingRecord not found"));

        // Create the calving record
        CalvingRecord calvingRecord = new CalvingRecord();
        calvingRecord.setBreedingRecord(breedingRecord);
        calvingRecord.setCalvingDate(calvingDate);
        calvingRecord.setCalf(new Animal(calfId));  // Assuming you already handle animal fetching logic
        calvingRecord.setCalfGender(calfGender);
        calvingRecord.setCalfHealthStatus(healthStatus);
        calvingRecord.setComplications(complications);

        // Save the new calving record
        return calvingRecordRepository.save(calvingRecord);
    }

    // Get all calving records for a given breeding record
    public List<CalvingRecord> getCalvingRecordsForBreeding(Long breedingRecordId) {
        return calvingRecordRepository.findByBreedingRecordId(breedingRecordId);
    }
}

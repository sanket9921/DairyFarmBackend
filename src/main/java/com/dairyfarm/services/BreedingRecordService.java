package com.dairyfarm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dairyfarm.models.BreedingRecord;
import com.dairyfarm.repositories.BreedingRecordRepository;

@Service
public class BreedingRecordService {
    @Autowired
    private BreedingRecordRepository breedingRecordRepository;

    public List<BreedingRecord> getBreedingRecordsByFemaleAnimal(Long femaleAnimalId) {
        return breedingRecordRepository.findByFemaleAnimalId(femaleAnimalId);
    }

    public BreedingRecord addBreedingRecord(BreedingRecord breedingRecord) {
        return breedingRecordRepository.save(breedingRecord);
    }

    public void deleteBreedingRecord(Long id) {
        breedingRecordRepository.deleteById(id);
    }
}

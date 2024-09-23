package com.dairyfarm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dairyfarm.models.HealthRecord;
import com.dairyfarm.repositories.HealthRecordRepository;

@Service
public class HealthRecordService {
    @Autowired
    private HealthRecordRepository healthRecordRepository;

    public List<HealthRecord> getHealthRecordsByAnimal(Long animalId) {
        return healthRecordRepository.findByAnimalId(animalId);
    }

    public HealthRecord addHealthRecord(HealthRecord healthRecord) {
        return healthRecordRepository.save(healthRecord);
    }

    public void deleteHealthRecord(Long id) {
        healthRecordRepository.deleteById(id);
    }
}

package com.dairyfarm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dairyfarm.models.HealthRecord;
import com.dairyfarm.services.HealthRecordService;

@RestController
@RequestMapping("/api/health-records")
public class HealthRecordController {
    @Autowired
    private HealthRecordService healthRecordService;

    @GetMapping("/animal/{animalId}")
    public List<HealthRecord> getHealthRecordsByAnimal(@PathVariable Long animalId) {
        return healthRecordService.getHealthRecordsByAnimal(animalId);
    }

    @PostMapping
    public HealthRecord addHealthRecord(@RequestBody HealthRecord healthRecord) {
        return healthRecordService.addHealthRecord(healthRecord);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHealthRecord(@PathVariable Long id) {
        healthRecordService.deleteHealthRecord(id);
        return ResponseEntity.ok().build();
    }
}

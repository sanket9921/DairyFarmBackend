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

import com.dairyfarm.models.BreedingRecord;
import com.dairyfarm.services.BreedingRecordService;

@RestController
@RequestMapping("/api/breeding-records")
public class BreedingRecordController {
    @Autowired
    private BreedingRecordService breedingRecordService;

    @GetMapping("/female-animal/{femaleAnimalId}")
    public List<BreedingRecord> getBreedingRecordsByFemaleAnimal(@PathVariable Long femaleAnimalId) {
        return breedingRecordService.getBreedingRecordsByFemaleAnimal(femaleAnimalId);
    }

    @PostMapping
    public BreedingRecord addBreedingRecord(@RequestBody BreedingRecord breedingRecord) {
        return breedingRecordService.addBreedingRecord(breedingRecord);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBreedingRecord(@PathVariable Long id) {
        breedingRecordService.deleteBreedingRecord(id);
        return ResponseEntity.ok().build();
    }
}

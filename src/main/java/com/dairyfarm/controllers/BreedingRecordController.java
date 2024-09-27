package com.dairyfarm.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dairyfarm.models.BreedingRecord;
import com.dairyfarm.models.CalvingRecord;
import com.dairyfarm.services.BreedingRecordService;
@RestController
@RequestMapping("/api/breeding")
public class BreedingRecordController {

    @Autowired
    private BreedingRecordService breedingRecordService;

    // Create a new breeding record (Natural or AI)
    @PostMapping("/create")
    public ResponseEntity<BreedingRecord> createBreedingRecord(
            @RequestParam Long animalId,
            @RequestParam LocalDate matingDate,
            @RequestParam(required = false) Long sireId,  // Optional for AI
            @RequestParam String inseminationType) {
        BreedingRecord record = breedingRecordService.createBreedingRecord(animalId, matingDate, sireId, inseminationType);
        return ResponseEntity.ok(record);
    }

    // Confirm pregnancy
    @PostMapping("/confirm-pregnancy/{breedingRecordId}")
    public ResponseEntity<BreedingRecord> confirmPregnancy(
            @PathVariable Long breedingRecordId,
            @RequestParam LocalDate confirmationDate) {
        BreedingRecord record = breedingRecordService.confirmPregnancy(breedingRecordId, confirmationDate);
        return ResponseEntity.ok(record);
    }

    // Get breeding records for an animal
    @GetMapping("/history/{animalId}")
    public ResponseEntity<List<BreedingRecord>> getBreedingHistory(@PathVariable Long animalId) {
        List<BreedingRecord> records = breedingRecordService.getBreedingRecordsForAnimal(animalId);
        return ResponseEntity.ok(records);
    }

    // Get upcoming pregnancies (for pregnancy tracking)
    @GetMapping("/upcoming-pregnancies")
    public ResponseEntity<List<BreedingRecord>> getUpcomingPregnancies(
            @RequestParam LocalDate fromDate,
            @RequestParam LocalDate toDate) {
        List<BreedingRecord> records = breedingRecordService.getUpcomingPregnancies(fromDate, toDate);
        return ResponseEntity.ok(records);
    }

    // Register a calving event
    @PostMapping("/register-calving/{breedingRecordId}")
    public ResponseEntity<CalvingRecord> registerCalving(
            @PathVariable Long breedingRecordId,
            @RequestParam LocalDate calvingDate,
            @RequestParam Long calfId,
            @RequestParam String calfGender,
            @RequestParam String healthStatus,
            @RequestParam(required = false) String complications) {
        CalvingRecord calvingRecord = breedingRecordService.registerCalving(breedingRecordId, calvingDate, calfId, calfGender, healthStatus, complications);
        return ResponseEntity.ok(calvingRecord);
    }
}

package com.dairyfarm.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dairyfarm.models.CalvingRecord;
import com.dairyfarm.services.BreedingRecordService;
import com.dairyfarm.services.CalvingRecordService;

@RestController
@RequestMapping("/api/calving-records")
public class CalvingRecordController {

    @Autowired
    private BreedingRecordService breedingRecordService;

    @Autowired
    private CalvingRecordService calvingRecordService;  // You will need to create this service for calving management

    // Register a calving event
    @PostMapping("/register")
    public ResponseEntity<CalvingRecord> registerCalving(@RequestParam Long breedingRecordId,
                                                         @RequestParam LocalDate calvingDate,
                                                         @RequestParam Long calfId,
                                                         @RequestParam String calfGender,
                                                         @RequestParam String healthStatus,
                                                         @RequestParam(required = false) String complications) {
        CalvingRecord calvingRecord = breedingRecordService.registerCalving(breedingRecordId, calvingDate, calfId, calfGender, healthStatus, complications);
        return new ResponseEntity<>(calvingRecord, HttpStatus.CREATED);
    }

    // Get all calving records for a breeding record
    @GetMapping("/breeding-record/{breedingRecordId}")
    public ResponseEntity<List<CalvingRecord>> getCalvingRecordsForBreeding(@PathVariable Long breedingRecordId) {
        List<CalvingRecord> calvingRecords = calvingRecordService.getCalvingRecordsForBreeding(breedingRecordId);
        return new ResponseEntity<>(calvingRecords, HttpStatus.OK);
    }
}

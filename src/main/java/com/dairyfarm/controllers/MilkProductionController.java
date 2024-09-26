package com.dairyfarm.controllers;

import com.dairyfarm.models.MilkProduction;
import com.dairyfarm.services.MilkProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/milk-production")
public class MilkProductionController {

    @Autowired
    private MilkProductionService milkProductionService;

    /**
     * Add milk production for an individual animal.
     *
     * @param animalId       ID of the animal
     * @param totalQuantity  Total quantity of milk
     * @param productionDate Date of milk production
     * @param notes          Additional notes (optional)
     * @return MilkProduction saved record
     */
    @PostMapping("/animal")
    public ResponseEntity<MilkProduction> addMilkProductionForAnimal(
            @RequestParam Long animalId,
            @RequestParam BigDecimal totalQuantity,
            @RequestParam Date productionDate,
            @RequestParam(required = false) String notes) {

        MilkProduction milkProduction = milkProductionService.addMilkProductionForAnimal(animalId, totalQuantity, productionDate, notes);
        return new ResponseEntity<>(milkProduction, HttpStatus.CREATED);
    }

    /**
     * Add milk production for the whole farm (no specific animal).
     *
     * @param totalQuantity  Total quantity of milk
     * @param productionDate Date of milk production
     * @param notes          Additional notes (optional)
     * @return MilkProduction saved record
     */
    @PostMapping("/farm")
    public ResponseEntity<MilkProduction> addMilkProductionForFarm(
            @RequestParam BigDecimal totalQuantity,
            @RequestParam Date productionDate,
            @RequestParam(required = false) String notes) {

        MilkProduction milkProduction = milkProductionService.addMilkProductionForFarm(totalQuantity, productionDate, notes);
        return new ResponseEntity<>(milkProduction, HttpStatus.CREATED);
    }

    /**
     * Update milk production for an individual animal.
     *
     * @param productionId   ID of the milk production record
     * @param animalId       ID of the animal
     * @param totalQuantity  Total quantity of milk
     * @param productionDate Date of milk production
     * @param notes          Additional notes (optional)
     * @return MilkProduction updated record
     */
    @PutMapping("/animal/{id}")
    public ResponseEntity<MilkProduction> updateMilkProductionForAnimal(
            @PathVariable("id") Long productionId,
            @RequestParam Long animalId,
            @RequestParam BigDecimal totalQuantity,
            @RequestParam Date productionDate,
            @RequestParam(required = false) String notes) {

        MilkProduction updatedProduction = milkProductionService.updateMilkProductionForAnimal(productionId, animalId, totalQuantity, productionDate, notes);
        return new ResponseEntity<>(updatedProduction, HttpStatus.OK);
    }

    /**
     * Update milk production for the whole farm.
     *
     * @param productionId   ID of the milk production record
     * @param totalQuantity  Total quantity of milk
     * @param productionDate Date of milk production
     * @param notes          Additional notes (optional)
     * @return MilkProduction updated record
     */
    @PutMapping("/farm/{id}")
    public ResponseEntity<MilkProduction> updateMilkProductionForFarm(
            @PathVariable("id") Long productionId,
            @RequestParam BigDecimal totalQuantity,
            @RequestParam Date productionDate,
            @RequestParam(required = false) String notes) {

        MilkProduction updatedProduction = milkProductionService.updateMilkProductionForFarm(productionId, totalQuantity, productionDate, notes);
        return new ResponseEntity<>(updatedProduction, HttpStatus.OK);
    }
}

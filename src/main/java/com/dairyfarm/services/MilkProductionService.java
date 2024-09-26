package com.dairyfarm.services;

import com.dairyfarm.models.Animal;
import com.dairyfarm.models.MilkProduction;
import com.dairyfarm.repositories.AnimalRepository;
import com.dairyfarm.repositories.MilkProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Service
public class MilkProductionService {

    @Autowired
    private MilkProductionRepository milkProductionRepository;

    @Autowired
    private AnimalRepository animalRepository;

    /**
     * Add milk production for an individual animal.
     *
     * @param animalId       ID of the animal
     * @param totalQuantity  Total quantity of milk
     * @param productionDate Date of milk production
     * @param notes          Additional notes (optional)
     * @return MilkProduction saved record
     */
    public MilkProduction addMilkProductionForAnimal(Long animalId, BigDecimal totalQuantity, Date productionDate, String notes) {
        Optional<Animal> animalOpt = animalRepository.findById(animalId);

        if (animalOpt.isEmpty()) {
            throw new RuntimeException("Animal not found with ID: " + animalId);
        }

        Animal animal = animalOpt.get();
        MilkProduction milkProduction = new MilkProduction();
        milkProduction.setAnimal(animal);
        milkProduction.setTotalQuantity(totalQuantity);
        milkProduction.setProductionDate(productionDate);
        milkProduction.setNotes(notes);

        return milkProductionRepository.save(milkProduction);
    }

    /**
     * Add milk production for the whole farm (no specific animal).
     *
     * @param totalQuantity  Total quantity of milk
     * @param productionDate Date of milk production
     * @param notes          Additional notes (optional)
     * @return MilkProduction saved record
     */
    public MilkProduction addMilkProductionForFarm(BigDecimal totalQuantity, Date productionDate, String notes) {
        MilkProduction milkProduction = new MilkProduction();
        milkProduction.setAnimal(null); // No animal for whole farm production
        milkProduction.setTotalQuantity(totalQuantity);
        milkProduction.setProductionDate(productionDate);
        milkProduction.setNotes(notes);

        return milkProductionRepository.save(milkProduction);
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
    public MilkProduction updateMilkProductionForAnimal(Long productionId, Long animalId, BigDecimal totalQuantity, Date productionDate, String notes) {
        Optional<MilkProduction> productionOpt = milkProductionRepository.findById(productionId);

        if (productionOpt.isEmpty()) {
            throw new RuntimeException("Milk production record not found with ID: " + productionId);
        }

        MilkProduction production = productionOpt.get();

        Optional<Animal> animalOpt = animalRepository.findById(animalId);
        if (animalOpt.isEmpty()) {
            throw new RuntimeException("Animal not found with ID: " + animalId);
        }

        production.setAnimal(animalOpt.get());
        production.setTotalQuantity(totalQuantity);
        production.setProductionDate(productionDate);
        production.setNotes(notes);

        return milkProductionRepository.save(production);
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
    public MilkProduction updateMilkProductionForFarm(Long productionId, BigDecimal totalQuantity, Date productionDate, String notes) {
        Optional<MilkProduction> productionOpt = milkProductionRepository.findById(productionId);

        if (productionOpt.isEmpty()) {
            throw new RuntimeException("Milk production record not found with ID: " + productionId);
        }

        MilkProduction production = productionOpt.get();
        production.setAnimal(null); // Ensure this is a whole farm production
        production.setTotalQuantity(totalQuantity);
        production.setProductionDate(productionDate);
        production.setNotes(notes);

        return milkProductionRepository.save(production);
    }
}

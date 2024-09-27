package com.dairyfarm.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dairyfarm.models.BreedingRecord;

@Repository
public interface BreedingRecordRepository extends JpaRepository<BreedingRecord, Long> {
    List<BreedingRecord> findByAnimalId(Long animalId);  // Find breeding records by animal

    List<BreedingRecord> findBySireId(Long sireId);  // Find breeding records by sire

    List<BreedingRecord> findBySuccessStatus(Boolean successStatus);  // Find successful breedings

    List<BreedingRecord> findByDueDateBetween(LocalDate startDate, LocalDate endDate);  // Find records with due dates within a date range
}

package com.dairyfarm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dairyfarm.models.CalvingRecord;

@Repository
public interface CalvingRecordRepository extends JpaRepository<CalvingRecord, Long> {
    List<CalvingRecord> findByBreedingRecordId(Long breedingRecordId);  // Find calving records by breeding record
}

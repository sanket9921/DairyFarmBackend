package com.dairyfarm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dairyfarm.models.BreedingRecord;

@Repository
public interface BreedingRecordRepository extends JpaRepository<BreedingRecord, Long> {
    List<BreedingRecord> findByFemaleAnimalId(Long femaleAnimalId);
    List<BreedingRecord> findByMaleAnimalId(Long maleAnimalId);
}

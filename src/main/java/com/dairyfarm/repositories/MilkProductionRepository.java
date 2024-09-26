package com.dairyfarm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dairyfarm.models.MilkProduction;

@Repository
public interface MilkProductionRepository extends JpaRepository<MilkProduction, Long> {
	List<MilkProduction> findByAnimal_Id(Long animalId);

    List<MilkProduction> findByAnimalIsNull();
}
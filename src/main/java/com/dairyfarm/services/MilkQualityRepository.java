package com.dairyfarm.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dairyfarm.models.MilkQuality;

@Repository
public interface MilkQualityRepository extends JpaRepository<MilkQuality, Long> {
    // Additional query methods can be defined here if needed
}
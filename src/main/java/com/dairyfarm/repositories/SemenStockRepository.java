package com.dairyfarm.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dairyfarm.models.SemenStock;

@Repository
public interface SemenStockRepository extends JpaRepository<SemenStock, Long> {
    List<SemenStock> findByDonorId(Long donorId);  // Find semen stock by donor (bull)

    List<SemenStock> findByExpiryDateBefore(LocalDate currentDate);  // Find expired or expiring semen stock
}

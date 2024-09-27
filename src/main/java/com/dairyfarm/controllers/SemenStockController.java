package com.dairyfarm.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dairyfarm.models.SemenStock;
import com.dairyfarm.services.SemenStockService;

@RestController
@RequestMapping("/api/semen-stock")
public class SemenStockController {

    @Autowired
    private SemenStockService semenStockService;

    // Add new semen stock
    @PostMapping("/add")
    public ResponseEntity<SemenStock> addSemenStock(@RequestParam Long donorId,
                                                    @RequestParam Integer quantity,
                                                    @RequestParam LocalDate expiryDate) {
        SemenStock stock = semenStockService.addSemenStock(donorId, quantity, expiryDate);
        return new ResponseEntity<>(stock, HttpStatus.CREATED);
    }

    // Use semen stock during an AI attempt
    @PutMapping("/use/{semenStockId}")
    public ResponseEntity<SemenStock> useSemen(@PathVariable Long semenStockId,
                                               @RequestParam int quantityUsed) {
        SemenStock updatedStock = semenStockService.useSemen(semenStockId, quantityUsed);
        return new ResponseEntity<>(updatedStock, HttpStatus.OK);
    }

    // Get expiring or expired semen stock
    @GetMapping("/expiring")
    public ResponseEntity<List<SemenStock>> getExpiringSemen(@RequestParam LocalDate currentDate) {
        List<SemenStock> expiringStock = semenStockService.getExpiringSemen(currentDate);
        return new ResponseEntity<>(expiringStock, HttpStatus.OK);
    }
}

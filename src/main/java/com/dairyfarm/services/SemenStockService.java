package com.dairyfarm.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dairyfarm.exception.ResourceNotFoundException;
import com.dairyfarm.models.Animal;
import com.dairyfarm.models.SemenStock;
import com.dairyfarm.repositories.AnimalRepository;
import com.dairyfarm.repositories.SemenStockRepository;

@Service
public class SemenStockService {

    @Autowired 
    private SemenStockRepository semenStockRepository;
    
    @Autowired
    private AnimalRepository animalRepository;

    // Add new semen stock
    public SemenStock addSemenStock(Long donorId, Integer quantity, LocalDate expiryDate) {
        SemenStock stock = new SemenStock();
        stock.setDonor(new Animal(donorId));
        stock.setQuantity(quantity);
        stock.setExpiryDate(expiryDate);

        return semenStockRepository.save(stock);
    }

    // Reduce semen stock after AI
    public SemenStock useSemen(Long semenStockId, int quantityUsed) {
        SemenStock stock = semenStockRepository.findById(semenStockId)
                .orElseThrow(() -> new ResourceNotFoundException("SemenStock not found"));

        if (stock.getQuantity() < quantityUsed) {
            throw new IllegalArgumentException("Not enough semen stock available");
        }

        stock.setQuantity(stock.getQuantity() - quantityUsed);

        return semenStockRepository.save(stock);
    }

    // Get semen stock expiring soon
    public List<SemenStock> getExpiringSemen(LocalDate currentDate) {
        return semenStockRepository.findByExpiryDateBefore(currentDate);
    }
}

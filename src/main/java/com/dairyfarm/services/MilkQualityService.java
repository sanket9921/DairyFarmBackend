package com.dairyfarm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dairyfarm.exception.ResourceNotFoundException;
import com.dairyfarm.models.MilkQuality;

import java.util.List;
import java.util.Optional;

@Service
public class MilkQualityService {

    @Autowired
    private MilkQualityRepository milkQualityRepository;

    public List<MilkQuality> getAllQualities() {
        return milkQualityRepository.findAll();
    }

    public Optional<MilkQuality> getQualityById(Long id) {
        return milkQualityRepository.findById(id);
    }

    public MilkQuality addQuality(MilkQuality milkQuality) {
        return milkQualityRepository.save(milkQuality);
    }

    public MilkQuality updateQuality(Long id, MilkQuality qualityDetails) {
        MilkQuality quality = milkQualityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Milk quality not found with id " + id));
        // Update fields as necessary
        quality.setFatContent(qualityDetails.getFatContent());
        quality.setProteinContent(qualityDetails.getProteinContent());
        quality.setSomaticCellCount(qualityDetails.getSomaticCellCount());
        return milkQualityRepository.save(quality);
    }

    public void deleteQuality(Long id) {
        milkQualityRepository.deleteById(id);
    }
}

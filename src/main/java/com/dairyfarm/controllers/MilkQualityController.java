package com.dairyfarm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dairyfarm.exception.ResourceNotFoundException;
import com.dairyfarm.models.MilkQuality;
import com.dairyfarm.services.MilkQualityService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*") // Allow all origins (all IPs)
@RequestMapping("/api/milk-qualities")
public class MilkQualityController {

    @Autowired
    private MilkQualityService milkQualityService;

    @GetMapping
    public List<MilkQuality> getAllQualities() {
        return milkQualityService.getAllQualities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MilkQuality> getQualityById(@PathVariable Long id) {
        return milkQualityService.getQualityById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Milk quality not found with id " + id));
    }

    @PostMapping
    public MilkQuality createQuality(@RequestBody MilkQuality milkQuality) {
        return milkQualityService.addQuality(milkQuality);
    }

    @PutMapping("/{id}")
    public MilkQuality updateQuality(@PathVariable Long id, @RequestBody MilkQuality qualityDetails) {
        return milkQualityService.updateQuality(id, qualityDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuality(@PathVariable Long id) {
        milkQualityService.deleteQuality(id);
        return ResponseEntity.noContent().build();
    }
}

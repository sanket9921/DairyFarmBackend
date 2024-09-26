package com.dairyfarm.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dairyfarm.models.Animal;
import com.dairyfarm.services.AnimalService;

@RestController
@CrossOrigin(origins = "*") // Allow all origins (all IPs)
	@RequestMapping("/api/animals")
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    @GetMapping
    public List<Animal> getAllAnimals() {
        return animalService.getAllAnimals();
    }
    
    @GetMapping("/user/{userId}")
    public List<Animal> getAnimalsByUserId(@PathVariable Long userId) {
        return animalService.getAnimalsByUserId(userId);
    }

    @GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable Long id) {
        return animalService.getAnimalById(id);
    }

    @PostMapping(consumes = "multipart/form-data")
    public Animal createAnimal(@RequestPart("animal") Animal animal,
                               @RequestPart("imageFile") MultipartFile imageFile,
                               @RequestParam("userId") Long userId) throws IOException {
        return animalService.createAnimal(animal, imageFile, userId);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Animal> updateAnimal(
            @PathVariable Long id,
            @RequestPart("animal") Animal updatedAnimal, // Part to update animal details
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile // Part for the image file
    ) {
        try {
            Animal updatedAnimalEntity = animalService.updateAnimal(id, updatedAnimal, imageFile);
            return ResponseEntity.ok(updatedAnimalEntity);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null); // Handle any exceptions (like Cloudinary errors)
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnimal(@PathVariable Long id) throws IOException {
        animalService.deleteAnimal(id);
        return ResponseEntity.ok().build();
    }
}

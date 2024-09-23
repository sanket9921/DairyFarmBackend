package com.dairyfarm.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dairyfarm.exception.ResourceNotFoundException;
import com.dairyfarm.models.Animal;
import com.dairyfarm.models.User;
import com.dairyfarm.repositories.AnimalRepository;
import com.dairyfarm.repositories.UserRepository;

import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;




@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;
    
    @Autowired
    private Cloudinary cloudinary;

    @Autowired UserRepository userRepository;
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    public Animal getAnimalById(Long id) {
        return animalRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Animal not found"));
    }

    public Animal createAnimal(Animal animal, MultipartFile imageFile, Long userId)throws IOException {
    	User user = userRepository.findById(userId).get();
    	
    	if (imageFile != null && !imageFile.isEmpty()) {
            // Upload image to Cloudinary
            Map uploadResult = cloudinary.uploader().upload(imageFile.getBytes(),
                    ObjectUtils.emptyMap());
            String imageUrl = (String) uploadResult.get("url");

           // Set image URL in animal object
            animal.setImageUrl(imageUrl);
            animal.setUser(user);
        }
        return animalRepository.save(animal);
    }

    public Animal updateAnimal(Long id, Animal updatedAnimal) {
        Animal animal = getAnimalById(id);
        animal.setBreed(updatedAnimal.getBreed());
        animal.setDateOfBirth(updatedAnimal.getDateOfBirth());
        animal.setGender(updatedAnimal.getGender());
        animal.setStatus(updatedAnimal.getStatus());
        return animalRepository.save(animal);
    }

    public void deleteAnimal(Long id) {
        animalRepository.deleteById(id);
    }
}

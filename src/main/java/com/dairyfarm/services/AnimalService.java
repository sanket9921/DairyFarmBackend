package com.dairyfarm.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    
    public List<Animal> getAnimalsByUserId(Long userId) {
        return animalRepository.findByUserId(userId);
    }
    
    public Animal updateAnimal(Long id, Animal updatedAnimal, MultipartFile newImageFile) throws IOException {
        Animal animal = getAnimalById(id);

        // Update basic animal details
        animal.setBreed(updatedAnimal.getBreed());
        animal.setDateOfBirth(updatedAnimal.getDateOfBirth());
        animal.setGender(updatedAnimal.getGender());
        animal.setStatus(updatedAnimal.getStatus());

        // Check if there's a new image file
        if (newImageFile != null && !newImageFile.isEmpty()) {
            // If the animal already has an image, delete the old one from Cloudinary
            if (animal.getImageUrl() != null && !animal.getImageUrl().isEmpty()) {
                // Extract the public ID of the old image from the URL to delete it
                String publicId = getPublicIdFromUrl(animal.getImageUrl());
                if (publicId != null) {
                    cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
                }
            }

            // Upload the new image to Cloudinary
            Map uploadResult = cloudinary.uploader().upload(newImageFile.getBytes(), ObjectUtils.emptyMap());
            String newImageUrl = (String) uploadResult.get("secure_url");

            // Update the image URL in the animal entity
            animal.setImageUrl(newImageUrl);
        }

        return animalRepository.save(animal);
    }
    public void deleteAnimal(Long id) throws IOException {
        Optional<Animal> animalOpt = animalRepository.findById(id);
        
        if (animalOpt.isPresent()) {
            Animal animal = animalOpt.get(); 
            
            // Delete image from Cloudinary
            if (animal.getImageUrl() != null && !animal.getImageUrl().isEmpty()) {
                // Extract the public ID from the Cloudinary image URL
                String publicId = getPublicIdFromUrl(animal.getImageUrl());
                if (publicId != null) {
                    cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
                }
            }

            // Delete animal from database
            animalRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Animal not found");
        }
    }
    
 // Helper method to extract Cloudinary public ID from image URL
    private String getPublicIdFromUrl(String imageUrl) {
        // Assuming the Cloudinary URL has the format: 
        // https://res.cloudinary.com/{cloud_name}/image/upload/{public_id}.{format}
        String[] urlParts = imageUrl.split("/");
        if (urlParts.length > 0) {
            String publicIdWithFormat = urlParts[urlParts.length - 1];
            return publicIdWithFormat.split("\\.")[0];  // Remove the file extension
        }
        return null;
    }
}

package com.dairyfarm.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dva4vlwsl"); // YOUR_CLOUD_NAME
        config.put("api_key", "718486735492191"); //YOUR_API_KEY
        config.put("api_secret", "IniXqlwiKQNVNzUrw5X2en8ik1k"); //YOUR_API_SECRET
        return new Cloudinary(config);
    }
}

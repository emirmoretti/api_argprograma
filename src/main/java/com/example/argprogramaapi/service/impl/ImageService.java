package com.example.argprogramaapi.service.impl;

import com.example.argprogramaapi.model.Image;
import com.example.argprogramaapi.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private CloudinaryService cloudinaryService;


    public Optional<Image> findById(Long id){
        return imageRepository.findById(id);
    }

    public void save(Image image){
        imageRepository.save(image);
    }

    public Image resultToImage(Map result){
        return new Image(result.get("original_filename").toString(),
                result.get("secure_url").toString(),
                result.get("public_id").toString());
    }

    public Map delete(Long id) throws IOException {
        Image image = findById(id).get();
        Map result = cloudinaryService.delete(image.getImagenId());
        imageRepository.deleteById(id);
        return result;
    }

    public boolean exists(Long id){
        return imageRepository.existsById(id);
    }
}

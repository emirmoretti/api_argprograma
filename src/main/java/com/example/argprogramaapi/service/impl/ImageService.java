package com.example.argprogramaapi.service.impl;

import com.example.argprogramaapi.model.Image;
import com.example.argprogramaapi.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;


    public Optional<Image> findById(Long id){
        return imageRepository.findById(id);
    }

    public void save(Image image){
        imageRepository.save(image);
    }

    public void delete(Long id){
        imageRepository.deleteById(id);
    }

    public boolean exists(Long id){
        return imageRepository.existsById(id);
    }
}

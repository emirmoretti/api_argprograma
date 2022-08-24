package com.example.argprogramaapi.service.impl;

import com.example.argprogramaapi.model.Imagen;
import com.example.argprogramaapi.repository.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ImagenService {
    @Autowired
    ImagenRepository imagenRepository;


    public Optional<Imagen> findById(Long id){
        return imagenRepository.findById(id);
    }

    public void save(Imagen imagen){
        imagenRepository.save(imagen);
    }

    public void delete(Long id){
        imagenRepository.deleteById(id);
    }

    public boolean exists(Long id){
        return imagenRepository.existsById(id);
    }
}

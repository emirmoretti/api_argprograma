package com.example.argprogramaapi.service.impl;

import com.example.argprogramaapi.dto.Message;
import com.example.argprogramaapi.model.Image;
import com.example.argprogramaapi.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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

    public Image save(MultipartFile archivo) throws IOException{
        Map result = cloudinaryService.upload(archivo);
        Image image = resultToImage(result);
        imageRepository.save(image);
        return image;
    }

    public Image resultToImage(Map result){
        return new Image(result.get("original_filename").toString(),
                result.get("secure_url").toString(),
                result.get("public_id").toString());
    }

    public void delete(Long id) throws IOException {
        Image image = findById(id).get();
        if (exists(id)){
            imageRepository.deleteById(id);
        }
        cloudinaryService.delete(image.getImagenId());
    }

    public boolean exists(Long id){
        return imageRepository.existsById(id);
    }

    public void biValidation(MultipartFile archivo) throws IOException {
        BufferedImage bi = ImageIO.read(archivo.getInputStream());
        if (bi == null) {
            throw new IOException("Imagen no valida");
        }
    }
}

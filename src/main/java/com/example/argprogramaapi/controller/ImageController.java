package com.example.argprogramaapi.controller;

import com.example.argprogramaapi.dto.Message;
import com.example.argprogramaapi.model.Image;
import com.example.argprogramaapi.service.impl.CloudinaryService;
import com.example.argprogramaapi.service.impl.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/cloudinary")
@CrossOrigin
public class ImageController {

    @Autowired
    CloudinaryService cloudinaryService;
    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile) throws IOException{
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi == null){
            return new ResponseEntity(new Message("imagen no válida"), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);
        Image image =
                new Image(result.get("original_filename").toString(),
                        result.get("url").toString(),
                        result.get("public_id").toString());
        imageService.save(image);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) throws IOException{
        if(!imageService.exists(id))
            return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
        Image image = imageService.findById(id).get();
        Map result = cloudinaryService.delete(image.getImagenId());
        imageService.delete(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

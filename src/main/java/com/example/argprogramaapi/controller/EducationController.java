package com.example.argprogramaapi.controller;

import com.example.argprogramaapi.dto.Message;
import com.example.argprogramaapi.model.Education;
import com.example.argprogramaapi.model.Image;
import com.example.argprogramaapi.model.Skill;
import com.example.argprogramaapi.service.EducationService;
import com.example.argprogramaapi.service.impl.CloudinaryService;
import com.example.argprogramaapi.service.impl.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/education")
@CrossOrigin(origins = "http://localhost:4200")
public class EducationController {
    @Autowired
    private EducationService educationService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Education> educationList = educationService.getAll();
        if(educationList == null || educationList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista vacia");
        }
        return ResponseEntity.ok().body(educationList);
    }
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Education education){
        return ResponseEntity.status(HttpStatus.CREATED).body(educationService.save(education));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editEducation(@PathVariable Long id, @Valid @RequestBody Education educationNew){
        return ResponseEntity.ok().body(educationService.updateEducation(id, educationNew));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEducation(@PathVariable Long id){
        try {
            educationService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCause());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile archivo, @RequestParam Long id) throws IOException {
        Education educationDb = educationService.findById(id);
        if (educationDb == null) {
            return new ResponseEntity(new Message("No existe esa id"), HttpStatus.BAD_REQUEST);
        }
        BufferedImage bi = ImageIO.read(archivo.getInputStream());
        if(bi == null){
            return new ResponseEntity(new Message("imagen no válida"), HttpStatus.BAD_REQUEST);
        }
        if(educationDb.getImage() != null){
            Long idDb = educationDb.getImage().getId();
            educationDb.setImage(null);
            educationService.save(educationDb);
            imageService.delete(idDb);
        }
        Image image = imageService.save(archivo);
        educationDb.setImage(image);
        educationService.save(educationDb);
        return ResponseEntity.ok().build();
    }

}

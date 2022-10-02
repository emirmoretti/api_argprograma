package com.example.argprogramaapi.controller;

import com.example.argprogramaapi.dto.Message;
import com.example.argprogramaapi.model.Education;
import com.example.argprogramaapi.model.Image;
import com.example.argprogramaapi.service.EducationService;
import com.example.argprogramaapi.service.impl.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/education")
@CrossOrigin(origins = "http://localhost:4200")
public class EducationController {
    @Autowired
    private EducationService educationService;
    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Education> educationList = educationService.getAll();
        if(educationList == null || educationList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista vacia");
        }
        return ResponseEntity.ok().body(educationList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getEducationById(@PathVariable Long id){
        return ResponseEntity.ok().body(educationService.findById(id));
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
      return ResponseEntity.ok(educationService.uploadImage(archivo, id));
    }

}

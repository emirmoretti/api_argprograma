package com.example.argprogramaapi.controller;

import com.example.argprogramaapi.model.Education;
import com.example.argprogramaapi.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/education")
@CrossOrigin(origins = "http://localhost:4200")
public class EducationController {
    @Autowired
    private EducationService educationService;
    @PostMapping("/add/{idProfile}")
    public ResponseEntity<?> create(@PathVariable(name = "idProfile") Long id,@RequestBody Education education){
        try {
            educationService.save(education, id);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
        }
        return ResponseEntity.ok().body(education);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Education> educationList = educationService.getAll();
        if(educationList == null || educationList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(educationList);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editEducation(@PathVariable Long id, @RequestBody Education educationNew){
        return null;
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
}

package com.example.argprogramaapi.controller;

import com.example.argprogramaapi.model.Education;
import com.example.argprogramaapi.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Education> educationList = educationService.getAll();
        if(educationList == null || educationList.isEmpty()){
            return ResponseEntity.notFound().build();
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
}

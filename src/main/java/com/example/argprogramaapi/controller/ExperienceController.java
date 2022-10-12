package com.example.argprogramaapi.controller;

import com.example.argprogramaapi.model.Experience;
import com.example.argprogramaapi.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/experience")
public class ExperienceController {
    @Autowired
    private ExperienceService experienceService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(experienceService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getExperience(@PathVariable Long id){
        return ResponseEntity.ok(experienceService.getById(id));
    }
    @PostMapping
    public ResponseEntity<?> saveExperience(@RequestBody Experience experience){
        return ResponseEntity.ok(experienceService.saveExperience(experience));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateExperience(@PathVariable Long id, @RequestBody Experience experience){
        return ResponseEntity.ok(experienceService.updateExperience(id,experience));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExperience(@PathVariable Long id){
        experienceService.deleteExperience(id);
        return ResponseEntity.ok().build();
    }
}

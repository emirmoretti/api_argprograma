package com.example.argprogramaapi.controller;

import com.example.argprogramaapi.model.Skill;
import com.example.argprogramaapi.repository.SkillRepository;
import com.example.argprogramaapi.service.impl.SkillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {

    @Autowired
    private SkillServiceImpl skillService;

    @GetMapping
    public ResponseEntity<?> getAllSkills(){
        List<Skill> skillList =  skillService.getAll();
        if(skillList == null || skillList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Skills list is empty");
        }
        return ResponseEntity.ok().body(skillList);
    }

    @PostMapping
    public ResponseEntity<?> createSkill(@RequestBody Skill skillRequest){
        return ResponseEntity.ok().body(skillService.save(skillRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editSkill(@PathVariable Long id, @RequestBody Skill  skillRequest){
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSkill(@PathVariable Long id){
        try {
            skillService.deleteById(id);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}

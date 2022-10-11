package com.example.argprogramaapi.controller;
import com.example.argprogramaapi.model.Skill;
import com.example.argprogramaapi.service.impl.SkillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/skills")
@CrossOrigin(origins =  {"http://localhost:4200", "https://emirmoretti-d67f9.firebaseapp.com/"}, maxAge = 3600, allowCredentials="true")
public class SkillController {

    @Autowired
    private SkillServiceImpl skillService;

    @GetMapping
    public ResponseEntity<?> getAllSkills() {
        List<Skill> skillList = skillService.getAll();
        if (skillList == null || skillList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Skills list is empty");
        }
        return ResponseEntity.ok().body(skillList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(skillService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> createSkill(@RequestBody Skill skillRequest) {
        return ResponseEntity.ok().body(skillService.save(skillRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editSkill(@PathVariable Long id, @RequestBody Skill skillRequest) {
        Skill skill = skillService.updateSkill(id, skillRequest);
        return ResponseEntity.ok().body(skill);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSkill(@PathVariable Long id) {
        try {
            skillService.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile archivo, @RequestParam Long id) throws IOException {
        return ResponseEntity.ok(skillService.uploadImage(archivo, id));
    }
    @DeleteMapping("/image/{idSkill}")
    public ResponseEntity<?> deleteImage(@PathVariable Long idSkill) throws IOException {
        skillService.deleteImage(idSkill);
        return ResponseEntity.ok("Imagen eliminada");
    }
}

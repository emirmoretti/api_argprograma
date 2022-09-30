package com.example.argprogramaapi.controller;

import com.example.argprogramaapi.dto.Message;
import com.example.argprogramaapi.model.Image;
import com.example.argprogramaapi.model.Skill;
import com.example.argprogramaapi.service.impl.ImageService;
import com.example.argprogramaapi.service.impl.SkillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/skills")
@CrossOrigin(origins = "http://localhost:4200")
public class SkillController {

    @Autowired
    private SkillServiceImpl skillService;
    @Autowired
    private ImageService imageService;

    @GetMapping
    public ResponseEntity<?> getAllSkills(){
        List<Skill> skillList =  skillService.getAll();
        if(skillList == null || skillList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Skills list is empty");
        }
        return ResponseEntity.ok().body(skillList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(skillService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> createSkill(@RequestBody Skill skillRequest){
        return ResponseEntity.ok().body(skillService.save(skillRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editSkill(@PathVariable Long id, @RequestBody Skill  skillRequest){
        Skill skill = skillService.updateSkill(id, skillRequest);
        return ResponseEntity.ok().body(skill);
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

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile archivo, @RequestParam Long id) throws IOException {
        Skill skillDb = skillService.findById(id);
        if (skillDb == null) {
            return new ResponseEntity(new Message("imagen no válida"), HttpStatus.BAD_REQUEST);
        }
        BufferedImage bi = ImageIO.read(archivo.getInputStream());
        if(bi == null){
            return new ResponseEntity(new Message("imagen no válida"), HttpStatus.BAD_REQUEST);
        }
        if(skillDb.getImage() != null){
            Long idDbImage = skillDb.getImage().getId();
            skillDb.setImage(null);
            skillService.save(skillDb);
            imageService.delete(idDbImage);
        }
        Image image = imageService.save(archivo);
        skillDb.setImage(image);
        skillService.save(skillDb);
        return ResponseEntity.ok().body(skillDb);
    }
}

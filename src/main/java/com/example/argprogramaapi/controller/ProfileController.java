package com.example.argprogramaapi.controller;

import com.example.argprogramaapi.dto.Message;
import com.example.argprogramaapi.model.Image;
import com.example.argprogramaapi.model.Profile;
import com.example.argprogramaapi.model.Skill;
import com.example.argprogramaapi.service.impl.ImageService;
import com.example.argprogramaapi.service.impl.ProfileServiceImpl;
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
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileServiceImpl profileService;

    @Autowired
    private ImageService imageService;

    @GetMapping
    public ResponseEntity<?> getMyProfile(){
        Profile user = profileService.findFirstProfile();
        return ResponseEntity.ok().body(user);
    }
    @PostMapping
    public ResponseEntity<?> createProfile(@RequestBody Profile profile ) throws Exception {
        return ResponseEntity.ok(profileService.create(profile));
    }
    @PutMapping
    public ResponseEntity<?> updateMyProfile(@RequestBody Profile profile){
        Profile profileDb = profileService.update(profile);
        return ResponseEntity.ok().body(profileDb);
    }
    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile archivo) throws IOException {
        Profile profile = profileService.findFirstProfile();
        if (profile == null) {
            return new ResponseEntity(new Message("Perfil invalido"), HttpStatus.BAD_REQUEST);
        }
        BufferedImage bi = ImageIO.read(archivo.getInputStream());
        if(bi == null){
            return new ResponseEntity(new Message("imagen no válida"), HttpStatus.BAD_REQUEST);
        }
        if(profile.getImage() != null){
            Long idDbImage = profile.getImage().getId();
            profile.setImage(null);
            profileService.update(profile);
            imageService.delete(idDbImage);
        }
        Image image = imageService.save(archivo);
        profile.setImage(image);
        profileService.update(profile);
        return ResponseEntity.ok().body(profile);
    }
}

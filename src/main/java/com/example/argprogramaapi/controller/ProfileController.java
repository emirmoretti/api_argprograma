package com.example.argprogramaapi.controller;

import com.example.argprogramaapi.model.Profile;
import com.example.argprogramaapi.service.impl.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileServiceImpl profileService;

    @GetMapping
    public List<Profile> returnAll(){
        return profileService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfile(@PathVariable(name = "id") Long id){
        Profile user = profileService.findById(id);
        return ResponseEntity.ok().body(user);
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Profile profile){
        Profile profileDb = profileService.update(profile);
        return ResponseEntity.ok().body(profileDb);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name="id") Long id, @RequestBody Profile profile){
        Profile profileDb = profileService.update(profile);
        return ResponseEntity.ok().body(profileDb);
    }
}

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
    public ResponseEntity<?> getMyProfile(){
        Profile user = profileService.findFirstProfile();
        return ResponseEntity.ok().body(user);
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Profile profile){
        Profile profileDb = profileService.update(profile);
        return ResponseEntity.ok().body(profileDb);
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Profile profile){
        Profile profileDb = profileService.update(profile);
        return ResponseEntity.ok().body(profileDb);
    }
}

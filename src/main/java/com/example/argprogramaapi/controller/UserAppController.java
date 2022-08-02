package com.example.argprogramaapi.controller;

import com.example.argprogramaapi.model.UserApp;
import com.example.argprogramaapi.service.UserAppService;
import com.example.argprogramaapi.service.impl.UserAppServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserAppController {

    @Autowired
    private UserAppServiceImpl userAppService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable(name = "id") Long id){
        UserApp user = userAppService.findById(id);
        return ResponseEntity.ok().body(user);
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserApp userApp){
        UserApp userDb = userAppService.update(userApp);
        return ResponseEntity.ok().body(userDb);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name="id") Long id, @RequestBody UserApp userApp){
        UserApp userDb = userAppService.update(userApp);
        return ResponseEntity.ok().body(userDb);
    }
}

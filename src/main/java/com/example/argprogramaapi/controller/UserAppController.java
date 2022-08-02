package com.example.argprogramaapi.controller;

import com.example.argprogramaapi.model.UserApp;
import com.example.argprogramaapi.service.UserAppService;
import com.example.argprogramaapi.service.impl.UserAppServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

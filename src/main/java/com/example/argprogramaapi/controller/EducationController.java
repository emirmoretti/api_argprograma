package com.example.argprogramaapi.controller;

import com.example.argprogramaapi.model.Education;
import com.example.argprogramaapi.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/education")
public class EducationController {
    @Autowired
    private EducationService educationService;
    @PostMapping
    public Education create(@RequestParam Education education){
        return educationService.create(education);
    }
}

package com.example.argprogramaapi.controller;

import com.example.argprogramaapi.service.impl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectServiceImpl projectService;

}

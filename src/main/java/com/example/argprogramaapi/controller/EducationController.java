package com.example.argprogramaapi.controller;

import com.example.argprogramaapi.model.Education;
import com.example.argprogramaapi.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/education")
@CrossOrigin(origins = "http://localhost:4200")
public class EducationController {
    @Autowired
    private EducationService educationService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Education> educationList = educationService.getAll();
        if(educationList == null || educationList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(educationList);
    }
    @PostMapping("/add/{idProfile}")
    public ResponseEntity<?> create(@PathVariable(name = "idProfile") Long id, @Valid @RequestBody Education education, BindingResult result){
        Map<String, Object> response = new HashMap<>();
        Education educationNew = null;
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> {
                        return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
                    })
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
           educationNew = educationService.save(education, id);
        } catch (DataAccessException e){
            response.put("mensaje", "Error al crear la educacion");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Educacion ha sido creado con exito");
        response.put("educacion", educationNew);
        return new ResponseEntity<Map>(response, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editEducation(@PathVariable Long id, @Valid @RequestBody Education educationNew, BindingResult result){
        Education educationActual = educationService.findById(id);
        Education educationActualizado = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> {
                        return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
                    })
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if (educationActual == null) {
            response.put("mensaje", "education not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        try {
            educationActual.setGraduationDate(educationNew.getGraduationDate());
            educationActual.setName(educationNew.getName());
            educationActual.setStartDate(educationNew.getStartDate());
            educationActual.setImageUrl(educationNew.getImageUrl());
            educationActualizado = educationService.save(educationActual, educationActual.getProfile().getId());
        } catch (DataAccessException e){
            response.put("mensaje", "Error al crear al cliente");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Educacion ha sido actualizado con exito");
        response.put("educacion", educationActualizado);
        return new ResponseEntity<Map>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEducation(@PathVariable Long id){
        try {
            educationService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCause());
        }
        return ResponseEntity.ok().build();
    }
}

package com.example.argprogramaapi.service;

import com.example.argprogramaapi.model.Skill;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SkillService {

    Skill save(Skill skill);

    List<Skill> getAll();

    Skill findById(Long id);

    Skill updateSkill(Long id, Skill skill);

    void deleteById(Long id) throws IOException;

    Skill uploadImage(MultipartFile archivo, Long id) throws IOException;

    void deleteImage(Long id) throws IOException;

}

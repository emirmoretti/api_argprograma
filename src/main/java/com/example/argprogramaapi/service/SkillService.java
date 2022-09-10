package com.example.argprogramaapi.service;

import com.example.argprogramaapi.model.Skill;

import java.io.IOException;
import java.util.List;

public interface SkillService {

    Skill save(Skill skill);

    List<Skill> getAll();

    Skill findById(Long id);

    Skill updateSkill(Long id, Skill skill);

    void deleteById(Long id) throws IOException;

}

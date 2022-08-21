package com.example.argprogramaapi.service;

import com.example.argprogramaapi.model.Skill;

import java.util.List;

public interface SkillService {

    Skill save(Skill skill);

    List<Skill> getAll();

    Skill getById(Long id);

    void deleteById(Long id);

}

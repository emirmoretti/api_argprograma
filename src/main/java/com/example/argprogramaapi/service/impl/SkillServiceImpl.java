package com.example.argprogramaapi.service.impl;

import com.example.argprogramaapi.model.Profile;
import com.example.argprogramaapi.model.Skill;
import com.example.argprogramaapi.repository.SkillRepository;
import com.example.argprogramaapi.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private ProfileServiceImpl profileService;

    @Autowired
    private ImageService imageService;

    @Override
    public Skill save(Skill skill) {
        Profile profile = profileService.findFirstProfile();
        skill.setProfile(profile);
        return skillRepository.save(skill);
    }

    @Override
    public List<Skill> getAll() {
        return skillRepository.findAll();
    }

    @Override
    public Skill findById(Long id) {
        return skillRepository.findById(id).orElseThrow(() -> new RuntimeException("Skill not found"));
    }

    @Override
    public Skill updateSkill(Long id, Skill skill) {
        Skill skillDb = findById(id);
        if(skillDb == null)
            throw new EntityNotFoundException("skill not found");
        skillDb.setName(skill.getName());
        skillDb.setImage(skill.getImage());
        skillDb.setProgress(skill.getProgress());
        return skillRepository.save(skillDb);
    }

    @Override
    public void deleteById(Long id) throws IOException {
        Skill skilldb = skillRepository.findById(id).get();
        imageService.delete(skilldb.getImage().getId());
        skillRepository.deleteById(id);
    }
}

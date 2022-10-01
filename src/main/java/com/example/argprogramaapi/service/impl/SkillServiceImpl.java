package com.example.argprogramaapi.service.impl;

import com.example.argprogramaapi.model.Image;
import com.example.argprogramaapi.model.Profile;
import com.example.argprogramaapi.model.Project;
import com.example.argprogramaapi.model.Skill;
import com.example.argprogramaapi.repository.SkillRepository;
import com.example.argprogramaapi.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        return skillRepository.save(skillDb);
    }

    @Override
    public void deleteById(Long id) throws IOException {
        Skill skilldb = skillRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Skill not found"));
        if(skilldb.getImage() != null){
            imageService.delete(skilldb.getImage().getId());
        }
        skillRepository.deleteById(id);
    }

    @Override
    public Skill uploadImage(MultipartFile archivo, Long id) throws IOException{
        Skill skillDb = findById(id);
        imageService.biValidation(archivo);
        if (skillDb.getImage() != null) {
            deleteImage(id);
        }
        Image image = imageService.save(archivo);
        skillDb.setImage(image);
        return save(skillDb);
    }

    @Override
    public void deleteImage(Long id) throws IOException{
        Skill skillDb = findById(id);
        Long idDbImage = skillDb.getImage().getId();
        skillDb.setImage(null);
        save(skillDb);
        imageService.delete(idDbImage);
    }
}

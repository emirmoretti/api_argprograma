package com.example.argprogramaapi.util;

import com.example.argprogramaapi.model.ERole;
import com.example.argprogramaapi.model.Education;
import com.example.argprogramaapi.model.Profile;
import com.example.argprogramaapi.model.Role;
import com.example.argprogramaapi.repository.EducationRepository;
import com.example.argprogramaapi.repository.ProfileRepository;
import com.example.argprogramaapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class SeederData {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private EducationRepository educationRepository;
    @Autowired
    private RoleRepository roleRepository;

    @EventListener
    public void eventListener(ContextRefreshedEvent contextRefreshedEvent){
        if(profileRepository.count() < 1){
           // createProfile();
        }
        if(educationRepository.count() < 1){
            createEducation();
        }
        if (roleRepository.count() < 1){
            createRole();
        }
    }

    public void createProfile(){
        Profile profile = new Profile();
        profile.setName("Emir");
        profile.setLastName("Moretti");
        profile.setTitle("Programador Full Stack");
        profileRepository.save(profile);
    }
    public void createEducation(){
        Education education = new Education();
        education.setName("Instituto Superior Politecnico de Cordoba");
        education.setProfile(profileRepository.findFirstByOrderById());
        educationRepository.save(education);
    }

    public void createRole(){
        ERole[] roles = {ERole.ROLE_USER, ERole.ROLE_MODERATOR, ERole.ROLE_ADMIN};
        for (ERole role: roles ) {
            Role newRole = new Role(role);
            roleRepository.save(newRole);
        }
    }
}

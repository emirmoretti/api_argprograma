package com.example.argprogramaapi.util;

import com.example.argprogramaapi.model.*;
import com.example.argprogramaapi.repository.EducationRepository;
import com.example.argprogramaapi.repository.ProfileRepository;
import com.example.argprogramaapi.repository.RoleRepository;
import com.example.argprogramaapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SeederData {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private EducationRepository educationRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserRepository userRepository;

    @EventListener
    public void eventListener(ContextRefreshedEvent contextRefreshedEvent){
        if (roleRepository.count() < 1){
            createRole();
        }
        if (userRepository.count() < 1) {
            createUser();
        }
    }

    public void createUser(){
        User user = new User("emirmoretti",
                "emir.moretti@hotmail.com.ar",
                encoder.encode("emir123456"));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
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

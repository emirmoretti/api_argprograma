package com.example.argprogramaapi.service.impl;

import com.example.argprogramaapi.exception.UserNotFoundException;
import com.example.argprogramaapi.model.Profile;
import com.example.argprogramaapi.repository.ProfileRepository;
import com.example.argprogramaapi.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Profile findFirstProfile() {
        return profileRepository.findFirstByOrderById();
    }

    @Override
    public Profile create(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Profile update(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public List<Profile> getAll() {
        return profileRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        profileRepository.deleteById(id);
    }

}

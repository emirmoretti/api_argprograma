package com.example.argprogramaapi.service;

import com.example.argprogramaapi.model.Profile;

import java.util.List;

public interface ProfileService {

    Profile findById(Long id);

    Profile create(Profile user);

    Profile update(Profile user);

    List<Profile> getAll();

    void delete(Long id);
}

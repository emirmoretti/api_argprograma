package com.example.argprogramaapi.service.impl;

import com.example.argprogramaapi.exception.UserNotFoundException;
import com.example.argprogramaapi.model.UserApp;
import com.example.argprogramaapi.repository.UserAppRepository;
import com.example.argprogramaapi.service.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserAppServiceImpl implements UserAppService {

    @Autowired
    private UserAppRepository userAppRepository;

    @Override
    public UserApp findById(Long id) {
        return userAppRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public UserApp create(UserApp user) {
        return userAppRepository.save(user);
    }

    @Override
    public UserApp update(UserApp user) {
        return userAppRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userAppRepository.deleteById(id);
    }
}

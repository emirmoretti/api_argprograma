package com.example.argprogramaapi.service;

import com.example.argprogramaapi.model.UserApp;

public interface UserAppService {

    UserApp findById(Long id);

    UserApp create(UserApp user);

    UserApp update(UserApp user);

    void delete(Long id);
}

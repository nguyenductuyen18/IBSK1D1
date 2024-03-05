package com.codegym.applicationsandlibraries.service;

import com.codegym.applicationsandlibraries.model.User;
import com.codegym.applicationsandlibraries.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService{
    @Autowired
    private IUserRepository iUserRepository;
    @Override
    public Iterable<User> findAll() {
        return iUserRepository.findAll();
    }

    @Override
    public Optional<User> findById(int id) {
        return iUserRepository.findById(id);
    }

    @Override
    public User save(User user) {
return iUserRepository.save(user);
    }

    @Override
    public void deleteById(int id) {
iUserRepository.deleteById(id);
    }
}

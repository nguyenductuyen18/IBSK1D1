package com.codegym.applicationsandlibraries.service;

import java.util.Optional;

public interface IG<T>{
    Iterable<T> findAll();
    Optional<T> findById(int id);
    T save(T t);
    void deleteById(int id);
}

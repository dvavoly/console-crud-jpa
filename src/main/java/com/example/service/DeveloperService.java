package com.example.service;

import com.example.model.Developer;

import java.util.List;
import java.util.Optional;

public interface DeveloperService {

    Developer saveDeveloper(Developer developer);

    List<Developer> getAllDevelopers();

    Optional<Developer> getDeveloperById(Integer id);

    Developer updateDeveloper(Developer developer);

    void deleteDeveloperById(Integer id);
}

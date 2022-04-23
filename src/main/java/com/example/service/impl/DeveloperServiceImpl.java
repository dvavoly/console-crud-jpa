package com.example.service.impl;

import com.example.model.Developer;
import com.example.repository.DeveloperRepository;
import com.example.service.DeveloperService;

import java.util.List;
import java.util.Optional;

public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository developerRepository;

    public DeveloperServiceImpl(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public Developer saveDeveloper(Developer developer) {
        return developerRepository.save(developer);
    }

    @Override
    public List<Developer> getAllDevelopers() {
        return developerRepository.getAll();
    }

    @Override
    public Optional<Developer> getDeveloperById(Integer id) {
        return developerRepository.getById(id);
    }

    @Override
    public Developer updateDeveloper(Developer developer) {
        return developerRepository.update(developer);
    }

    @Override
    public void deleteDeveloperById(Integer id) {
        developerRepository.deleteById(id);
    }
}

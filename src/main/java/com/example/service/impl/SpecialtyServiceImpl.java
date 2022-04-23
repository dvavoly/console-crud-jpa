package com.example.service.impl;

import com.example.model.Specialty;
import com.example.repository.SpecialtyRepository;
import com.example.service.SpecialtyService;

import java.util.List;
import java.util.Optional;

public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    public SpecialtyServiceImpl(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Specialty saveSpecialty(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public List<Specialty> getAllSpecialties() {
        return specialtyRepository.getAll();
    }

    @Override
    public Optional<Specialty> getSpecialtyById(Integer id) {
        return specialtyRepository.getById(id);
    }

    @Override
    public Specialty updateSpecialty(Specialty specialty) {
        return specialtyRepository.update(specialty);
    }

    @Override
    public void deleteSpecialtyById(Integer id) {
        specialtyRepository.deleteById(id);
    }
}

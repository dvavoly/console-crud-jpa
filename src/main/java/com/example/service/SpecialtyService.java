package com.example.service;

import com.example.model.Specialty;

import java.util.List;
import java.util.Optional;

public interface SpecialtyService {

    Specialty saveSpecialty(Specialty specialty);

    List<Specialty> getAllSpecialties();

    Optional<Specialty> getSpecialtyById(Integer id);

    Specialty updateSpecialty(Specialty specialty);

    void deleteSpecialtyById(Integer id);
}

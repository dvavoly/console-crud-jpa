package com.example.service.impl;

import com.example.model.Specialty;
import com.example.repository.SpecialtyRepository;
import com.example.service.SpecialtyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class SpecialtyServiceImplTest {

    private SpecialtyService specialtyService;
    private SpecialtyRepository repository;
    private Specialty specialty;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(SpecialtyRepository.class);
        specialtyService = new SpecialtyServiceImpl(repository);
        specialty = new Specialty();
        specialty.setId(1);
        specialty.setSpecialtyName("Test Name");
    }

    @DisplayName("JUnit test for saveSpecialty method")
    @Test
    void givenSpecialtyObject_whenSaveSpecialty_thenReturnSpecialtyObject() {
        given(repository.save(specialty)).willReturn(specialty);
        var savedSpecialty = specialtyService.saveSpecialty(specialty);
        assertThat(savedSpecialty).isNotNull();
    }

    @DisplayName("JUnit test for getAllSpecialties method")
    @Test
    void givenSpecialtyList_whenGetAllSpecialties_thanReturnSpecialtyList() {
        Specialty specialty1 = new Specialty();
        specialty1.setId(2);
        specialty1.setSpecialtyName("Test Name Two");

        given(repository.getAll()).willReturn(List.of(specialty, specialty1));
        List<Specialty> specialtyList = specialtyService.getAllSpecialties();
        assertThat(specialtyList).isNotNull();
        assertThat(specialtyList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for getSpecialtyById method")
    @Test
    void givenSpecialtyId_whenGetSpecialtyById_thanReturnSpecialtyObject() {
        given(repository.getById(1)).willReturn(Optional.of(specialty));
        var actual = specialtyService.getSpecialtyById(specialty.getId()).get();
        assertThat(actual).isNotNull();
        assertThat(actual.getSpecialtyName()).isEqualTo("Test Name");
    }

    @DisplayName("JUnit test for updateSpecialty method")
    @Test
    void givenSpecialtyObject_whenUpdateSpecialty_thanReturnSpecialtyObject() {
        given(repository.update(specialty)).willReturn(specialty);
        Specialty actual = specialtyService.updateSpecialty(specialty);
        assertThat(actual.getSpecialtyName()).isEqualTo(specialty.getSpecialtyName());
        assertThat(actual.getId()).isEqualTo(specialty.getId());
    }

    @DisplayName("JUnit test for deleteSpecialtyById method")
    @Test
    void givenSpecialtyId_whenDeleteSpecialtyById_thanNothing() {
        willDoNothing().given(repository).deleteById(specialty.getId());
        specialtyService.deleteSpecialtyById(specialty.getId());
        verify(repository, times(1)).deleteById(specialty.getId());
    }
}
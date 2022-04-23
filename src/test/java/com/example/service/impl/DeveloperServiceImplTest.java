package com.example.service.impl;

import com.example.model.Developer;
import com.example.model.Skill;
import com.example.model.Specialty;
import com.example.model.enums.Status;
import com.example.repository.DeveloperRepository;
import com.example.service.DeveloperService;
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

class DeveloperServiceImplTest {

    private DeveloperRepository repository;
    private DeveloperService developerService;

    Developer developer;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(DeveloperRepository.class);
        developerService = new DeveloperServiceImpl(repository);
        developer = Developer.builder()
                .id(1)
                .firstName("First name")
                .lastName("Last name")
                .skills(List.of(
                        new Skill(1, "Test skill one"),
                        new Skill(2, "Test skill two")))
                .specialty(new Specialty(1, "Test specialty"))
                .status(Status.ACTIVE)
                .build();
    }

    @DisplayName("JUnit test for saveDeveloper method")
    @Test
    void givenDeveloperObject_whenSaveDeveloper_thanReturnDeveloperObject() {
        given(repository.save(developer)).willReturn(developer);
        var actual = developerService.saveDeveloper(developer);
        assertThat(actual).isNotNull();
        assertThat(actual).usingRecursiveComparison().isEqualTo(developer);

    }

    @DisplayName("JUnit test for getAllDevelopers method")
    @Test
    void givenDevelopersList_whenGetAllDevelopers_thanReturnDevelopersList() {
        var developer1 = Developer.builder()
                .id(2)
                .firstName("First name1")
                .lastName("Last name1")
                .skills(List.of(
                        new Skill(1, "Test skill one1"),
                        new Skill(2, "Test skill two1")))
                .specialty(new Specialty(1, "Test specialty1"))
                .status(Status.ACTIVE)
                .build();
        given(repository.getAll()).willReturn(List.of(developer, developer1));
        List<Developer> actual = developerService.getAllDevelopers();
        assertThat(actual).isNotNull();
        assertThat(actual.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for getDeveloperById method")
    @Test
    void givenDeveloperId_whenGetDeveloperById_thanReturnDeveloperObject() {
        given(repository.getById(developer.getId())).willReturn(Optional.of(developer));
        var actual = developerService.getDeveloperById(developer.getId()).get();
        assertThat(actual).isNotNull();
        assertThat(actual).usingRecursiveComparison().isEqualTo(developer);
    }

    @DisplayName("JUnit test for updateDeveloper method")
    @Test
    void givenDeveloperObject_whenUpdateDeveloper_thanReturnDeveloperObject() {
        given(repository.update(developer)).willReturn(developer);
        var actual = developerService.updateDeveloper(developer);
        assertThat(actual).isNotNull();
        assertThat(actual).usingRecursiveComparison().isEqualTo(developer);
    }

    @DisplayName("JUnit test for deleteDeveloperById method")
    @Test
    void givenDeveloperId_whenDeleteDeveloperById_thanNothing() {
        willDoNothing().given(repository).deleteById(developer.getId());
        developerService.deleteDeveloperById(developer.getId());
        verify(repository, times(1)).deleteById(developer.getId());
    }
}
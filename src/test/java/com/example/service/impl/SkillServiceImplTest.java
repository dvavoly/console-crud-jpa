package com.example.service.impl;

import com.example.model.Skill;
import com.example.repository.SkillRepository;
import com.example.service.SkillService;
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

class SkillServiceImplTest {

    private SkillService skillService;
    private SkillRepository repository;
    private Skill skill;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(SkillRepository.class);
        skillService = new SkillServiceImpl(repository);
        skill = new Skill();
        skill.setSkillName("Test name");
        skill.setId(1);
    }

    @DisplayName("JUnit test for saveSkill method")
    @Test
    void givenSkillObject_whenSaveSkill_thanReturnSkillObject() {
        given(repository.save(skill)).willReturn(skill);
        Skill actual = skillService.saveSkill(skill);
        assertThat(actual).isNotNull();
    }

    @DisplayName("JUnit test for getAllSkills method")
    @Test
    void givenSkillList_whenGetAllSkills_thanReturnSkillList() {
        Skill skill1 = new Skill();
        skill1.setId(2);
        skill1.setSkillName("Test name two");
        given(repository.getAll()).willReturn(List.of(skill, skill1));
        List<Skill> actual = skillService.getAllSkills();
        assertThat(actual).isNotNull();
        assertThat(actual.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for getSkillById method")
    @Test
    void givenSkillId_whenGetSkillById_thanReturnSkillObject() {
        given(repository.getById(skill.getId())).willReturn(Optional.of(skill));
        Skill actual = skillService.getSkillById(skill.getId()).get();
        assertThat(actual).isNotNull();
        assertThat(actual.getSkillName()).isEqualTo("Test name");
    }

    @DisplayName("JUnit test for updateSkill method")
    @Test
    void givenSkillObject_whenUpdateSkill_thanReturnSkillObject() {
        given(repository.update(skill)).willReturn(skill);
        Skill actual = skillService.updateSkill(skill);
        assertThat(actual).isNotNull();
        assertThat(actual.getSkillName()).isEqualTo("Test name");
    }

    @DisplayName("JUnit test for deleteSkillById method")
    @Test
    void givenSkillId_whenDeleteSkillById_thanNothing() {
        willDoNothing().given(repository).deleteById(skill.getId());
        skillService.deleteSkillById(skill.getId());
        verify(repository, times(1)).deleteById(skill.getId());
    }
}
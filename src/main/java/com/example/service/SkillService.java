package com.example.service;

import com.example.model.Skill;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;
import java.util.Optional;

public interface SkillService {
    Skill saveSkill(Skill skill);

    List<Skill> getAllSkills();

    Optional<Skill> getSkillById(Integer id);

    Skill updateSkill(Skill skill);

    void deleteSkillById(Integer id);
}

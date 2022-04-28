package com.example.service.impl;

import com.example.model.Skill;
import com.example.repository.SkillRepository;
import com.example.service.SkillService;

import java.util.List;
import java.util.Optional;

public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public Skill saveSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.getAll();
    }

    @Override
    public Optional<Skill> getSkillById(Integer id) {
        return skillRepository.getById(id);
    }

    @Override
    public Skill updateSkill(Skill skill) {
        return skillRepository.update(skill);
    }

    @Override
    public void deleteSkillById(Integer id) {
        skillRepository.deleteById(id);
    }
}

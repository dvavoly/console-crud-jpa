package com.example.repository.jpa;

import com.example.model.Skill;
import com.example.repository.SkillRepository;
import com.example.utils.JpaUtil;
import com.example.utils.Messages;

import java.util.List;
import java.util.Optional;

public class JpaSkillRepositoryImpl implements SkillRepository {
    @Override
    public Optional<Skill> getById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException(Messages.ID_CANNOT_BE_NULL.toString());
        }
        var entityManager = JpaUtil.getEntityManager();
        entityManager.getTransaction().begin();
        var skill = entityManager.find(Skill.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(skill);
    }

    @Override
    public List<Skill> getAll() {
        var entityManager = JpaUtil.getEntityManager();
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var query = criteriaBuilder.createQuery(Skill.class);
        var skillRoot = query.from(Skill.class);
        var all = query.select(skillRoot);

        var allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public Skill save(Skill skill) {
        if (skill == null || skill.getSkillName().isEmpty()) {
            throw new IllegalArgumentException(Messages.SKILL_CANNOT_BE_NULL_OR_EMPTY.toString());
        }
        var entityManager = JpaUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(skill);
        entityManager.getTransaction().commit();
        entityManager.close();
        return skill;
    }

    @Override
    public Skill update(Skill skill) {
        if (skill == null || skill.getSkillName().isEmpty()) {
            throw new IllegalArgumentException(Messages.SKILL_CANNOT_BE_NULL_OR_EMPTY.toString());
        }
        if (skill.getId() == null) {
            throw new IllegalArgumentException(Messages.ID_CANNOT_BE_NULL.toString());
        }
        var entityManager = JpaUtil.getEntityManager();
        entityManager.getTransaction().begin();
        var result = entityManager.find(Skill.class, skill.getId());
        result.setSkillName(skill.getSkillName());
        entityManager.persist(result);
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    @Override
    public void deleteById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException(Messages.ID_CANNOT_BE_NULL.toString());
        }
        var entityManager = JpaUtil.getEntityManager();
        entityManager.getTransaction().begin();
        var skill = entityManager.find(Skill.class, id);
        entityManager.remove(skill);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

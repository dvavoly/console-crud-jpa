package com.example.repository.impl;

import com.example.model.Skill;
import com.example.repository.SkillRepository;
import com.example.util.HibernateUtil;
import com.example.util.Messages;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class JpaSkillRepositoryImpl implements SkillRepository {
    @Override
    public Optional<Skill> getById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException(Messages.ID_CANNOT_BE_NULL.toString());
        }
        var session = HibernateUtil.openSession();
        var skill = session.find(Skill.class, id);
        return Optional.ofNullable(skill);
    }

    @Override
    public List<Skill> getAll() {
        var session = HibernateUtil.openSession();
        var criteriaBuilder = session.getCriteriaBuilder();
        var query = criteriaBuilder.createQuery(Skill.class);
        var skillRoot = query.from(Skill.class);
        var all = query.select(skillRoot);

        var allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public Skill save(Skill skill) {
        if (skill == null || skill.getSkillName().isEmpty()) {
            throw new IllegalArgumentException(Messages.SKILL_CANNOT_BE_NULL_OR_EMPTY.toString());
        }
        var session = HibernateUtil.openSession();
        var transaction = session.beginTransaction();
        session.persist(skill);
        transaction.commit();
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
        var session = HibernateUtil.openSession();
        var transaction = session.beginTransaction();
        var result = session.find(Skill.class, skill.getId());
        result.setSkillName(skill.getSkillName());
        session.persist(result);
        transaction.commit();
        return result;
    }

    @Override
    public void deleteById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException(Messages.ID_CANNOT_BE_NULL.toString());
        }
        var session = HibernateUtil.openSession();
        var transaction = session.beginTransaction();
        var skill = session.find(Skill.class, id);
        session.remove(skill);
        transaction.commit();
    }
}

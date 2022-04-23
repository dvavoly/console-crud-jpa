package com.example.repository.jpa;

import com.example.model.Developer;
import com.example.model.Skill;
import com.example.model.Specialty;
import com.example.model.enums.Status;
import com.example.repository.DeveloperRepository;
import com.example.utils.JpaUtil;
import com.example.utils.Messages;

import java.util.List;
import java.util.Optional;

public class JpaDeveloperRepositoryImpl implements DeveloperRepository {
    @Override
    public Optional<Developer> getById(Integer id) {
        var entityManager = JpaUtil.getEntityManager();
        entityManager.getTransaction().begin();
        var developer = entityManager.find(Developer.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(developer);
    }

    @Override
    public List<Developer> getAll() {
        var entityManager = JpaUtil.getEntityManager();
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var query = criteriaBuilder.createQuery(Developer.class);
        var developerRoot = query.from(Developer.class);
        var all = query.select(developerRoot);

        var allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public Developer save(Developer developer) {
        if (developer == null) {
            throw new IllegalArgumentException(Messages.ID_CANNOT_BE_NULL.toString());
        }
        var entityManager = JpaUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(developer);
        entityManager.getTransaction().commit();
        entityManager.close();
        return developer;
    }

    @Override
    public Developer update(Developer developer) {
        if (developer.getId() == null) {
            throw new IllegalArgumentException(Messages.ID_CANNOT_BE_NULL.toString());
        }
        var entityManager = JpaUtil.getEntityManager();
        entityManager.getTransaction().begin();
        var result = entityManager.find(Developer.class, developer.getId());
        result.setFirstName(developer.getFirstName());
        result.setLastName(developer.getLastName());
        result.setSkills(developer.getSkills());
        result.setSpecialty(developer.getSpecialty());
        entityManager.persist(result);
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    @Override
    public void deleteById(Integer id) {
        var entityManager = JpaUtil.getEntityManager();
        entityManager.getTransaction().begin();
        var developer = entityManager.find(Developer.class, id);
        developer.setStatus(Status.DELETED);
        entityManager.persist(developer);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

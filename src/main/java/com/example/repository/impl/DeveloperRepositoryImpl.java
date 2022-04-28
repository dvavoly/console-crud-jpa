package com.example.repository.impl;

import com.example.model.Developer;
import com.example.model.enums.Status;
import com.example.repository.DeveloperRepository;
import com.example.util.HibernateUtil;
import com.example.util.Messages;

import java.util.List;
import java.util.Optional;

public class DeveloperRepositoryImpl implements DeveloperRepository {
    @Override
    public Optional<Developer> getById(Integer id) {
        var session = HibernateUtil.openSession();
        var developer = session.get(Developer.class, id);
        return Optional.ofNullable(developer);
    }

    @Override
    public List<Developer> getAll() {
        var session = HibernateUtil.openSession();
        var criteriaBuilder = session.getCriteriaBuilder();
        var query = criteriaBuilder.createQuery(Developer.class);
        var developerRoot = query.from(Developer.class);
        var all = query.select(developerRoot);

        var allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public Developer save(Developer developer) {
        if (developer == null) {
            throw new IllegalArgumentException(Messages.ID_CANNOT_BE_NULL.toString());
        }
        var session = HibernateUtil.openSession();
        session.getTransaction().begin();
        session.persist(developer);
        session.getTransaction().commit();
        return developer;
    }

    @Override
    public Developer update(Developer developer) {
        if (developer.getId() == null) {
            throw new IllegalArgumentException(Messages.ID_CANNOT_BE_NULL.toString());
        }
        var session = HibernateUtil.openSession();
        session.getTransaction().begin();
        var result = session.find(Developer.class, developer.getId());
        result.setFirstName(developer.getFirstName());
        result.setLastName(developer.getLastName());
        result.setSkills(developer.getSkills());
        result.setSpecialty(developer.getSpecialty());
        session.persist(result);
        session.getTransaction().commit();
        return result;
    }

    @Override
    public void deleteById(Integer id) {
        var session = HibernateUtil.openSession();
        var transaction = session.beginTransaction();
        var developer = session.find(Developer.class, id);
        developer.setStatus(Status.DELETED);
        session.persist(developer);
        transaction.commit();
    }
}

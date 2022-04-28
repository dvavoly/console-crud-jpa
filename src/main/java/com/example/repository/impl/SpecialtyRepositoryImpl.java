package com.example.repository.impl;

import com.example.model.Specialty;
import com.example.repository.SpecialtyRepository;
import com.example.util.HibernateUtil;
import com.example.util.Messages;

import java.util.List;
import java.util.Optional;

public class SpecialtyRepositoryImpl implements SpecialtyRepository {
    @Override
    public Optional<Specialty> getById(Integer id) {

        if (id == null) {
            throw new IllegalArgumentException(Messages.ID_CANNOT_BE_NULL.toString());
        }

        var session = HibernateUtil.openSession();
        var specialty = session.find(Specialty.class, id);
        return Optional.ofNullable(specialty);
    }

    @Override
    public List<Specialty> getAll() {
        var session = HibernateUtil.openSession();
        var criteriaBuilder = session.getCriteriaBuilder();
        var query = criteriaBuilder.createQuery(Specialty.class);
        var rootEntry = query.from(Specialty.class);
        var all = query.select(rootEntry);

        var allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public Specialty save(Specialty specialty) {
        if (specialty == null || specialty.getSpecialtyName().isEmpty()) {
            throw new IllegalArgumentException(Messages.SPECIALTY_CANNOT_BE_NULL_OR_EMPTY.toString());
        }
        var session = HibernateUtil.openSession();
        var transaction = session.getTransaction();
        session.persist(specialty);
        transaction.commit();
        return specialty;
    }

    @Override
    public Specialty update(Specialty specialty) {
        if (specialty == null || specialty.getSpecialtyName().isEmpty()) {
            throw new IllegalArgumentException(Messages.SPECIALTY_CANNOT_BE_NULL_OR_EMPTY.toString());
        }
        if (specialty.getId() == null) {
            throw new IllegalArgumentException(Messages.ID_CANNOT_BE_NULL.toString());
        }
        var session = HibernateUtil.openSession();
        var transaction = session.beginTransaction();
        Specialty result = session.find(Specialty.class, specialty.getId());
        result.setSpecialtyName(specialty.getSpecialtyName());
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
        var specialty = session.find(Specialty.class, id);
        session.remove(specialty);
        transaction.commit();
    }
}

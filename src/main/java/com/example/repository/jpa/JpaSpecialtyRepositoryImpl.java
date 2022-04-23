package com.example.repository.jpa;

import com.example.model.Specialty;
import com.example.repository.SpecialtyRepository;
import com.example.utils.JpaUtil;
import com.example.utils.Messages;

import java.util.List;
import java.util.Optional;

public class JpaSpecialtyRepositoryImpl implements SpecialtyRepository {
    @Override
    public Optional<Specialty> getById(Integer id) {

        if (id == null) {
            throw new IllegalArgumentException(Messages.ID_CANNOT_BE_NULL.toString());
        }

        var entityManager = JpaUtil.getEntityManager();
        entityManager.getTransaction().begin();
        var specialty = entityManager.find(Specialty.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(specialty);
    }

    @Override
    public List<Specialty> getAll() {
        var entityManager = JpaUtil.getEntityManager();
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var query = criteriaBuilder.createQuery(Specialty.class);
        var rootEntry = query.from(Specialty.class);
        var all = query.select(rootEntry);

        var allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public Specialty save(Specialty specialty) {
        if (specialty == null || specialty.getSpecialtyName().isEmpty()) {
            throw new IllegalArgumentException(Messages.SPECIALTY_CANNOT_BE_NULL_OR_EMPTY.toString());
        }
        var entityManager = JpaUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(specialty);
        entityManager.getTransaction().commit();
        entityManager.close();
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
        var entityManager = JpaUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Specialty result = entityManager.find(Specialty.class, specialty.getId());
        result.setSpecialtyName(specialty.getSpecialtyName());
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
        Specialty specialty = entityManager.find(Specialty.class, id);
        entityManager.remove(specialty);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

package com.example.repository;

import com.example.model.Developer;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T, Id> {
    Optional<T> getById(Id id);

    List<T> getAll();

    T save(T t);

    T update(T t);

    void deleteById(Id id);
}

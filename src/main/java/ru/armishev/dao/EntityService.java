package ru.armishev.dao;

import ru.armishev.entity.stuff.Waiter;

import java.util.List;
import java.util.Optional;

public interface EntityService<T> {
    List<T> getList();

    Optional<T> getById(Integer integer);

    int add(T waiter);

    T update(T waiter);

    void delete(Integer integer);
}

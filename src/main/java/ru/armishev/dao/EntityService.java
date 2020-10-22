package ru.armishev.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.armishev.entity.stuff.Waiter;

import java.util.List;
import java.util.Optional;

public interface EntityService<T> {
    List<T> getList();

    Optional<T> getById(Integer integer);

    int add(T element);

    T update(T element);

    void delete(Integer id);

    Gson getPublicGson();
}

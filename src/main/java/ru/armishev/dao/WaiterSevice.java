package ru.armishev.dao;

import ru.armishev.entity.stuff.Waiter;

import java.util.List;
import java.util.Optional;

public interface WaiterSevice {
    List<Waiter> getList();

    Optional<Waiter> getById(Integer integer);
}

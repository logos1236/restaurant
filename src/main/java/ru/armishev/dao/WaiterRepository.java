package ru.armishev.dao;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.armishev.entity.stuff.Waiter;

import java.util.List;
import java.util.Optional;

public interface WaiterRepository extends JpaRepository<Waiter, Long> {
    @Query(value="SELECT * FROM stuff WHERE type = 2", nativeQuery = true)
    List<Waiter> findAll();
}

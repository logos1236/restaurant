package ru.armishev.dao.rest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armishev.entity.stuff.Waiter;

import java.util.List;
import java.util.Optional;

@Service
public interface WaiterRepository extends JpaRepository<Waiter, Integer> {
    //@Query(value="SELECT * FROM #{#entityName} WHERE type = 2", nativeQuery = true)
    @Query(value="SELECT * FROM stuff WHERE type = 2", nativeQuery = true)
    List<Waiter> findAll();

    @Query(value="SELECT * FROM stuff WHERE type = 2 and id = ?1", nativeQuery = true)
    Optional<Waiter> findById(Integer id);

    //SELECT LAST_INSERT_ID();
    @Modifying
    @Query(value = "INSERT INTO stuff (name, type) VALUES (?#{#waiter.getName()},2)", nativeQuery = true)
    void add(@Param("waiter") Waiter waiter);

    @Query(value = "SELECT MAX(id) FROM stuff;", nativeQuery = true)
    int getMaxId();

    @Modifying
    @Query(value = "DELETE FROM stuff WHERE type = 2 AND id = ?1", nativeQuery = true)
    @Transactional
    void deleteById(Integer integer);

    @Modifying
    @Query(value = "UPDATE stuff SET name = ?#{#waiter.getName()} WHERE id = ?#{#waiter.getId()}", nativeQuery = true)
    @Transactional
    void update(@Param("waiter") Waiter waiter);
}

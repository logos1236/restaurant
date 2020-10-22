package ru.armishev.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armishev.entity.product.Product;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value="SELECT * FROM product", nativeQuery = true)
    List<Product> findAll();

    @Query(value="SELECT * FROM #{#entityName} WHERE id = ?1", nativeQuery = true)
    Optional<Product> findById(Integer id);

    @Modifying
    @Query(value = "INSERT INTO #{#entityName} (name, cost, description, category, execute_time) " +
            "VALUES (?#{#product.getName()}, ?#{#product.getCost()}, ?#{#product.getDescription()}, ?#{#product.getCategory()}, ?#{#product.getExecuteTime()})", nativeQuery = true)
    void add(@Param("product") Product product);

    @Query(value = "SELECT MAX(id) FROM #{#entityName};", nativeQuery = true)
    int getMaxId();

    @Modifying
    @Query(value = "DELETE FROM #{#entityName} WHERE id = ?1", nativeQuery = true)
    @Transactional
    void deleteById(Integer integer);

    @Modifying
    @Query(value = "UPDATE #{#entityName} SET name = ?#{#product.getName()}, cost = ?#{#product.getCost()}, description = ?#{#product.getDescription()}, category = ?#{#product.getCategory()}, execute_time = ?#{#product.getExecuteTime()} WHERE id = ?#{#product.getId()}", nativeQuery = true)
    @Transactional
    void update(@Param("product") Product product);
}

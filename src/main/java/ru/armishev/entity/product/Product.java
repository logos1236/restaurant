package ru.armishev.entity.product;

import org.hibernate.annotations.GenericGenerator;
import ru.armishev.entity.ingredient.Ingredient;

import javax.persistence.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product implements IProduct {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private long cost;

    @Column(name = "description")
    private String description;

    public void setExecute_time(long execute_time) {
        this.execute_time = execute_time;
    }

    public long getExecute_time() {
        return execute_time;
    }

    @Transient
    private ProductCategory category;

    @Column(name = "execute_time")
    private long execute_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public long getExecuteTime() {
        return execute_time;
    }

    public void setExecuteTime(long execute_time) {
        this.execute_time = execute_time;
    }


    @Override
    public long getCost() {
        return cost;
    }

    @Override
    public ProductCategory geCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                ", execute_time=" + execute_time +
                '}';
    }

    @Override
    public String getDescription() {
        return description;
    }

}

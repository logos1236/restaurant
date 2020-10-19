package ru.armishev.entity.product;

import ru.armishev.entity.ingredient.Ingredient;

import java.util.List;

public class Product implements IProduct {
    int id;
    long cost;
    String description;
    ProductCategory category;
    List<Ingredient> supplies;
    long execute_time;

    @Override
    public long getCost() {
        return cost;
    }

    @Override
    public ProductCategory geCategory() {
        return category;
    }

    @Override
    public List<Ingredient> getSupplies() {
        return supplies;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public long getExecuteTime() {
        return execute_time;
    }
}

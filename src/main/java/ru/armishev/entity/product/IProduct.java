package ru.armishev.entity.product;

import ru.armishev.entity.ingredient.Ingredient;

import java.util.List;

public interface IProduct {
    long getCost();

    ProductCategory geCategory();

    String getDescription();

    long getExecuteTime();
}

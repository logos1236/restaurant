package ru.armishev.entity.product;

public class ProductCategory implements IProductCategory {
    private int id;
    private String name;
    private String discription;
    private ProductCategory parent;

    @Override
    public ProductCategory getParent() {
        return null;
    }
}

package ru.armishev.entity.order;

import ru.armishev.entity.product.Product;

import java.util.Map;

public interface IOrderCustomer {
    public void setProduct(Map<Product,Integer> products);
    public void cancel();
    public void pay();
}

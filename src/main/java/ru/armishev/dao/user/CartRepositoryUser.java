package ru.armishev.dao.user;

import java.util.List;

public interface CartRepositoryUser {
    List<CartDAOUser.CartProduct> getProducts();

    List<CartDAOUser.CartProduct> setProducts(String jsonProducts);

    CartDAOUser.CartProduct getProduct(int product_id) throws Exception;
}

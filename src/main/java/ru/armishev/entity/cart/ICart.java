package ru.armishev.entity.cart;

import ru.armishev.entity.table.Table;
import java.util.ArrayList;

public interface ICart {
    ArrayList<Cart.CartProduct> getProducts();
    void setProduct(Cart.CartProduct product);
    Cart.CartProduct getProduct(Integer product_id) throws Exception;

    Table getTable();
    void setTable(Table table);
}

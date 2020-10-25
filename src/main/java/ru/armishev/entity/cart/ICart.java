package ru.armishev.entity.cart;

import com.sun.tools.javac.util.List;
import ru.armishev.entity.product.Product;
import ru.armishev.entity.table.Table;

import java.util.ArrayList;
import java.util.Map;

public interface ICart {
    ArrayList<Cart.CartProduct> getProducts();
    void setProduct(Cart.CartProduct product);
    Cart.CartProduct getProduct(Integer product_id) throws Exception;

    Table getTable();
    void setTable(Table table);
}

package ru.armishev.entity.cart;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.armishev.entity.table.Table;

import java.util.ArrayList;
import java.util.Objects;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart implements ICart {
    private ArrayList<CartProduct> products = new ArrayList();
    private Table table;
    private long cost;

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public void calculateCost() {

    }

    @Override
    public ArrayList<CartProduct> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "products=" + products +
                '}';
    }

    @Override
    public void setProduct(CartProduct product) {
        if (product == null) {
            return;
        }

        if (products.contains(product)) {
            products.remove(product);
        }

        if (product.quantity > 0) {
            products.add(product);
        }
    }

    @Override
    public Cart.CartProduct getProduct(Integer product_id) {
        for(CartProduct product: products) {
            if (product.id == product_id) {
                return product;
            }
        }

        return null;
    }

    public static class CartProduct {
        private int id;
        private int quantity;

        public CartProduct(int id, int count) {
            this.id = id;
            this.quantity = count;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CartProduct that = (CartProduct) o;
            return id == that.id;
        }

        @Override
        public String toString() {
            return "CartProduct{" +
                    "id=" + id +
                    ", quantity=" + quantity +
                    '}';
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
}

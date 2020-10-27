package ru.armishev.dao.user;

import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.armishev.entity.product.Product;
import ru.armishev.helper.Helper;

import java.util.*;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartDAOUser implements CartRepositoryUser {
    @Autowired
    private ProductDAOUser product_dao;

    private List<CartProduct> products = new ArrayList();

    public long getCost() {
        long result = 0;

        if (!products.isEmpty()) {
            for(CartProduct cartProduct:products) {
                result += cartProduct.getCost();
            }
        }

        return result;
    }

    @Override
    public List<CartProduct> getProducts() {
        return products;
    }

    @Override
    public List<CartProduct> setProducts(String jsonProducts) {
        JsonParser parser = new JsonParser();
        JsonArray product_list = parser.parse(jsonProducts).getAsJsonArray();

        for(JsonElement elem:product_list) {
            JsonObject product = elem.getAsJsonObject();
            int product_id = product.get("id").getAsInt();
            int product_quantity = product.get("quantity").getAsInt();

            if (product_id <= 0) {
                throw new IllegalArgumentException("product_id не может быть отрицательным");
            }

            CartProduct already_product_in_cart = getProduct(product_id);
            List<Integer> ids_product_for_add = new ArrayList();

            if (product_quantity > 0) {
                //Добавляем товарную позицию
                if (already_product_in_cart != null) {
                    already_product_in_cart.quantity = product_quantity;
                } else {
                    ids_product_for_add.add(product_id);
                }

                //Получаем список товаров для добавления в корзину
                if (!ids_product_for_add.isEmpty()) {
                    List<Product> product_for_add = product_dao.getList(ids_product_for_add);

                    for(Product add_product:product_for_add) {
                        products.add(new CartProduct(add_product, product_quantity));
                    }
                }
            } else {
                //Удаляем товарную позицию
                if (already_product_in_cart != null) {
                    products.remove(already_product_in_cart);
                }
            }
        }

        return products;
    }

    @Override
    public CartProduct getProduct(int product_id) {
        for(CartProduct cartProduct:products) {
            if (cartProduct.product.getId() == product_id) {
                return cartProduct;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        if (!products.isEmpty()) {
            result.append("<div class='product_list'>");
            for(CartProduct cartProduct:products) {
                result.append("<div class='product'>")
                        .append("<div>").append(cartProduct.product.getName()).append("</div>")
                        .append("<div>").append(cartProduct.quantity).append("</div>")
                        .append("<div>").append(Helper.getPriceFormated(cartProduct.product.getCost())).append("</div>")
                        .append("</div>");
            }
            result.append("</div>");

            result.append("<div class='cart_cost'>Цена корзины ").append(Helper.getPriceFormated(this.getCost())).append("</div>");
        } else {
            result.append("Пустая корзина");
        }

        return result.toString();
    }

    public static class CartProduct {
        private Product product;
        private int quantity;

        public CartProduct(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }

        public long getCost() {
            return product.getCost()*quantity;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CartProduct that = (CartProduct) o;
            return product.getId() == that.product.getId();
        }

        @Override
        public String toString() {
            return "CartProduct{" +
                    "product=" + product +
                    ", quantity=" + quantity +
                    '}';
        }

        @Override
        public int hashCode() {
            return Objects.hash(product.getId());
        }
    }
}

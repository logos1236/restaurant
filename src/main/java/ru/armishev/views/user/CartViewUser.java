package ru.armishev.views.user;

import ru.armishev.dao.user.CartDAOUser;
import ru.armishev.helper.Helper;

import java.util.List;

public class CartViewUser {
    public static String htmlCart(CartDAOUser cart) {
        StringBuilder result = new StringBuilder();

        if (!cart.getProducts().isEmpty()) {
            result.append("<div class='product_list'>");
            for(CartDAOUser.CartProduct cartProduct:cart.getProducts()) {
                result.append("<div class='product'>")
                        .append("<div>").append(cartProduct.getProduct().getName()).append("</div>")
                        .append("<div>").append(cartProduct.getQuantity()).append("</div>")
                        .append("<div>").append(Helper.getPriceFormated(cartProduct.getProduct().getCost())).append("</div>")
                        .append("</div>");
            }
            result.append("</div>");

            result.append("<div class='cart_cost'>Цена корзины ").append(Helper.getPriceFormated(cart.getCost())).append("</div>");
        } else {
            result.append("Пустая корзина");
        }

        return result.toString();
    }
}

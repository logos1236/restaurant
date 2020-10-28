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

            result.append("<div class='cart_cost'>Цена корзины: ").append(Helper.getPriceFormated(cart.getCost())).append("</div>");

            result.append("<form class='service-reserve-cart-form' action='/user/cart/reserve-cart/' method='POST'><button class='service-reserve-cart-btn'>Оформить заказ</button></form>");
        } else {
            result.append("Пустая корзина");
        }

        return result.toString();
    }

    public static String htmlReservedCart(CartDAOUser cart) {
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

            result.append("<div class='cart_cost'>Цена корзины: ").append(Helper.getPriceFormated(cart.getCost())).append("</div>");

            result.append("<form class='service-order-create-form' action='/user/order/create/' method='POST'><button class='service-order-create-btn'>Создать заказ</button></form>");
        }

        return result.toString();
    }
}

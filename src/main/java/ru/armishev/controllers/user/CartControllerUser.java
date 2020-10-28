package ru.armishev.controllers.user;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armishev.dao.user.CartDAOUser;
import ru.armishev.views.user.CartViewUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/user/cart", produces = "application/json; charset=utf-8")
public class CartControllerUser {
    @Autowired
    private CartDAOUser dao_cart;

    @PostMapping("/add/")
    @ResponseBody
    public String add(HttpServletRequest request, Model model) {
        // Unlock cart
        dao_cart.freeCart();

        // Set product in cart
        String jsonProducts = request.getParameter("product");
        List<CartDAOUser.CartProduct> cart = dao_cart.setProducts(jsonProducts);

        // Send html of cart
        JsonObject result =  new JsonObject();
        result.addProperty("cart", CartViewUser.htmlCart(dao_cart));

        return result.toString();
    }

    @PostMapping("/reserve-cart/")
    @ResponseBody
    public String reserveCart() {
        dao_cart.reserveCart();

        JsonObject result =  new JsonObject();
        result.addProperty("success", dao_cart.isReservedCart());
        result.addProperty("redirect_url", "/user/order/");
        result.addProperty("success_message", "Успешно");

        return result.toString();
    }
}

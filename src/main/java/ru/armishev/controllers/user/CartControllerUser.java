package ru.armishev.controllers.user;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armishev.dao.user.CartDAOUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/user/cart")
public class CartControllerUser {
    @Autowired
    private CartDAOUser dao_cart;

    @PostMapping("/add/")
    @ResponseBody
    public String add(HttpServletRequest request, Model model) {
        // Set product in cart
        String jsonProducts = request.getParameter("product");
        List<CartDAOUser.CartProduct> cart = dao_cart.setProducts(jsonProducts);

        // Send html of cart
        JsonObject result =  new JsonObject();
        result.addProperty("cart", dao_cart.toString());

        return result.toString();
    }
}

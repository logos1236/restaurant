package ru.armishev.controllers.user;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armishev.dao.user.CartDAOUser;
import ru.armishev.dao.user.OrderDAOUser;
import ru.armishev.entity.order.Order;
import ru.armishev.views.user.CartViewUser;

@Controller
@RequestMapping(value = "/user/order")
public class OrderControllerUser {
    @Autowired
    private CartDAOUser dao_cart;

    @Autowired
    private OrderDAOUser dao_order;

    @GetMapping("/")
    public String index(Model model) {
        if (!dao_cart.isReservedCart()) {
            return "redirect:/user/product/";
        }
        model.addAttribute("cart", dao_cart);
        model.addAttribute("htmlReservedCart", CartViewUser.htmlReservedCart(dao_cart));

        return "views/user/order/index.html";
    }

    @PostMapping(value="/create/", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String createOrderAPI(Model model) {
        JsonObject result =  new JsonObject();

        if (dao_cart.isReservedCart()) {
            result.addProperty("success", true);

            // Создаем заказ в API
            dao_order.createOrder();
        } else {
            result.addProperty("success", false);
        }

        return result.toString();
    }
}

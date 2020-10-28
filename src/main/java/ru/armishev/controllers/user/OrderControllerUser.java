package ru.armishev.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armishev.dao.user.CartDAOUser;
import ru.armishev.views.user.CartViewUser;

@Controller
@RequestMapping(value = "/user/order")
public class OrderControllerUser {
    @Autowired
    private CartDAOUser dao_cart;

    @GetMapping("/")
    public String index(Model model) {
        return "views/user/order/index.html";
    }
}

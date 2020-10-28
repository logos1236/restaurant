package ru.armishev.controllers.user;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armishev.dao.user.CartDAOUser;
import ru.armishev.dao.user.ProductDAOUser;
import ru.armishev.entity.product.Product;
import ru.armishev.views.user.CartViewUser;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/user/product")
public class ProductControllerUser {
    @Autowired
    private ProductDAOUser dao_product;

    @Autowired
    private CartDAOUser dao_cart;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("products", dao_product.getList());
        model.addAttribute("cart", dao_cart);
        model.addAttribute("cartHtml", CartViewUser.htmlCart(dao_cart));

        return "views/user/product/list.html";
    }
}

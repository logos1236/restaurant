package ru.armishev.controllers.user;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armishev.dao.admin.ProductDAOAdmin;
import ru.armishev.dao.user.ProductDAOUser;
import ru.armishev.entity.cart.Cart;
import ru.armishev.entity.product.Product;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/user/product")
public class ProductControllerUser {
    @Autowired
    private ProductDAOUser dao;

    @Autowired
    private Cart cart;

    @GetMapping("/")
    public String index(Model model) {
        List<Product> data = dao.getList();
        model.addAttribute("products", data);

        return "/user/product/list.html";
    }

    @PostMapping("/add/")
    @ResponseBody
    public String add(HttpServletRequest request, Model model) {
        //List<Product> data = dao.getList();
        //model.addAttribute("products", data);

        String jsonArray = request.getParameter("product");

        Type listType = new TypeToken<List<Cart.CartProduct>>(){}.getType();
        List<Cart.CartProduct> result = new Gson().fromJson(jsonArray, listType);

        if (!result.isEmpty()) {
            for(Cart.CartProduct product: result) {
                System.out.println(result);
                cart.setProduct(product);
            }
        }

        return cart.toString();
    }
}

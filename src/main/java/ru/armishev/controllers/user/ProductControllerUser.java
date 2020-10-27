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
        model.addAttribute("cart", cart);

        System.out.println(cart.toString());

        return "views/user/product/list.html";
    }

    @PostMapping("/add/")
    @ResponseBody
    public String add(HttpServletRequest request, Model model) {
        // Set product in cart
        String jsonArray = request.getParameter("product");

        Type listType = new TypeToken<List<Cart.CartProduct>>(){}.getType();
        List<Cart.CartProduct> product_to_add = new Gson().fromJson(jsonArray, listType);

        if (!product_to_add.isEmpty()) {
            for(Cart.CartProduct product: product_to_add) {
                System.out.println(product_to_add);
                cart.setProduct(product);
            }
        }

        // Send html of cart
        Map<String, String> result = new HashMap<>();
        result.put("cart", cart.toString());

        return new Gson().toJson(result);
    }
}

package ru.armishev.controllers.admin;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armishev.dao.rest.ProductDAO;
import ru.armishev.dao.web.ProductDAOWeb;
import ru.armishev.entity.product.Product;
import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/admin/product")
public class ProductControllerAdmin {
    @Autowired
    private ProductDAOWeb dao;

    @GetMapping("/")
    public String index(Model model) {
        List<Product> data = dao.getList();
        model.addAttribute("products", data);

        System.out.println(data);

        return "/admin/product/list.html";
    }
}

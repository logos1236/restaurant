package ru.armishev.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armishev.dao.admin.ProductDAOAdmin;
import ru.armishev.entity.product.Product;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/product")
public class ProductControllerAdmin {
    @Autowired
    private ProductDAOAdmin dao;

    @GetMapping("/")
    public String index(Model model) {
        List<Product> data = dao.getList();
        model.addAttribute("products", data);

        return "/admin/product/list.html";
    }
}

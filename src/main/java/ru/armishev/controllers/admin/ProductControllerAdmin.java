package ru.armishev.controllers.admin;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armishev.dao.rest.ProductDAO;
import ru.armishev.dao.web.ProductDAOWeb;

@Controller
@RequestMapping(value = "/admin/product")
public class ProductControllerAdmin {
    @Autowired
    private ProductDAOWeb dao;

    @GetMapping("/")
    public String index() {

        dao.getList();

        return "/admin/product/list.html";
    }
}

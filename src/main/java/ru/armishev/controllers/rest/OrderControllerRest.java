package ru.armishev.controllers.rest;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.armishev.entity.product.Product;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Controller
@RequestMapping(value = "/rest/order", produces = "application/json; charset=utf-8")
public class OrderControllerRest {
    @PutMapping("/")
    @ResponseBody
    public String index(@RequestBody String data) {
        System.out.println(data);

        return null;
    }
}

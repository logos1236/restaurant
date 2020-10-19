package ru.armishev.controllers;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armishev.dao.WaiterDAO;
import ru.armishev.dao.WaiterSevice;

@Controller

@RequestMapping(value = "/stuff", produces = "application/json; charset=utf-8")
public class Stuff {
    @Autowired
    private WaiterDAO waiterDAO;

    @GetMapping("/")
    @ResponseBody
    public String index(Model model) {
        Gson g = new GsonBuilder().addSerializationExclusionStrategy(WaiterDAO.public_strategy).create();

        return g.toJson(waiterDAO.getList());
    }
}

package ru.armishev.controllers;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.armishev.dao.WaiterDAO;
import ru.armishev.dao.WaiterSevice;
import ru.armishev.entity.stuff.Waiter;

import java.util.Optional;

@Controller

@RequestMapping(value = "/stuff", produces = "application/json; charset=utf-8")
public class Stuff {
    @Autowired
    private WaiterDAO waiterDAO;

    @GetMapping("/")
    @ResponseBody
    public String index() {
        Gson g = new GsonBuilder().addSerializationExclusionStrategy(WaiterDAO.public_strategy).create();

        return g.toJson(waiterDAO.getList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String getById(@PathVariable Integer id) {
        Gson g = new GsonBuilder().addSerializationExclusionStrategy(WaiterDAO.public_strategy).create();
        Optional<Waiter> result = waiterDAO.getById(id);

        if (result.isPresent()) {
            return g.toJson(waiterDAO.getById(id).get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
    }
}

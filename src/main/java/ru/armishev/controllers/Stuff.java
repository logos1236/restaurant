package ru.armishev.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.armishev.dao.WaiterDAO;
import ru.armishev.entity.stuff.Waiter;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
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
            return g.toJson(result.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
    }

    @GetMapping("/add")
    public String add(HttpServletRequest request) {
        Gson g = new GsonBuilder().addSerializationExclusionStrategy(WaiterDAO.public_strategy).create();

        Waiter new_waiter = new Waiter();
        new_waiter.setName("Tratata");

        int waiter_id = waiterDAO.add(new_waiter);

        return "redirect:/stuff/"+waiter_id;
    }

    @GetMapping("/{id}/delete")
    @ResponseBody
    public String add(@PathVariable Integer id) {
        waiterDAO.delete(id);

        return id+"";
    }

    @GetMapping("/{id}/update")
    @ResponseBody
    public String update(@PathVariable Integer id) {
        Gson g = new GsonBuilder().addSerializationExclusionStrategy(WaiterDAO.public_strategy).create();

        Optional<Waiter> waiter = waiterDAO.getById(id);

        if (waiter.isPresent()) {
            waiter.get().setName("1111111");
            waiterDAO.update(waiter.get());

            return g.toJson(waiter.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
    }
}

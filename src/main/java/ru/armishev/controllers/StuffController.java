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

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping(value = "/stuff", produces = "application/json; charset=utf-8")
public class StuffController {
    @Autowired
    private WaiterDAO dao;

    @GetMapping("/")
    @ResponseBody
    public String index() {
        Gson g = dao.getPublicGson();

        return g.toJson(dao.getList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String getById(@PathVariable Integer id) {
        Gson g = dao.getPublicGson();
        Optional<Waiter> result = dao.getById(id);

        if (result.isPresent()) {
            return g.toJson(result.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
    }

    @GetMapping("/add")
    public String add(HttpServletRequest request) {
        Gson g = dao.getPublicGson();

        Waiter new_waiter = new Waiter();
        new_waiter.setName("Tratata");

        int waiter_id = dao.add(new_waiter);

        return "redirect:/stuff/"+waiter_id;
    }

    @GetMapping("/{id}/delete")
    @ResponseBody
    public String add(@PathVariable Integer id) {
        dao.delete(id);

        return id+"";
    }

    @GetMapping("/{id}/update")
    @ResponseBody
    public String update(@PathVariable Integer id) {
        Gson g = dao.getPublicGson();

        Optional<Waiter> waiter = dao.getById(id);

        if (waiter.isPresent()) {
            waiter.get().setName("1111111");
            dao.update(waiter.get());

            return g.toJson(waiter.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
    }
}

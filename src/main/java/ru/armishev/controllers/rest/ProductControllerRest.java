package ru.armishev.controllers.rest;

import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.armishev.dao.rest.ProductDAO;
import ru.armishev.entity.product.Product;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping(value = "/rest/product", produces = "application/json; charset=utf-8")
public class ProductControllerRest {
    @Autowired
    private ProductDAO dao;

    @GetMapping("/")
    @ResponseBody
    public String index(@RequestParam(name="data", required = false) String data) {
        List<Integer> id_list = getListIdFromUrl(data);
        Gson g = dao.getPublicGson();

        List<Product> result = (id_list.isEmpty()) ? dao.getList() : dao.getList(id_list);
        return g.toJson(result);
    }

    /*
    Получаем массив id товаров из переданного в url параметра
     */
    private static List<Integer> getListIdFromUrl(String data) {
        List<Integer> id_list = new ArrayList<>();

        if (data != null) {
            JsonParser parser = new JsonParser();
            String decode_data = "";

            try {
                decode_data = URLDecoder.decode(data, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            JsonArray id_json_array = parser.parse(parser.parse(decode_data).getAsJsonObject().get("id").getAsString()).getAsJsonArray();

            if (id_json_array != null) {
                for(JsonElement element: id_json_array) {
                    id_list.add(element.getAsInt());
                }
            }
        }

        return id_list;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String getById(@PathVariable Integer id) {
        Gson g = dao.getPublicGson();
        Optional<Product> result = dao.getById(id);

        if (result.isPresent()) {
            return g.toJson(result.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
    }

    @GetMapping("/add")
    public String add(HttpServletRequest request) {
        Gson g = dao.getPublicGson();

        Product new_elem = new Product();
        new_elem.setName("Tratata");

        int waiter_id = dao.add(new_elem);

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

        Optional<Product> waiter = dao.getById(id);

        if (waiter.isPresent()) {
            waiter.get().setName("1111111");
            dao.update(waiter.get());

            return g.toJson(waiter.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
    }
}

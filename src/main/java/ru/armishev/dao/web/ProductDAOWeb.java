package ru.armishev.dao.web;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armishev.dao.EntityService;
import ru.armishev.entity.product.Product;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductDAOWeb implements EntityService<Product> {
    @Autowired
    private RestClient restClient;
    private String main_url = "/product/";

    @Override
    public List<Product> getList() {
        String jsonArray = restClient.get(main_url);

        Type listType = new TypeToken<ArrayList<Product>>(){}.getType();
        List<Product> result = new Gson().fromJson(jsonArray, listType);

        return result;
    }

    @Override
    public Optional<Product> getById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public int add(Product element) {
        return 0;
    }

    @Override
    public Product update(Product element) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Gson getPublicGson() {
        return null;
    }
}

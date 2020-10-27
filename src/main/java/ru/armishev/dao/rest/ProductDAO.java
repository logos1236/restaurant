package ru.armishev.dao.rest;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armishev.dao.EntityService;
import ru.armishev.entity.product.Product;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductDAO implements EntityService<Product> {
    private static ExclusionStrategy public_strategy = new ExclusionStrategy() {
        private final String[] allow_name_list = {"id", "name", "cost", "description", "category", "execute_time"};

        @Override
        public boolean shouldSkipField(FieldAttributes fieldAttributes) {
            boolean result = !(Arrays.stream(allow_name_list).anyMatch(t->{return t.equals(fieldAttributes.getName());}));

            return result;
        }

        @Override
        public boolean shouldSkipClass(Class<?> aClass) {
            return false;
        }
    };

    @Override
    public Gson getPublicGson() {
        return new GsonBuilder().addSerializationExclusionStrategy(public_strategy).create();
    }

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> getList() {
        return repository.findAll();
    }

    @Override
    public List<Product> getList(List<Integer> id_list) {
        return repository.findList(id_list);
    }

    @Override
    public Optional<Product> getById(Integer integer) {
        return repository.findById(integer);
    }

    @Override
    public int add(Product product) {
        repository.add(product);
        return repository.getMaxId();
    }

    @Override
    public Product update(Product product) {
        repository.update(product);

        return product;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}

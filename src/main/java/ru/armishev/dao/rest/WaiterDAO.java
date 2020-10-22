package ru.armishev.dao.rest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armishev.dao.EntityService;
import ru.armishev.entity.stuff.Waiter;

@Service
public class WaiterDAO implements EntityService<Waiter> {
    private static ExclusionStrategy public_strategy = new ExclusionStrategy() {
        private final String[] allow_name_list = {"id", "name"};

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
    private WaiterRepository repository;

    @Override
    public List<Waiter> getList() {
        return repository.findAll();
    }

    @Override
    public Optional<Waiter> getById(Integer integer) {
        return repository.findById(integer);
    }

    @Override
    @Transactional
    public int add(Waiter waiter) {
        repository.add(waiter);
        return repository.getMaxId();
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Waiter update(Waiter waiter) {
        repository.update(waiter);

        return waiter;
    }
}

package ru.armishev.dao;

import java.util.Arrays;
import java.util.List;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armishev.entity.stuff.Waiter;

@Service
public class WaiterDAO implements WaiterSevice {
    public static ExclusionStrategy public_strategy = new ExclusionStrategy() {
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

    @Autowired
    private WaiterRepository waiterRepository;

    @Override
    public List<Waiter> getList() {
        return waiterRepository.findAll();
    }
}

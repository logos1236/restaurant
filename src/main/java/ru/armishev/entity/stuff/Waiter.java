package ru.armishev.entity.stuff;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Waiter extends Stuff {
    @Override
    public void doWork() {

    }
}

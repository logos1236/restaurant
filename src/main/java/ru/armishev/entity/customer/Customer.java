package ru.armishev.entity.customer;

import ru.armishev.entity.table.Table;

public class Customer implements ICustomer {
    int id;
    String name;
    long tips_amount;

    @Override
    public Table getTable() {
        return null;
    }
}

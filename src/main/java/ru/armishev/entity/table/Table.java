package ru.armishev.entity.table;

public class Table implements ITable {
    int id;

    @Override
    public boolean isBusy() {
        return false;
    }
}

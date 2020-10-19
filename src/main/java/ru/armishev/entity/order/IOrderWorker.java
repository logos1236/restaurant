package ru.armishev.entity.order;

public interface IOrderWorker {
    public void executeStart();
    public void executeEnd();
    public void close();
}

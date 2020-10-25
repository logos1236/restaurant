package ru.armishev.entity.order;

import ru.armishev.entity.customer.Customer;
import ru.armishev.entity.product.Product;
import ru.armishev.entity.table.Table;
import ru.armishev.entity.stuff.Waiter;

import javax.persistence.Entity;
import java.util.*;

public class Order implements IOrderCustomer, IOrderWorker {
    private int id;
    private long create_time;
    private long supposed_execute_time;
    private long close_time;

    private Map<Product,Integer> products;
    private Customer customer;
    private Waiter waiter;
    private Table table;
    private OrderStatus order_status;
    private OrderPayStatus order_pay_status;

    public Order(Customer customer, Table table, Map<Product,Integer> products) {
        this.customer = customer;
        this.table = table;
        this.products = products;
        this.order_status = OrderStatus.CREATED;
        this.order_pay_status = OrderPayStatus.EXPECT_PAY;
        this.create_time = System.currentTimeMillis();
    }

    public long getSupposedExecuteTime() {
        Map.Entry<Product, Integer> maxEntry = products.entrySet().stream()
                .max(Map.Entry.comparingByKey((product, t1) -> {
                    return Long.compare(product.getExecuteTime(),t1.getExecuteTime());
                })).get();

        return maxEntry.getKey().getExecuteTime();
    }

    public long getCost() {
        long result = products.
                entrySet().
                stream().
                mapToLong(entry->{return entry.getKey().getCost()*entry.getValue();}).
                sum();
        return result;
    }

    @Override
    public void setProduct(Map<Product,Integer> products) {
        this.products = products;
    }

    @Override
    public void cancel() {
        order_status = OrderStatus.CANCELED;
    }

    @Override
    public void executeStart() {
        order_status = OrderStatus.IN_PROCESS;
    }

    @Override
    public void executeEnd() {
        order_status = OrderStatus.EXECUTED;
    }

    @Override
    public void close() {
        order_status = OrderStatus.CLOSED;
        this.close_time = System.currentTimeMillis();
    }

    @Override
    public void pay() {
        order_pay_status = OrderPayStatus.PAYED;
    }
}

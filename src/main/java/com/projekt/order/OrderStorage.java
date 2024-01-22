package com.projekt.order;

import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class OrderStorage {

    private final List<Order> orderList;

    public OrderStorage() {
        this.orderList = new ArrayList<>();
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void addOrder(Order order){
        orderList.add(order);
    }
}

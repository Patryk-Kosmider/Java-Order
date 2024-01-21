package com.projekt.order;

import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class OrderStorage {

    private final List<Order> orderList;

    public OrderStorage(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void addOrder(Order order){
        orderList.add(order);
    }
}

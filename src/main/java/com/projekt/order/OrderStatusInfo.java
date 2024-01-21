package com.projekt.order;

import java.util.Map;

public class OrderStatusInfo {

    private Order order;
    private OrderStatus orderStatus;
    private Map<Product, Integer> productIntegerMap;

    public OrderStatusInfo(OrderStatus orderStatus, Map<Product, Integer> productIntegerMap, Order order) {
        this.orderStatus = orderStatus;
        this.productIntegerMap = productIntegerMap;
        this.order = order;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Map<Product, Integer> getProductIntegerMap() {
        return productIntegerMap;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setProductIntegerMap(Map<Product, Integer> productIntegerMap) {
        this.productIntegerMap = productIntegerMap;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

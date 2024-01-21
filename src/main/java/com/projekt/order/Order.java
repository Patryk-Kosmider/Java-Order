package com.projekt.order;

import java.util.*;
public class Order {

    private String userID;

    private String orderID;
    private  OrderStatus orderStatus;

    private Map<Product, Integer> orderProductsList;
    private String orderAddress;

    public Order(String userID,  Map<Product, Integer> orderProductsList, String orderAddress, String orderID) {
        this.userID = userID;
        this.orderProductsList = orderProductsList;
        this.orderAddress = orderAddress;
        this.orderID = orderID;
        this.orderStatus = OrderStatus.NOWE;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderProductsList(Map<Product, Integer> orderProductsList) {
        this.orderProductsList = orderProductsList;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getUserID() {
        return userID;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Map<Product, Integer> getOrderProductsList() {
        return orderProductsList;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public String getOrderID() {
        return orderID;
    }
}

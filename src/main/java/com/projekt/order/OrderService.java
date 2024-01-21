package com.projekt.order;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderService {
    private OrderStorage orderStorage;
    private ProductList productList;

    public OrderService(OrderStorage orderStorage, ProductList productList) {
        this.orderStorage = orderStorage;
        this.productList = productList;
    }


    public boolean checkIfCanOrderProducts(Map<Product, Integer> productIntegerMap){
        for (Map.Entry<Product, Integer> entry: productIntegerMap.entrySet()){
            Product product = entry.getKey();
            Integer requestedQt = entry.getValue();
            if (!productList.getProductList().contains(product)){
                return false;
            }

           for (Product product1: productList.getProductList()){
               if(product1.equals(product)){
                   int availableQt = product1.getProductQt();
                   if(availableQt - requestedQt < 0 ){
                       return false;
                   }
               }
           }
        }
        return true;
    }

    public void  deleteQtFromProductList(Map<Product, Integer> productIntegerMap){
        for(Map.Entry<Product, Integer> entry: productIntegerMap.entrySet()){
            Product product = entry.getKey();
            Integer requstedQt = entry.getValue();
            for(Product productInStorage: productList.getProductList()){
                if(product.equals(productInStorage)){
                    product.setProductQt(product.getProductQt() - requstedQt);
                }
            }
        }

    }

    public Order order(String userID, Map<Product, Integer> productIntegerMap, String orderAddress, String orderID){
        if(checkIfCanOrderProducts(productIntegerMap)){
            deleteQtFromProductList(productIntegerMap);
            return new Order(userID, productIntegerMap, orderAddress, orderID);
        }
        throw new RuntimeException("This product can't be ordered");
    }

    public OrderStatusInfo orderStatusInfo(String orderID){
        for (Order order : orderStorage.getOrderList()){
            if(order.getOrderID().equals(orderID)){
                return new OrderStatusInfo(order.getOrderStatus(), order.getOrderProductsList(), order);
            }
        }
        return null;
    }

    public OrderStatusInfo cancelOrder(String orderID){
        for(Order order : orderStorage.getOrderList()){
            if(order.getOrderID().equals(orderID)){
                order.setOrderStatus(OrderStatus.ANULOWANE);
                return new OrderStatusInfo(OrderStatus.ANULOWANE, order.getOrderProductsList(), order);
            }
        }
        return null;
    }

    public OrderStatusInfo confirmOrder(String orderID){
        for (Order order : orderStorage.getOrderList()){
            if(order.getOrderID().equals(orderID)){
                order.setOrderStatus(OrderStatus.DOSTARCZONE);
                return new OrderStatusInfo(OrderStatus.DOSTARCZONE, order.getOrderProductsList(), order);
            }
        }

        return null;
    }
}

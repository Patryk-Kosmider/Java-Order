package com.projekt.order;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceUnitTests {

    @Mock
    private ProductList productList;

    @Mock
    private OrderStorage orderStorage;

    @InjectMocks
    private OrderService orderService;


    @Test
    void shouldCheckIfProductsCanBeOrdered() {
        MockitoAnnotations.openMocks(this);


        Product laptop = new Product("Laptop", 2);
        Product phone = new Product("Phone", 3);
        // True scenario
        Map<Product, Integer> orderlist1 = new HashMap<>();
        orderlist1.put(laptop , 1);
        // False scenario
        Map <Product, Integer> orderlist2 = new HashMap<>();
        orderlist2.put(laptop, 3);
        // True sceniario with multiple items
        Map <Product, Integer> orderlist3 = new HashMap<>();
        orderlist3.put(laptop, 1);
        orderlist3.put(phone, 2);
        // False sceniario with multiple items
        Map <Product, Integer> orderlist4 = new HashMap<>();
        orderlist4.put(laptop, 1);
        orderlist4.put(phone, 5);

        // When getProductList - return list of laptop and phone
        Mockito.when(productList.getProductList()).thenReturn(List.of(laptop, phone));

        Assertions.assertTrue(orderService.checkIfCanOrderProducts(orderlist1));
        Assertions.assertFalse(orderService.checkIfCanOrderProducts(orderlist2));
        Assertions.assertTrue(orderService.checkIfCanOrderProducts(orderlist3));
        Assertions.assertFalse(orderService.checkIfCanOrderProducts(orderlist4));

    }


    @Test
    void deleteProductQTAtOrder() {
        MockitoAnnotations.openMocks(this);
        Product laptop = new Product("Laptop", 3);
        Map <Product, Integer> productIntegerMap = new HashMap<>();
        productIntegerMap.put(laptop, 2);

        Mockito.when(productList.getProductList()).thenReturn(List.of(laptop));

        orderService.deleteQtFromProductList(productIntegerMap);
        Assertions.assertEquals(1, laptop.getProductQt());

    }

    @Test
    void createNewOrder() {
        Product laptop = new Product("Laptop", 3);
        Map <Product, Integer> orderlist = new HashMap<>();
        orderlist.put(laptop, 2);
        Map <Product, Integer> orderlistFalse = new HashMap<>();
        orderlistFalse.put(laptop, 5);

        Mockito.when(productList.getProductList()).thenReturn(List.of(laptop));

        Assertions.assertNotNull(orderService.order("1A", orderlist, "Testowa 5/21", "ABC01"));
        Assertions.assertThrows(RuntimeException.class, () -> orderService.order("1A", orderlistFalse, "Testowa 5/21", "ABC02"));

        Assertions.assertEquals(1, laptop.getProductQt());
    }

    @Test
    void orderStatusInfo() {
        Map <Product, Integer> orderlist = new HashMap<>();
        Product laptop = new Product("Laptop", 3);
        orderlist.put(laptop, 2);
        Order order = new Order("1B", orderlist, "Testowa 1/1", "ABC03");

        Mockito.when(orderStorage.getOrderList()).thenReturn(List.of(order));

        OrderStatusInfo orderStatusInfo = orderService.orderStatusInfo("ABC03");
        OrderStatusInfo orderStatusInfo1 = orderService.orderStatusInfo("ABC04");

        Assertions.assertNotNull(orderStatusInfo);
        Assertions.assertEquals(order, orderStatusInfo.getOrder());
        Assertions.assertEquals(OrderStatus.NOWE,order.getOrderStatus());
        Assertions.assertEquals(order.getOrderProductsList(), orderlist);
        Assertions.assertNull(orderStatusInfo1);

    }

    @Test
    void cancelOrder() {

        Map <Product, Integer> orderlist = new HashMap<>();
        Product laptop = new Product("Laptop", 3);
        orderlist.put(laptop, 2);
        Order order = new Order("1B", orderlist, "Testowa 1/1", "ABC03");

        Mockito.when(orderStorage.getOrderList()).thenReturn(List.of(order));

        OrderStatusInfo orderStatusInfo = orderService.cancelOrder("ABC03");
        OrderStatusInfo orderStatusInfo1 = orderService.cancelOrder("ABC04");

        Assertions.assertNotNull(orderStatusInfo);
        Assertions.assertEquals(OrderStatus.ANULOWANE, orderStatusInfo.getOrderStatus());
        Assertions.assertEquals(order, orderStatusInfo.getOrder());
        Assertions.assertEquals(order.getOrderProductsList(), orderlist);
        Assertions.assertNull(orderStatusInfo1);

    }

    @Test
    void cofirmOrder() {

        Map <Product, Integer> orderlist = new HashMap<>();
        Product laptop = new Product("Laptop", 3);
        orderlist.put(laptop, 2);
        Order order = new Order("1B", orderlist, "Testowa 1/1", "ABC03");

        Mockito.when(orderStorage.getOrderList()).thenReturn(List.of(order));

        OrderStatusInfo orderStatusInfo = orderService.confirmOrder("ABC03");
        OrderStatusInfo orderStatusInfo1 = orderService.confirmOrder("ABC04");

        Assertions.assertNotNull(orderStatusInfo);
        Assertions.assertEquals(OrderStatus.DOSTARCZONE, orderStatusInfo.getOrderStatus());
        Assertions.assertEquals(order, orderStatusInfo.getOrder());
        Assertions.assertEquals(order.getOrderProductsList(), orderlist);
        Assertions.assertNull(orderStatusInfo1);

    }
}

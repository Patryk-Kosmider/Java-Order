package com.projekt.order;

import org.junit.jupiter.api.*;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import java.util.Map;

@SpringBootTest
public class OrderServiceITTests {

    private static boolean isSetupDone = false;

    private  Product product1 ;
    private  Product product2;
    private  Product product3;
    private Product product4;
    private Order order1;
    private  Order order2;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderStorage orderStorage;
    @Autowired
    private ProductList productList;

    @BeforeEach
     void setUp() {
            product1 = new Product("Laptop", 10);
            product2 = new Product("Phone", 5);
            product3 = new Product("Keyobard", 3);
            product4 = new Product("Mouse", 0);

            productList.addProduct(product1);
            productList.addProduct(product2);
            productList.addProduct(product3);
            productList.addProduct(product4);

            order1 = new Order("Patryk", Map.of(product1, 5, product2, 2), "Testowa 1/5", "ABC01");
            order2 = new Order("Bartek", Map.of(product3, 1, product2, 1), "Testowa 1/32", "ABC02");

            orderStorage.addOrder(order1);
            orderStorage.addOrder(order2);


    }

    @AfterEach
    void afterAll(){
        productList.getProductList().clear();
        orderStorage.getOrderList().clear();
    }

    @Test
    void shouldCheckIfProductsCanBeOrdered() {
        Assertions.assertTrue(orderService.checkIfCanOrderProducts(Map.of(product1, 3, product2, 2)));
        Assertions.assertFalse(orderService.checkIfCanOrderProducts(Map.of(product1, 3, product4, 1)));
    }

    @Test
    void shouldDeleteQtFromProductList() {
        orderService.deleteQtFromProductList(Map.of(product1, 2, product3, 1));
        Assertions.assertEquals(8, product1.getProductQt());
        Assertions.assertEquals(2, product3.getProductQt());

        product1.setProductQt(10);
        product3.setProductQt(3);
    }

    @Test
    void shouldCreateNewOrder() {
        Order order3 = orderService.order("Patryk2", Map.of(product1, 2, product2, 2), "Testowa 25/1", "ABC03");
        orderStorage.addOrder(order3);
        Assertions.assertNotNull(order3);
        Assertions.assertTrue(orderStorage.getOrderList().contains(order3));

        Assertions.assertThrows(RuntimeException.class, () -> orderService.order("Patryk3", Map.of(product4, 1), "Testowa 12", "ABC04"));

    }

    @Test
    void shouldReturnOrderStatusInfo () {

        OrderStatusInfo orderStatusInfo = orderService.orderStatusInfo("ABC02");
        Assertions.assertNotNull(orderStatusInfo);
        Assertions.assertEquals(OrderStatus.NOWE, orderStatusInfo.getOrderStatus());
        Assertions.assertEquals(order2,orderStatusInfo.getOrder());
        Assertions.assertEquals(Map.of(product3, 1, product2, 1), orderStatusInfo.getProductIntegerMap());
    }

    @Test
    void shouldCancelOrder() {
        OrderStatusInfo orderStatusInfo = orderService.cancelOrder("ABC02");
        Assertions.assertNotNull(orderStatusInfo);
        Assertions.assertEquals(OrderStatus.ANULOWANE, orderStatusInfo.getOrderStatus());
        Assertions.assertEquals(OrderStatus.ANULOWANE, order2.getOrderStatus());
    }

    @Test
    void shouldConfirmOrder() {
        OrderStatusInfo orderStatusInfo = orderService.confirmOrder("ABC02");
        Assertions.assertNotNull(orderStatusInfo);
        Assertions.assertEquals(OrderStatus.DOSTARCZONE, orderStatusInfo.getOrderStatus());
        Assertions.assertEquals(OrderStatus.DOSTARCZONE, order2.getOrderStatus());
    }
}

package com.projekt.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.Map;

@SpringBootTest
public class OrderServiceITTests {

    private OrderStorage orderStorage;
    private ProductList productList;

    @Autowired
    private OrderService orderService;

    @BeforeTestClass
    void setUp() {
        Product product1 = new Product("Laptop",5);
        Product product2 = new Product("Phone", 3);
        Product product3 = new Product("Keyobard", 1);
        Product product4 = new Product("Mouse", 0);

        productList.addProduct(product1);
        productList.addProduct(product2);
        productList.addProduct(product3);
        productList.addProduct(product4);

        Order order1 = new Order("Patryk", Map.of(product1, 5, product2, 2), "Testowa 1/5", "ABC01");

    }

    @Test
    void shouldCheckIfProductsCanBeOrdered() {

    }
}

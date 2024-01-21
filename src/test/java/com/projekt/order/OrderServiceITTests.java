package com.projekt.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceITTests {
    
    @Autowired
    private OrderService orderService;

    @BeforeEach

    @Test
    void shouldCheckIfProductsCanBeOrdered() {

    }
}

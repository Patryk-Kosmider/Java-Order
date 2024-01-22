package com.projekt.order;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductList {

    private final List<Product> productList;

    public ProductList() {
        this.productList = new ArrayList<>();
    }

    public List<Product> getProductList() {
        return productList;
    }
    public void addProduct(Product product) {
        productList.add(product);
    }
}

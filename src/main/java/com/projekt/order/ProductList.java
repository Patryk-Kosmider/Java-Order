package com.projekt.order;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductList {

    private final List<Product> productList;

    public ProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }
    public void addProduct(Product product) {
        productList.add(product);
    }
}

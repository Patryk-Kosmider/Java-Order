package com.projekt.order;

public class Product {

    private String productName;
    private int productQt;

    public Product(String productName, int productQt) {
        this.productName = productName;
        this.productQt = productQt;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductQt() {
        return productQt;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductQt(int productQt) {
        this.productQt = productQt;
    }
}

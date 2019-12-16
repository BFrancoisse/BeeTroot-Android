package com.example.beetrootapp.model;

public class Catalog {
    private int farmId;
    private int productId;
    private Farm farm;
    private Product product;

    public Catalog(int farmId, int productId, Farm farm, Product product) {
        this.farmId = farmId;
        this.productId = productId;
        this.farm = farm;
        this.product = product;
    }

    public int getFarmId() {
        return farmId;
    }

    public int getProductId() {
        return productId;
    }

    public Farm getFarm() {
        return farm;
    }

    public Product getProduct() {
        return product;
    }

    public void setFarmId(int farmId) {
        this.farmId = farmId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

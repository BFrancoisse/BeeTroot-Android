package com.example.beetrootapp.model;

import java.io.Serializable;

public class Farm implements Serializable {
    private int id;
    private String name;
    private String geographicCoordinates;
    private String description;
    private int addressId;
    private int userId;
    private Address address;
    private User user;
    private Catalog[] catalog;
    private Review[]review;
    private Picture []picture;
    private float distance;

    public Farm(int id, String name, String geographicCoordinates, String description, int addressId, int userId, Address address, User user, Catalog[] catalog, Review[] review, Picture[] picture, float distance) {
        this.id = id;
        this.name = name;
        this.geographicCoordinates = geographicCoordinates;
        this.description = description;
        this.addressId = addressId;
        this.userId = userId;
        this.address = address;
        this.user = user;
        this.catalog = catalog;
        this.review = review;
        this.picture = picture;
        this.distance=distance;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGeographicCoordinates() {
        return geographicCoordinates;
    }

    public String getDescription() {
        return description;
    }

    public int getAddressId() {
        return addressId;
    }

    public int getUserId() {
        return userId;
    }

    public Address getAddress() {
        return address;
    }

    public User getUser() {
        return user;
    }

    public Catalog[] getCatalog() {
        return catalog;
    }

    public Review[] getReview() {
        return review;
    }

    public Picture[] getPicture() {
        return picture;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGeographicCoordinates(String geographicCoordinates) {
        this.geographicCoordinates = geographicCoordinates;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCatalog(Catalog[] catalog) {
        this.catalog = catalog;
    }

    public void setReview(Review[] review) {
        this.review = review;
    }

    public void setPicture(Picture[] picture) {
        this.picture = picture;
    }
}

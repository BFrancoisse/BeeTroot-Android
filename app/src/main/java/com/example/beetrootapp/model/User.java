package com.example.beetrootapp.model;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private String phone;
    private int addressId;
    private Address address;
    private Review[] reviews;
    private Farm[] farm;
    private int userId;
    private Like[]likes;

    public User(int id, String email, String firstname, String lastname, String password, String phone, int addressId, Address address, Review[] reviews, Farm[] farm,Like[]likes/*,int userId*/) {
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.phone = phone;
        this.addressId = addressId;
        this.address = address;
        this.reviews = reviews;
        this.farm = farm;
        this.likes = likes;
        //this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public int getAddressId() {
        return addressId;
    }

    public Address getAddress() {
        return address;
    }

    public Review[] getReviews() {
        return reviews;
    }

    public Farm[] getFarm() {
        return farm;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setReviews(Review[] reviews) {
        this.reviews = reviews;
    }

    public void setFarm(Farm[] farm) {
        this.farm = farm;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

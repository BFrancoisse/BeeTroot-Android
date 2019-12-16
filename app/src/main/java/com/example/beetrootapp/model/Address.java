package com.example.beetrootapp.model;

public class Address {
    private int id;
    private String street;
    private String number;
    private int zipCode;
    private String city;
    private String country;
    private Farm[] farm;
    private Harvest[] harvest;
    private User[] user;

    public Address(int id, String street, String number, int zipCode, String city, String country, Farm[] farm, Harvest[] harvest, User[] user) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.farm = farm;
        this.harvest = harvest;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Farm[] getFarm() {
        return farm;
    }

    public Harvest[] getHarvest() {
        return harvest;
    }

    public User[] getUser() {
        return user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setFarm(Farm[] farm) {
        this.farm = farm;
    }

    public void setHarvest(Harvest[] harvest) {
        this.harvest = harvest;
    }

    public void setUser(User[] user) {
        this.user = user;
    }
}

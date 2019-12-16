package com.example.beetrootapp.model;

public class Harvest {
    private int id;
    private String name;
    private String geographicCoordinates;
    private String description;
    private String farmerName;
    private String farmerPhone;
    private int addressId;
    private Address address;
    private Picture[]picture;

    public Harvest(int id, String name, String geographicCoordinates, String description, String farmerName, String farmerPhone, int addressId, Address address, Picture[] picture) {
        this.id = id;
        this.name = name;
        this.geographicCoordinates = geographicCoordinates;
        this.description = description;
        this.farmerName = farmerName;
        this.farmerPhone = farmerPhone;
        this.addressId = addressId;
        this.address = address;
        this.picture = picture;
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

    public String getFarmerName() {
        return farmerName;
    }

    public String getFarmerPhone() {
        return farmerPhone;
    }

    public int getAddressId() {
        return addressId;
    }

    public Address getAddress() {
        return address;
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

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public void setFarmerPhone(String farmerPhone) {
        this.farmerPhone = farmerPhone;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPicture(Picture[] picture) {
        this.picture = picture;
    }
}

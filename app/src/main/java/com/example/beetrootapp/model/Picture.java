package com.example.beetrootapp.model;

import java.io.Serializable;

public class Picture implements Serializable {
    private int id;
    private String name;
    private String pictureURL;
    private int farmId;
    private int harvestId;
    private Farm farm;
    private Harvest harvest;

    public Picture(int id, String name, String pictureURL, int farmId, int harvestId, Farm farm, Harvest harvest) {
        this.id = id;
        this.name = name;
        this.pictureURL = pictureURL;
        this.farmId = farmId;
        this.harvestId = harvestId;
        this.farm = farm;
        this.harvest = harvest;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public int getFarmId() {
        return farmId;
    }

    public int getHarvestId() {
        return harvestId;
    }

    public Farm getFarm() {
        return farm;
    }

    public Harvest getHarvest() {
        return harvest;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public void setFarmId(int farmId) {
        this.farmId = farmId;
    }

    public void setHarvestId(int harvestId) {
        this.harvestId = harvestId;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public void setHarvest(Harvest harvest) {
        this.harvest = harvest;
    }
}

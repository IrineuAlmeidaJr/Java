package com.company;

public class TV {

    private String model;
    private String brand;
    private int sizeInch;

    public TV(String model, String brand, int sizeInch) {
        this.model = model;
        this.brand = brand;
        this.sizeInch = sizeInch;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public int getSizeInch() {
        return sizeInch;
    }
}

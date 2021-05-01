package com.nastichichika.model;


public class Tariff {
    private Long idTariff;
    private String nameTariff;
    private double price;

    public String getNameTariff() {
        return nameTariff;
    }

    public void setNameTariff(String nameTariff) {
        this.nameTariff = nameTariff;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

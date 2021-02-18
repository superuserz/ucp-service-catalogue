package com.nagp.ucp.service.catalog.domain;

public class Pricing {

    private int id;

    private int serviceId;

    private double price;

    private int discount;

    private boolean onInspection;

    public Pricing() {

    }

    public Pricing(int id, int serviceId, double price, int discount, boolean onInspection) {
        super();
        this.id = id;
        this.serviceId = serviceId;
        this.price = price;
        this.discount = discount;
        this.onInspection = onInspection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean isOnInspection() {
        return onInspection;
    }

    public void setOnInspection(boolean onInspection) {
        this.onInspection = onInspection;
    }

    @Override
    public String toString() {
        return "Pricing [id=" + id + ", serviceId=" + serviceId + ", price=" + price + ", discount=" + discount
            + ", onInspection=" + onInspection + "]";
    }

}

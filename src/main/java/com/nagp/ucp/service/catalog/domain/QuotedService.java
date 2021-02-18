package com.nagp.ucp.service.catalog.domain;

public class QuotedService extends Service {

    private double price;

    private int discount;

    private boolean quoteOnInspection;

    public QuotedService(double price, int discount, boolean quoteOnInspection) {
        super();
        this.price = price;
        this.discount = discount;
        this.quoteOnInspection = quoteOnInspection;
    }

    public QuotedService() {
        super();
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

    public boolean isQuoteOnInspection() {
        return quoteOnInspection;
    }

    public void setQuoteOnInspection(boolean quoteOnInspection) {
        this.quoteOnInspection = quoteOnInspection;
    }

    @Override
    public String toString() {
        return "QuotedService [price=" + price + ", discount=" + discount + ", quoteOnInspection=" + quoteOnInspection
            + ", getId()=" + getId() + ", getName()=" + getName() + ", isAvailable()=" + isAvailable()
            + ", getPincode()=" + getPincode() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
            + ", hashCode()=" + hashCode() + "]";
    }

}

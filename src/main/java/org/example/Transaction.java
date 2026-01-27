package org.example;

import java.util.Date;
import java.math.BigDecimal;

public class Transaction {
    private int id;
    private static int idCounter = 1;
    private Trader trader;
    private Asset asset;
    private String type;
    private int quantity;
    private BigDecimal price;
    private Date date;

    public Transaction(Trader trader, Asset asset, String type, int quantity, BigDecimal price, Date date) {
        this.id = idCounter++;
        this.trader = trader;
        this.asset = asset;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

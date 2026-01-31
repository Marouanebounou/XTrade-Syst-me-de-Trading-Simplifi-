package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.math.BigDecimal;

public class Transaction {
    private int id;
    private static int idCounter = 1;
    private Trader trader;
    private Asset asset;
    private String transactionType;
    private int quantity;
    private BigDecimal price;
    private Date date;

    public Transaction(Trader trader, Asset asset, int quantity , String transactionType) {
        this.id = idCounter++;
        this.trader = trader;
        this.asset = asset;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.price = asset.getUnitPrice();
        this.date = new Date();
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
        return transactionType;
    }

    public void setType(String type) {
        this.transactionType = type;
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

    public void showTransaction() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("=================================");
        System.out.println("Transaction ID   : " + id);
        System.out.println("Trader           : " + trader.getName() + " (ID: " + trader.getId() + ")");
        System.out.println("Asset            : " + asset.getName() +
                " | Code: " + asset.getCode() +
                " | Type: " + asset.getType());
        System.out.println("Type             : " + transactionType);
        System.out.println("Quantity         : " + quantity);
        System.out.println("Unit Price      : " + price + " MAD");

        BigDecimal total = price.multiply(BigDecimal.valueOf(quantity));
        System.out.println("Total Amount    : " + total + " MAD");

        System.out.println("Date            : " + sdf.format(date));
        System.out.println("=================================");
    }
}

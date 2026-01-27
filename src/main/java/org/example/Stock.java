package org.example;

import java.math.BigDecimal;

public class Stock extends Asset{
    private String market;
    public Stock(int code, String name, BigDecimal unitPrice, String type , String market) {
        super(code, name, unitPrice, type);
        this.market = market;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    @Override
    public void showAsset() {
        System.out.printf("Stock : Name : " + this.name + " - Code : " + this.code + " - Type : " + this.type + " - Price : " + this.unitPrice + "DH - Market : " + this.market);
    }
}

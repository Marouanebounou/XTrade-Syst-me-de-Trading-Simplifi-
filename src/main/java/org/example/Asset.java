package org.example;

import java.math.BigDecimal;

abstract public class Asset {
    protected int code;
    protected String name;
    protected BigDecimal unitPrice;
    protected String type;

    public Asset(int code, String name, BigDecimal unitPrice, String type) {
        this.code = code;
        this.name = name;
        this.unitPrice = unitPrice;
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    abstract public void showAsset();
}

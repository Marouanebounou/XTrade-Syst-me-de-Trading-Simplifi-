package org.example;

import java.math.BigDecimal;

public class CryptoCurrency extends Asset{
    private String blockchain;

    public CryptoCurrency(int code, String name, BigDecimal unitPrice, String type, String blockchain) {
        super(code, name, unitPrice, type);
        this.blockchain = blockchain;
    }

    public String getBlockchain() {
        return blockchain;
    }

    public void setBlockchain(String blockchain) {
        this.blockchain = blockchain;
    }

    @Override
    public void showAsset() {
        System.out.printf("Crypto currency : Name : " + this.name + " - Code : " + this.code + " - Type : " + this.type + " - Price : " + this.unitPrice + "DH - Block chain : " + this.blockchain);
    }
}

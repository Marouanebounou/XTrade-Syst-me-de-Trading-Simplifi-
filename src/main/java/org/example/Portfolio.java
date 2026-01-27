package org.example;

public class Portfolio {
    private Asset asset;
    private Trader trader;

    public Portfolio(Asset asset, Trader trader) {
        this.asset = asset;
        this.trader = trader;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }
}

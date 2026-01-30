package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Portfolio <T extends Asset> {
    private static int idCounter = 0;
    private int portfolioId;
    private HashMap<T , Integer> assetsMap = new HashMap<>();
    private List<Asset> assets;
    private Trader trader;

    public Portfolio(Trader trader) {
        this.portfolioId = idCounter++;
        this.assets = new ArrayList<>();
        this.trader = trader;
    }

    public int getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(int portfolioId) {
        this.portfolioId = portfolioId;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public List<Asset> getAsset() {
        return assets;
    }

    public void setAsset(List<Asset> asset) {
        this.assets = asset;
    }

    public HashMap<T, Integer> getAssetsMap() {
        return assetsMap;
    }

    public void setAssetsMap(HashMap<T, Integer> assetsMap) {
        this.assetsMap = assetsMap;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public void addAssetToPortfolio(Asset asset){
        assets.add(asset);
    }

    public void addToPortfolio(T asset , int quantity){
        try {
            Map.Entry target = null;
            for (Map.Entry a : assetsMap.entrySet()){
                if (a.getKey() == asset){
                    target = a;
                    a.setValue((int)a.getValue() + quantity);
                    break;
                }
            }
            if (target == null){
                assetsMap.put(asset , quantity);
                System.out.println("Asset added successfully.");
            }
        }catch (Exception e){
            System.out.println("Something went wrong while adding Asset to portfolio.");
        }
    }

    public void removeFromPortfolio(T asset , int qunatity){
        try {
            T target = null;
            for (Map.Entry m : assetsMap.entrySet()){
                if (m.getKey() == asset){
                    if ((int)m.getValue() < qunatity){

                    }
                    target = (T)m.getKey();
                    assetsMap.remove(m.getKey(),m.getValue());
                    System.out.println("Asset removed successfully.");
                }
            }
        } catch (Exception e) {
            System.out.println("Something");
        }
    }

}

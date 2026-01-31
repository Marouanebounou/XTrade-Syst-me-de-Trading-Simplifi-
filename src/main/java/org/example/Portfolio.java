package org.example;

import java.util.*;

public class Portfolio <T extends Asset> {
    private static int idCounter = 1;
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

    public void removeFromPortfolio(T asset , int quantity){
        try {
            T target = null;
            for (Map.Entry m : assetsMap.entrySet()){
                if (m.getKey() == asset){
                    target = (T)m.getKey();
                    int targetQuantity = (int)m.getValue();
                    if (targetQuantity > quantity){
                        m.setValue(targetQuantity - quantity);
                        System.out.println(quantity + " Sold from " + target.getName() + " With teh code : " + target.getCode());
                    } else if ((int)m.getValue() == quantity) {
                        assetsMap.remove(m.getKey(),m.getValue());
                        System.out.println("Asset sold successfully.");
                    }else {
                        System.out.println("The quantity is more than owned quantity.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong in removing an asset.");
        }
    }

    public void showPortfolio() {
        System.out.print("Trader: ");
        trader.showTrader();
        System.out.println("portfolio Id : " + this.portfolioId);
        List<T> assetsToShow = new ArrayList<>(assetsMap.keySet());
        assetsToShow.stream().forEach(a -> a.showAsset());
    }
}

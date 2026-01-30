package org.example;

import java.math.BigDecimal;
import java.util.*;

public class Platform {
    Scanner sc = new Scanner(System.in);
    private static int idCounter = 0;
    private int platformId ;
    private String name;
    private List<Trader> traders;
    private List<Asset> assets;
    private List<Transaction> transactions;
    private List<Portfolio> portfolios;

    public Platform(String name){
        this.platformId = idCounter++;
        this.name = name;
        this.assets = new ArrayList<>();
        this.traders = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.portfolios = new ArrayList<>();
    }

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Trader> getTraders() {
        return traders;
    }

    public void setTraders(List<Trader> traders) {
        this.traders = traders;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Portfolio> getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(List<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }

    public void addAsset(){
        try {

            System.out.printf("Enter asset code (number): ");
            int code = sc.nextInt();
            sc.nextLine();

            System.out.printf("Enter asset name: ");
            String name = sc.nextLine();

            System.out.printf("Enter unit price: ");
            BigDecimal unitPrice = sc.nextBigDecimal();
            sc.nextLine();

            System.out.printf("Choose asset type (1 = Stock, 2 = Crypto): ");
            int choice = Integer.parseInt(sc.nextLine());

            Asset asset;

            if (choice == 1) {
                System.out.printf("Enter stock market: ");
                String market = sc.nextLine();
                asset = new Stock(code, name, unitPrice, "Stock", market);
            } else {
                System.out.printf("Enter blockchain: ");
                String blockchain = sc.nextLine();
                asset = new CryptoCurrency(code, name, unitPrice, "Crypto", blockchain);
            }
            assets.add(asset);
            System.out.printf("Asset created successfully");

        } catch (NumberFormatException e) {
            System.out.printf("Wrong input format try again.");
        } catch (Exception e) {
            System.out.printf("Something went wrong in add Asset try again");
        }
    }

    public void addTrader(){
        try {
            System.out.printf("Enter trader name: ");
            String name = sc.nextLine();
            System.out.printf("Enter trader age: ");
            int age = sc.nextInt();
            sc.nextLine();
            Trader trader;
            if (age < 18){
                System.out.printf("This person is not allowed to be a trader.");
            }else {
                System.out.printf("Enter the trader sold: ");
                BigDecimal solde = sc.nextBigDecimal();
                sc.nextLine();
                trader = new Trader(name , age , solde);
                System.out.printf("Trader created successfully ");
                traders.add(trader);
                Portfolio portfolio = null;
                for (Trader t : traders){
                    if(t.getId() == trader.getId()){
                        portfolio = new Portfolio(t);
                    }
                }
                if (portfolio == null){
                    System.out.printf("Trader is not found");
                }else {
                    portfolios.add(portfolio);
                    System.out.printf("Portfolio add successfully");
                }
            }
        }catch (InputMismatchException e){
            System.out.printf("Wrong input format try again.");
        } catch (Exception e) {
            System.out.printf("Something went wrong in add Trader try again");
        }
    }

    public void showAssets(){
        assets.stream().forEach(asset -> asset.showAsset());
    }

    public void showTraders(){
        traders.stream().forEach(trader -> trader.showTrader());
    }

    public void buyAsset(){
        try{
            Asset target = null;
            showTraders();
            System.out.print("Enter the trader id: ");
            int traderId = Integer.parseInt(sc.nextLine());
            Trader targetTrader = null;
            for (Trader t : traders){
                if (t.getId() == traderId){
                    targetTrader = t;
                }
            }
            if (targetTrader == null){
                System.out.println("Trader not found!");
            }else {
                showAssets();
                System.out.print("Enter the asset Code: ");
                int choice = Integer.parseInt(sc.nextLine());
                for (Asset a : assets){
                    if (a.getCode() == choice){
                        target = a;
                    }
                }
                if (target == null){
                    System.out.println("Asset not found!");
                }else {
                    System.out.println("Enter the " + target.getName() + " Quantity : ");
                    int quantity = Integer.parseInt(sc.nextLine());
                    if (quantity <= 0){
                        System.out.println("Quantity should be more than 0!");
                    }else {
                        BigDecimal q = new BigDecimal(quantity);
                        BigDecimal price =target.getUnitPrice();
                        if (price.multiply(q).compareTo(targetTrader.getSolde()) < 0){
                            Portfolio portfolio = null;
                            for (Portfolio p : portfolios){
                                if (p.getTrader().getId() == targetTrader.getId()){
                                    portfolio = p;
                                }
                            }
                            if (portfolio == null){
                                System.out.println("Portfolio not found");
                            }else{
                                portfolio.addAssetToPortfolio(target);
                                portfolio.addToPortfolio(target , quantity);
                            }
                        }else {
                            System.out.println("The trader doesnt have enough money!");
                        }

                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Something thing went wrong in by asset.");
        }
    }

    public void sellAsset(){
        try {
            showTraders();
            System.out.print("Enter Trader id: ");
            int traderId = Integer.parseInt(sc.nextLine());
            if (traders.stream().anyMatch(trader -> trader.getId() == traderId)){
                Optional<Trader> trader = traders.stream()
                        .filter(trader1 -> trader1.getId() == traderId)
                        .findFirst();
                Trader targetTrader = trader.get();
                showAssets();
                System.out.print("Enter the Asset code: ");
                int assetCode = Integer.parseInt(sc.nextLine());
                if (assets.stream().anyMatch(asset -> asset.getCode() == assetCode)){
                    Optional<Asset> asset = assets.stream().filter(asset1 -> asset1.getCode() == assetCode).findFirst();
                    Asset targetAsset = asset.get();
                    Optional<Portfolio> traderPortfolio = portfolios.stream().filter(portfolio -> portfolio.getTrader().getId() == traderId).findFirst();
                    Portfolio portfolio = traderPortfolio.get();
                    System.out.print("Enter the quantity of "+ targetAsset.getName() + " Quantity : ");
                    int quantity = Integer.parseInt(sc.nextLine());
                    if (quantity > 0){

                    }
                }else {
                    System.out.println("No asset found.");
                }
            }else {
                System.out.println("No trader found.");
            }

            
        }catch (Exception e){
            System.out.println("Something went wrong in sell asset.");
        }
    }
}

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
    private static List<Transaction> transactions;
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

    public static List<Transaction> getTransactions() {
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

    public void showPortfolio(){
        portfolios.stream().forEach(portfolio -> portfolio.showPortfolio());
    }

    public void showHistory() {
        transactions.stream().forEach(transaction -> transaction.showTransaction());
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
                        if (price.multiply(q).compareTo(targetTrader.getSolde()) <= 0){
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
                                targetTrader.setSolde(targetTrader.getSolde().subtract(price.multiply(q)));
                                transactions.add(new Transaction(targetTrader , target , quantity , "Buy transaction"));
                                System.out.println("Asset bought successfully.");
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
                        portfolio.removeFromPortfolio(targetAsset , quantity);
                        BigDecimal q = new BigDecimal(quantity);
                        targetTrader.setSolde(targetTrader.getSolde().add(targetAsset.getUnitPrice().multiply(q)));
                        transactions.add(new Transaction(targetTrader , targetAsset , quantity , "Sell Transaction"));
                        System.out.println(targetAsset.getUnitPrice().multiply(q) + "DH added to trader sold.");
                    }else{
                        System.out.println("Quantity cannot be less than 0.");
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

    public void showTransactionsByTrader(){
        try {
            showTraders();
            System.out.print("Enter the trader id: ");
            int traderId = Integer.parseInt(sc.nextLine());
            if (traders.stream().anyMatch(trader -> trader.getId() == traderId)){
                Trader targetTrader = traders.stream().filter(trader -> trader.getId() == traderId).findFirst().get();
                transactions.stream().filter(transaction -> transaction.getTrader().getId() == targetTrader.getId()).forEach(transaction -> transaction.showTransaction());
            }else {
                System.out.println("Trader not found.");
            }

        } catch (Exception e) {
            System.out.println("Something went wrong while showing trader transactions.");
        }
    }

    public void filterByType(){
        try {
            if (assets.isEmpty()){
                System.out.println("No transactions found.");
            }else {
                System.out.println("Buy transactions");
                assets.stream().filter(asset -> asset.getType() == "Buy transaction").forEach(asset -> asset.showAsset());
                System.out.println("Sell transactions");
                assets.stream().filter(asset -> asset.getType() == "Sell Transaction").forEach(asset -> asset.showAsset());
            }
        } catch (Exception e) {
            System.out.println("Something went wrong while filtering transactions by type.");
        }
    }

    public void sortByDate() {
        try {
            if (transactions.isEmpty()){
                System.out.println("No transaction found.");
            }else {
                List<Transaction> transactionStream = transactions.stream().sorted(Comparator.comparing(Transaction::getDate).reversed()).toList();
                transactionStream.forEach(Transaction::showTransaction);
            }
        } catch (Exception e) {
            System.out.println("Something went wrong while sorting transactions by date.");
        }
    }

    public void sortByAmount() {
        try {
            if (transactions.isEmpty()){
                System.out.println("No transaction found.");
            }else {
                List<Transaction> transactionListStored = transactions.stream().sorted(Comparator.comparing(Transaction::getPrice).reversed()).toList();
                transactionListStored.forEach(Transaction::showTransaction);
            }
        } catch (Exception e) {
            System.out.println("Something went wrong while sorting transactions by amount.");
        }
    }

    public void totalVolumePerAsset() {
        try {
            showAssets();
            System.out.print("Enter asset code: ");
            int assetId = Integer.parseInt(sc.nextLine());
            if (assets.stream().anyMatch(asset -> asset.getCode() == assetId)){
                if (transactions.stream().anyMatch(transaction -> transaction.getAsset().getCode() == assetId)){
                    List<Transaction> assetTransactions = transactions.stream().filter(transaction -> transaction.getAsset().getCode() == assetId).toList();
                    BigDecimal total = BigDecimal.valueOf(0.0);
                    for (Transaction t : assetTransactions){
                        BigDecimal totalPrice = t.getPrice().multiply(BigDecimal.valueOf(t.getQuantity()));
                        total = total.add(totalPrice);
                    }
                    System.out.println("Total Volume amount : " + total);
                }else {
                    System.out.println("No transaction found for this asset.");
                }
            }else {
                System.out.println("Asset not found.");
            }
        }catch (Exception e){
            System.out.println("Something went wrong while showing total volume per asset.");
        }
    }

    public void totalBuyAmount() {
        try {
            showAssets();
            System.out.print("Enter asset code: ");
            int assetId = Integer.parseInt(sc.nextLine());
            if (assets.stream().anyMatch(asset -> asset.getCode() == assetId)){
                Asset targetAsset = assets.stream().filter(asset -> asset.getCode() == assetId).findFirst().get();
                List<Transaction> assetTransaction = transactions.stream().filter(transaction -> transaction.getAsset() == targetAsset && Objects.equals(transaction.getType(), "Buy transaction")).toList();
                if (assetTransaction.isEmpty()) {
                    System.out.println("No transaction with this asset.");
                }else {
                    BigDecimal total = new BigDecimal(0);
                    for (Transaction t : assetTransaction){
                        BigDecimal quantity = new BigDecimal(t.getQuantity());
                        total = total.add(t.getPrice().multiply(quantity));
                    }
                    System.out.println("Total buy amount for " + targetAsset.getName() + " : " + total + "DH.");
                }
            }else {
                System.out.println("Asset not found.");
            }
        } catch (Exception e) {
            System.out.println("Something went wrong while calculating total buy amount.");
        }
    }

    public void totalSellAmount() {
        try {
            showAssets();
            System.out.print("Enter asset code: ");
            int assetId = Integer.parseInt(sc.nextLine());
            if (assets.stream().anyMatch(asset -> asset.getCode() == assetId)){
                Asset targetAsset = assets.stream().filter(asset -> asset.getCode() == assetId).findFirst().get();
                List<Transaction> assetTransaction = transactions.stream().filter(transaction -> transaction.getAsset() == targetAsset && Objects.equals(transaction.getType(), "Sell Transaction")).toList();
                if (assetTransaction.isEmpty()) {
                    System.out.println("No transaction with this asset.");
                }else {
                    BigDecimal total = new BigDecimal(0);
                    for (Transaction t : assetTransaction){
                        BigDecimal quantity = new BigDecimal(t.getQuantity());
                        total = total.add(t.getPrice().multiply(quantity));
                    }
                    System.out.println("Total buy amount for " + targetAsset.getName() + " : " + total + "DH.");
                }
            }else {
                System.out.println("Asset not found.");
            }
        } catch (Exception e) {
            System.out.println("Something went wrong while calculating total buy amount.");
        }
    }

    public void totalVolumePerTrader() {
        try {

        } catch (Exception e) {
            System.out.println("Something went wrong while calculating total volume per trader.");
        }
    }

    public void totalNumberOfOrders() {
    }

    public void rankTopNTraders() {
    }

}

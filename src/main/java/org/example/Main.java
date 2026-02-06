package org.example;

import java.util.List;
import java.util.Scanner;



public class Main {
    static Scanner sc = new Scanner(System.in);
    static Platform xTrade = new Platform("XTrade");
    static void showMenu(){
        try {
            int choice;
            do {
                System.out.println("\n=====================================");
                System.out.println("      XTrade Trading Platform");
                System.out.println("=====================================");
                System.out.println("1. Add Trader");
                System.out.println("2. Add Asset");
                System.out.println("3. Show All Assets");
                System.out.println("4. Show All Traders");
                System.out.println("5. Show Portfolios");
                System.out.println("6. Buy Asset");
                System.out.println("7. Sell Asset");
                System.out.println("8. Transaction History");
                System.out.println("9. Export History (CSV)");
                System.out.println("-------------------------------------");
                System.out.println("10. Transaction Analysis");
                System.out.println("11. Trader Performance Analysis");
                System.out.println("12. Global Market Analysis");
                System.out.println("0. Exit");
                System.out.println("=====================================");
                System.out.print("Choose an option: ");


                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        xTrade.addTrader();
                        break;

                    case 2:
                        xTrade.addAsset();
                        break;

                    case 3:
                        xTrade.showAssets();
                        break;

                    case 4:
                        xTrade.showTraders();
                        break;

                    case 5:
                        xTrade.showPortfolio();
                        break;

                    case 6:
                        xTrade.buyAsset();
                        break;

                    case 7:
                        xTrade.sellAsset();
                        break;

                    case 8:
                        xTrade.showHistory();
                        break;

                    case 9:
                        ExcelGenerator.execute();
                        break;

                    case 10:
                        analysisMenu();
                        break;

                    case 11:
                        traderMenu();
                        break;

                    case 12:
                        marketMenu();
                        break;

                    case 0:
                        System.out.println("Exiting XTrade... Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }

            } while (choice != 0);
        } catch (Exception e) {
            System.out.println("Something went wrong try again.");
            showMenu();
        }
        sc.close();
    }
    static void analysisMenu(){
        try {
            int choice;
            do {
                System.out.println("\n=====================================");
                System.out.println("      XTrade Trading Platform");
                System.out.println("=====================================");
                System.out.println("1. Transaction Analysis");
                System.out.println("2. Trader Performance Analysis");
                System.out.println("3. Global Market Analysis");
                System.out.println("0. Exit");
                System.out.println("=====================================");
                System.out.print("Choose an option: ");
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1 -> transactionMenu();
                    case 2 -> traderMenu();
                    case 3 -> marketMenu();
                    case 0 -> System.out.println("Returning to main menu...");
                    default -> System.out.println("Invalid option. Try again.");
                }
            }while (choice != 0);
        } catch (Exception e) {
            System.out.println("Something went wrong try again.");
            analysisMenu();
        }
    }

    static void transactionMenu(){
        try {
            int choice;
            do {
                System.out.println("\n=====================================");
                System.out.println("       TRANSACTION ANALYSIS");
                System.out.println("=====================================");
                System.out.println("1. Show transactions by trader");
                System.out.println("2. Filter transactions by type (BUY / SELL)");
                System.out.println("3. Sort transactions by date");
                System.out.println("4. Sort transactions by amount");
                System.out.println("5. Total volume per asset");
                System.out.println("6. Total BUY amount");
                System.out.println("7. Total SELL amount");
                System.out.println("0. Back to Main Menu");
                System.out.println("=====================================");
                System.out.print("Choose an option: ");
                choice = Integer.parseInt(sc.nextLine());
                switch (choice){
                    case 1 -> xTrade.showTransactionsByTrader();
                    case 2 -> xTrade.filterByType();
                    case 3 -> xTrade.sortByDate();
                    case 4 -> xTrade.sortByAmount();
                    case 5 -> xTrade.totalVolumePerAsset();
                    case 6 -> xTrade.totalBuyAmount();
                    case 7 -> xTrade.totalSellAmount();
                    case 0 -> System.out.println("Returning to main menu...");
                    default -> System.out.println("Invalid option. Try again.");
                }

            }while (choice!=0);


        } catch (Exception e) {
            System.out.println("Something went wrong try again.");
            transactionMenu();
        }
    }

    static void traderMenu(){
        try {
            int choice;
            do {
                System.out.println("\n=====================================");
                System.out.println("    TRADER PERFORMANCE ANALYSIS");
                System.out.println("=====================================");
                System.out.println("1. Total volume per trader");
                System.out.println("2. Total number of orders");
                System.out.println("3. Rank Top N traders by volume");
                System.out.println("0. Back to Main Menu");
                System.out.println("=====================================");
                System.out.print("Choose an option: ");
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1 -> xTrade.totalVolumePerTrader();
                    case 2 -> xTrade.totalNumberOfOrders();
                    case 3 -> xTrade.rankTopNTraders();
                    case 0 -> System.out.println("Returning to main menu...");
                    default -> System.out.println("Invalid option. Try again.");
                }
            }while (choice!=0);


        } catch (Exception e) {
            System.out.println("Something went wrong try again.");
            traderMenu();
        }
    }

    static void marketMenu(){
        try {
            int choice;
            do {
            System.out.println("\n=====================================");
            System.out.println("      GLOBAL MARKET ANALYSIS");
            System.out.println("=====================================");
            System.out.println("1. Total volume per instrument");
            System.out.println("2. Most traded instrument");
            System.out.println("3. Total BUY amount (Global)");
            System.out.println("4. Total SELL amount (Global)");
            System.out.println("0. Back to Main Menu");
            System.out.println("=====================================");
            System.out.print("Choose an option: ");
            choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1 -> xTrade.totalVolumePerInstrument();
                    case 2 -> xTrade.mostTradedInstrument();
                    case 3 -> xTrade.totalGlobalBuyAmount();
                    case 4 -> xTrade.totalGlobalSellAmount();
                    case 0 -> System.out.println("Returning to main menu...");
                    default -> System.out.println("Invalid option. Try again.");
                }
            }while (choice!=0);
        } catch (Exception e) {
            System.out.println("Something went wrong try again.");
            marketMenu();
        }
    }
    static void main() {
        showMenu();
    }
}

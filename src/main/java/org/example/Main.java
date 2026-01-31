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
    static void main() {
        showMenu();
    }
}

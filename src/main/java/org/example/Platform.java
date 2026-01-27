package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Platform {
    Scanner sc = new Scanner(System.in);
    private List<Trader> traders;
    private List<Asset> assets;
    private List<Transaction> transactions;

    public Platform(){
        this.assets = new ArrayList<>();
        this.traders = new ArrayList<>();
        this.transactions = new ArrayList<>();
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

            if (choice == 1){

            }


        } catch (NumberFormatException e) {
            System.out.printf("Wrong input format try again.");
        } catch (Exception e) {
            System.out.printf("Something went wrong try again");
        }
    }
}

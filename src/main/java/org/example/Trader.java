package org.example;

import java.math.BigDecimal;

public class Trader extends Person{
    private BigDecimal solde;
    private Portfolio portfolio;

    public Trader(String name, int age, BigDecimal solde) {
        super(name, age);
        this.solde = solde;
    }

    public BigDecimal getSolde() {
        return solde;
    }

    public void setSolde(BigDecimal solde) {
        this.solde = solde;
    }

    public Portfolio getPortfolio() {
        if (portfolio == null){
            System.out.println("No portfolio found.");
            return null;
        }else {
            return portfolio;
        }
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public void showTrader(){
        System.out.println("Id : " + super.getId() +" - Name : " + super.getName() + " - Age : " + this.getAge() + " - Solde : " + this.solde + "DH.");
    }
}

package org.example;

import java.math.BigDecimal;

public class Trader extends Person{
    private BigDecimal solde;
    private Portfolio portfolio;

    public Trader(String name, int age, Portfolio portfolio, BigDecimal solde) {
        super(name, age);
        this.portfolio = portfolio;
        this.solde = solde;
    }

    public BigDecimal getSolde() {
        return solde;
    }

    public void setSolde(BigDecimal solde) {
        this.solde = solde;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }
}

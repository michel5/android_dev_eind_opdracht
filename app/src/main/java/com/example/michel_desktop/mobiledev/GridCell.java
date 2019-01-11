package com.example.michel_desktop.mobiledev;

public class GridCell {
    private String cointag;
    private double balance;

    /**
     * Gridcell constructor
     * @param cointag cointag van de balance
     * @param balance balance
     */
    public GridCell(String cointag, double balance) {
        this.cointag = cointag;
        this.balance = balance;
    }

    public String getCointag() {
        return cointag;
    }

    public void setCointag(String cointag) {
        this.cointag = cointag;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

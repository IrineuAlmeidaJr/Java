package com.company;

import java.util.ArrayList;

public class Customer {

    private String name;
    private ArrayList<Double> transactions;

    // Conferir se tem 'transaction' se é adicionado ou não, se é usado.
    public Customer(String name, Double transaction) {
        this.name = name;
        this.transactions = new ArrayList<Double>();
        addTransaction(transaction);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getTransactions() {
        return transactions;
    }

    public void addTransaction(double transaction) {
        this.transactions.add(transaction); //autoboxing
    }



}

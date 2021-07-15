package com.company;

import java.util.ArrayList;

public class Bank {

    private String name;
    private ArrayList<Branch> branches;

    public Bank(String name) {
        this.name = name;
        this.branches = new ArrayList<Branch>();
    }

    public boolean addBranch(String nameBranch) {
        if(findBranch(nameBranch) == null) {
            branches.add(new Branch(nameBranch));
            return true;
        }
        return false;
    }

    public boolean addCustomer(String nameBranch, String nameCustomer, double transaction) {
        Branch existBranch = findBranch(nameBranch);
        if(existBranch != null) {
            return existBranch.newCustomer(nameCustomer, transaction);
        }
        return false;
    }

    public boolean addCustomerTransaction(String nameBranch, String nameCustomer, double transaction) {
        Branch existBranch = findBranch(nameBranch);
        if(existBranch != null) {
            return existBranch.addCustomerTransaction(nameCustomer, transaction);
        }
        return false;
    }

    private Branch findBranch(String nameBranch) {
        for(int i=0; i<this.branches.size(); i++) {
            Branch checkedBranch = this.branches.get(i);
            if(checkedBranch.getName().equalsIgnoreCase(nameBranch)) {
                return checkedBranch;
            }
        }
        return null;
    }

    public boolean listCustomers(String nameBranch, boolean printTransactions) {
        Branch existBranch = findBranch(nameBranch);
        if(existBranch != null) {
            System.out.println("Customer details for branch " + existBranch.getName());
            ArrayList<Customer> branchCustomers = existBranch.getCustomers();
            for(int i=0; i<branchCustomers.size(); i++) {
                Customer branchCustomer = branchCustomers.get(i);
                System.out.println("Customer: " + branchCustomer.getName() + "[" + (i+1) + "]");
                if(printTransactions) {
                    System.out.println("Transactions");
                    ArrayList<Double> transactions = branchCustomer.getTransactions();
                    for (int j = 0; j < transactions.size(); j++) {
                        System.out.println("[" + (j + 1) + "]  Amount " + transactions.get(j));
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }
}

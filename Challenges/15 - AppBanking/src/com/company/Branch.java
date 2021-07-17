package com.company;

import java.util.ArrayList;

public class Branch {

    private String name;
    private ArrayList<Customer> customers;

    public Branch(String name) {
        this.name = name;
        this.customers = new ArrayList<Customer>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public boolean newCustomer(String nameCustomer, double transaction) {
        if(findCustomer(nameCustomer) == null) {
            this.customers.add(new Customer(nameCustomer, transaction));
            return true;
        }
        return false;
    }

    public boolean addCustomerTransaction(String nameCustomer, double transaction) {
        Customer existCustomer = findCustomer(nameCustomer);
        if(existCustomer != null) {
            existCustomer.addTransaction(transaction);
            return true;
        }
        return false;
    }

    private Customer findCustomer(String nameCustomer) {
        for(int i=0; i<this.customers.size(); i++){
            Customer checkedCustomer = this.customers.get(i);
            if(checkedCustomer.getName().equalsIgnoreCase(nameCustomer)) {
                return checkedCustomer;
            }
        }
        return null;
    }
}

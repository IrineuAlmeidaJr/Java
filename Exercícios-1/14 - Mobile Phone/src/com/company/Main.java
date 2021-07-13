package com.company;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static MobilePhone mobilePhone = new MobilePhone("(18) 99112-9232");

    public static void main(String[] args) {
        boolean quit = false;
        startPhone();
        printActions();
        while(!quit) {
            int action = scanner.nextInt();
            scanner.nextLine();

            switch(action) {
                case 0:
                    System.out.println("Shuttin down...");
                    quit = true;
                    break;
                case 1:
                    mobilePhone.printContacts();
                    break;
                case 2:
                    addNewContact();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                     removeContact();
                     break;
                case 5:
                    queryContact();
                    break;
                case 6:
                    printActions();
                    break;
            }

            System.out.println("\nEnter action: 6 to show available actions.");

        }
    }

    private static void addNewContact() {
        System.out.print("Enter new contact name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        Contact newContact = Contact.createContact(name, phone);
        if(mobilePhone.addNewContact(newContact)) {
            System.out.println("New contact added: name = " + name + ", phone = " + phone);
        } else {
            System.out.println("Cannot add, " + name + " already on file.");
        }
    }

    private static void updateContact() {
        System.out.print("Enter existing contact name: ");
        String name = scanner.nextLine();
        Contact existingContactRecord = mobilePhone.queryContact(name);
        if(existingContactRecord == null) {
            System.out.println("Contact not found.");
            return;
        }

        System.out.print("Enter new contact name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new contact phone number: ");
        String newPhone = scanner.nextLine();
        Contact newContact = Contact.createContact(newName, newPhone);

        if(mobilePhone.updateContact(existingContactRecord, newContact)) {
            System.out.println("Successfully update record.");
        } else {
            System.out.println("Erro updating record.");
        }
    }

    private static void removeContact() {
        System.out.print("Enter existing contact name: ");
        String name = scanner.nextLine();
        Contact existingContactRecord = mobilePhone.queryContact(name);
        if (existingContactRecord == null) {
            System.out.println("Contact not found.");
            return;
        }
        if (mobilePhone.removeContact(existingContactRecord)) {
            System.out.println("Successfully deleted.");
        } else {
            System.out.println("Erro deleting contact.");
        }
    }

    private static void queryContact() {
        System.out.print("Enter existing contact name: ");
        String name = scanner.nextLine();
        Contact existingContactRecord = mobilePhone.queryContact(name);
        if (existingContactRecord == null) {
            System.out.println("Contact not found.");
        } else {
            System.out.println("Name = " + existingContactRecord.getName() +
                    "\nPhone number = " + existingContactRecord.getPhoneNumber());
        }
    }

    private static void startPhone() {
        System.out.println("Starting phone...");
    }

    private static void printActions() {
        System.out.println( "- - - M E N U - - -\n" +
                            "0 - o shutdown.\n" +
                            "1 - Print list of contacts.\n" +
                            "2 - Add a new contact.\n" +
                            "3 - Update an  existing contact.\n" +
                            "4 - Remove a contact.\n" +
                            "5 - Search a contact.\n" +
                            "6 - Print a list of available actions.\n");
        System.out.print("Choose an option: ");

    }


}

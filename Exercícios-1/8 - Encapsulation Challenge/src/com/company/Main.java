package com.company;

public class Main {

    public static void main(String[] args) {
//	    PrinterMe printerMe = new PrinterMe(50, false);
//        System.out.println(printerMe.getTonerLevel());
//        printerMe.print(10);
//        System.out.println(printerMe.getTonerLevel());
//        printerMe.print(15);
//        System.out.println(printerMe.getTonerLevel());
//        System.out.println("Number of pages printed = " + printerMe.getPagesPrinted());

        Printer printer = new Printer(50, false);
        System.out.println("initial page count = " + printer.getPagesPrinted());
        int pagesPrinted = printer.printPages(4);
        System.out.println("Pages printed was " + pagesPrinted + " new total print count for printer = " + printer.getPagesPrinted());
        pagesPrinted = printer.printPages(2);
        System.out.println("Pages printed was " + pagesPrinted + " new total print count for printer = " + printer.getPagesPrinted());

    }
}

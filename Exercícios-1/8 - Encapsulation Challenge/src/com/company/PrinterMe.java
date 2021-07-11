package com.company;

public class PrinterMe {

    private double tonerLevel;
    private int pagesPrinted;
    private boolean isDuplex;

    public PrinterMe(int tonerLevel, boolean isDuplex) {
        this.tonerLevel = isValidValue(tonerLevel) ? tonerLevel : 100;
        this.isDuplex = isDuplex;
        pagesPrinted = 0;
    }

    public String getTonerLevel() {
        return tonerLevel + "%";
    }

    public int getPagesPrinted() {
        return pagesPrinted;
    }

    public boolean isDuplex() {
        return isDuplex;
    }

    public void print(int pages) {
        for(int i = 0; i < pages; i++) {
            tonerLevel -= 0.5;
            System.out.println("Pages " + i + " printed");
            pagesPrinted++;
        }
        System.out.println("End Printed\n-------------------------\n");
    }

    public void addNewToner() {
        this.tonerLevel = 100;
    }

    private boolean isValidValue(int number) {
        return number > 0 && number <=100;
    }


}

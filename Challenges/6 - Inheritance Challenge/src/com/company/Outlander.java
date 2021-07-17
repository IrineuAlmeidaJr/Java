package com.company;

public class Outlander extends Car{

    private int roadServiceMonths; // é uma modalidade de serviço que se quebrar tem suporte, como uma garantia

    public Outlander(int roadServiceMonths) {
        super("Outlander", "4WD", 5, 5, 6, false);
        this.roadServiceMonths = roadServiceMonths;
    }

    public void accelerate(int rate) {

        int newVelocity = getCurrentVelocity() + rate;
        if(newVelocity == 0) {
            stop();
            changerGear(1);
        } else if(newVelocity > 0 && newVelocity <= 10) {
            changerGear(1);
        } else if(newVelocity > 10 && newVelocity <= 20) {
            changerGear(2);
        } else if(newVelocity > 20 && newVelocity <= 30 ) {
            changerGear(3);
        } else {
            changerGear(4);
        }


        if(newVelocity > 0) {
            changeVelocity(newVelocity, getCurrentDirection());
        }

    }


}

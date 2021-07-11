package com.company;

public class LivingRoom {

    private Dimension dimension;
    private Sofa sofa;
    private TV television;

    public LivingRoom(Dimension dimension, Sofa sofa, TV television) {
        this.dimension = dimension;
        this.sofa = sofa;
        this.television = television;
    }

    public void tvPowerUp() {
        System.out.println("TV ON - " + television.getModel() + ".");
    }
}

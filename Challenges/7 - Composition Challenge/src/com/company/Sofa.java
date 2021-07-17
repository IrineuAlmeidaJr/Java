package com.company;

public class Sofa {

    private Dimension dimension;
    private int seatsSofa;

    public Sofa(Dimension dimension, int seatsSofa) {
        this.dimension = dimension;
        this.seatsSofa = seatsSofa;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public int getSeatsSofa() {
        return seatsSofa;
    }

}

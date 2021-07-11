package com.company;

public class Main {

    public static void main(String[] args) {
        Dimension dimension = new Dimension(500, 300);
        Sofa sofa = new Sofa(new Dimension(100, 30), 3);
        TV television = new TV("SM - 7000_4K", "Samsung", 50);

        LivingRoom livingRoom = new LivingRoom(dimension, sofa, television);

        livingRoom.tvPowerUp();




    }
}

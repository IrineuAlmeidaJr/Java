package com.company;

class Car {
    private String name;
    private int cylinders;
    private int wheels;
    private boolean engine;

    public Car(String name, int cylinders) {
        this.name = name;
        this.cylinders = cylinders;
        this.wheels = 4;
        this.engine = false;
    }

    public String getName() {
        return name;
    }

    public int getCylinders() {
        return cylinders;
    }

    public void startEngine() {
        System.out.println("Car -> startEngine");
    }

    public void accelerate() {
        System.out.println("Car -> Accelerate");
    }

    public void brake() {
        System.out.println("Car -> Brake");
    }
}

class Ford extends Car {
    public Ford(String name, int cylinders) {
        super(name, cylinders);
    }

    @Override
    public void startEngine() {
        System.out.println("Car -> startEngine - Ford");
    }

    @Override
    public void accelerate() {
        System.out.println("Car -> Accelerate - Ford");
    }

    @Override
    public void brake() {
        System.out.println("Car -> Brake - Ford");
    }
}

class Holden extends Car {
    public Holden(String name, int cylinders) {
        super(name, cylinders);
    }

    @Override
    public void startEngine() {
        System.out.println("Car -> startEngine - Holden");
    }

    @Override
    public void accelerate() {
        System.out.println("Car -> Accelerate - Holden");
    }

    @Override
    public void brake() {
        System.out.println("Car -> Brake - Holden");
    }
}

public class Main {

    public static void main(String[] args) {
        Car baseCar = new Car("baseCar", 50);
        baseCar.startEngine();
        baseCar.accelerate();
        baseCar.brake();

        Holden sandero = new Holden("Sandero", 95);
        sandero.startEngine();
        sandero.accelerate();
        sandero.brake();

        Ford ford = new Ford("F250", 150);
        ford.startEngine();
        ford.accelerate();
        ford.brake();

        Holden holden = new Holden("Holden Commodore", 150);
        holden.startEngine();
        holden.accelerate();
        holden.brake();


    }
}

package com.company;

public class Point {

    private int x;
    private int y;

    public Point() {
        this(0, 0);
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double distance() {
        return Math.sqrt(Math.pow(-x, 2) + Math.pow(-y, 2));
    }

    public double distance(int x, int y) {
        return Math.sqrt(Math.pow(x-this.x, 2) + Math.pow(y-this.y, 2));
    }

    public double distance(Point second) {
        return Math.sqrt(Math.pow(second.getX()-this.x, 2) + Math.pow(second.getY()-this.y, 2));
    }

}

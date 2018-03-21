package ru.stqa.pft.sandbox;

public class Point {

    private double a;
    private double b;

    public Point(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double distance (Point p2) {
        return Math.sqrt(((p2.a - a) * (p2.a - a)) + ((p2.b - b) * (p2.b - b)));
    }

}

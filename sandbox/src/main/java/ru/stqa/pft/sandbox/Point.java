package ru.stqa.pft.sandbox;

public class Point {

    private double a;
    private double b;

    public Point(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public Point () {

    }

    public double distance (Point p1, Point p2) {
        return Math.sqrt(((p2.a - p1.a) * (p2.a - p1.a)) + ((p2.b - p1.b) * (p2.b - p1.b)));
    }

    public static void main(String[] args) {
        Point p1 = new Point(0,0);
        Point p2 = new Point(0,0);

        Point p = new Point();

        System.out.println(p.distance(p1,p2));
    }
}

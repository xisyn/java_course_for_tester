package ru.stqa.pft.sandbox;

public class NotFirstHW {

    public static void main(String[] args) {
        hello("world");
        hello("user");

        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(4,6);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

        Point p1 = new Point(0,0);
        Point p2 = new Point(1,1);
        Point p3 = new Point(2,2);
        Point p4 = new Point(4,4);

        System.out.println(distance(p1,p2));
        System.out.println(distance(p2,p3));
        System.out.println(distance(p2, p4));

    }

    public static void hello(String somebody) {
        System.out.println("Hello " + somebody + "!");
    }

    public static double distance(Point p1, Point p2) {
        Point p = new Point();
        return p.distance(p1, p2);
    }

}
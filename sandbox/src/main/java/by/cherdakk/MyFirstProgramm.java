package by.cherdakk;

public class MyFirstProgramm {

  public static void main(String[] args) {
    System.out.println("Hello, World!");

    Point p1 = new Point(2,2);
    Point p2 = new Point(1,1);
    System.out.println(distance(p1, p2));

  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt((p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y));
  }

}

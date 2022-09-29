package WorkingWithAbstraction.Lab.PointInRectangle;

public class Rectangle {
    private Point pointA;
    private Point pointC;

    public Rectangle(Point a, Point c) {
        this.pointA = a;
        this.pointC = c;
    }

    public boolean contains(Point pointX) {
        return pointX.greaterOrEqual(pointA) && pointX.lessOrEqual(pointC);
    }
}

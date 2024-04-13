package util;

public class Point {
    private double x, y;

    public Point() {
        this.x = Math.random();
        this.y = Math.random();
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean isInCircle() {
        return x*x + y*y < 1;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

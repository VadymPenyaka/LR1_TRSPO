package util;

public class Point {
    private double x, y;

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

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Метод для віднімання однієї точки від іншої
    public Point subtract(Point p) {
        return new Point(this.x - p.x, this.y - p.y);
    }

    // Метод для знаходження векторного добутку
    public double cross(Point p) {
        return this.x * p.y - this.y * p.x;
    }

    // Метод для знаходження скалярного добутку
    public double dot(Point p) {
        return this.x * p.x + this.y * p.y;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}

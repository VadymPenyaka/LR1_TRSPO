import util.Point;

import java.util.*;

public class GrahamScan {

    public static List<Point> findConvexHull(List<Point> points) {
        if (points.size() < 3) throw new IllegalArgumentException("Мінімум 3 точки потрібно для конвексної оболонки");

        // Знаходимо точку з найменшою y-координатою (або з найменшою x-координатою, якщо y однакові)
        Point start = points.get(0);
        for (Point point : points) {
            if (point.getY() < start.getY() || (point.getY() == start.getY() && point.getX() < start.getX())) {
                start = point;
            }
        }

        List<Point> sortedPoints = new ArrayList<>(points);
        Point finalStart = start;
        sortedPoints.sort((p1, p2) -> {
            double orientation = orientation(finalStart, p1, p2);
            if (orientation == 0) {
                return (int) (distance(finalStart, p1) - distance(finalStart, p2));
            }
            return (orientation > 0) ? -1 : 1;
        });

        Stack<Point> stack = new Stack<>();
        stack.push(sortedPoints.get(0));
        stack.push(sortedPoints.get(1));
        stack.push(sortedPoints.get(2));

        for (int i = 3; i < points.size(); i++) {
            Point top = stack.peek();
            while (orientation(nextToTop(stack), top, points.get(i)) <= 0) {
                stack.pop();
                top = stack.peek();
            }
            stack.push(points.get(i));
        }

        return new ArrayList<>(stack.subList(0, stack.size()));
    }

    private static double orientation(Point a, Point b, Point c) {
        double val = (b.getY() - a.getY()) * (c.getX() - b.getX()) - (b.getX() - a.getX()) * (c.getY() - b.getY());
        if (val == 0) return 0;  // колінеарні
        return (val > 0) ? 1 : -1; // годинникове або проти годинникової стрілки
    }

    private static double distance(Point p1, Point p2) {
        return (p1.getX() - p2.getX()) * (p1.getX() - p2.getX()) + (p1.getY() - p2.getY()) * (p1.getY() - p2.getY());
    }

    private static Point nextToTop(Stack<Point> stack) {
        Point top = stack.pop();
        Point res = stack.peek();
        stack.push(top);
        return res;
    }

}

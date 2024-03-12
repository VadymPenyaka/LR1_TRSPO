import util.GeometryUtils;
import util.Point;

import java.util.List;

public class ConvexHullIntersection {

    // Функція для перевірки перетину двох конвексних оболонок
    public static boolean areConvexHullsIntersecting(List<Point> convexHull1, List<Point> convexHull2) {
        // Перевірка на перетин будь-якої пари ребер між оболонками
        for (int i = 0; i < convexHull1.size(); i++) {
            for (int j = 0; j < convexHull2.size(); j++) {
                int next1 = (i + 1) % convexHull1.size();
                int next2 = (j + 1) % convexHull2.size();

                Point p1 = convexHull1.get(i);
                Point q1 = convexHull1.get(next1);
                Point p2 = convexHull2.get(j);
                Point q2 = convexHull2.get(next2);

                if (GeometryUtils.doIntersect(p1, q1, p2, q2)) {
                    return true; // Якщо хоча б одне ребро перетинається, оболонки перетинаються
                }
            }
        }

        // Перевірка, чи точка однієї оболонки лежить всередині іншої оболонки
        for (Point point : convexHull1) {
            if (isPointInsideConvexHull(point, convexHull2)) {
                return true;
            }
        }

        for (Point point : convexHull2) {
            if (isPointInsideConvexHull(point, convexHull1)) {
                return true;
            }
        }

        // Якщо жодне ребро не перетинається і жодна точка не лежить всередині іншої оболонки
        return false;
    }

    // Використовуємо реалізований раніше метод для перевірки, чи точка лежить всередині конвексної оболонки
    private static boolean isPointInsideConvexHull(Point point, List<Point> convexHull) {
        for (int i = 0; i < convexHull.size(); i++) {
            int next = (i + 1) % convexHull.size();
            Point p1 = convexHull.get(i);
            Point p2 = convexHull.get(next);

            // Якщо точка лежить праворуч від будь-якого ребра, вона не лежить всередині оболонки
            if (GeometryUtils.orientation(p1, point, p2) != 2) {
                return false;
            }
        }
        return true;
    }

}

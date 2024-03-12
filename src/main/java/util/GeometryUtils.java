package util;

public class GeometryUtils {
    public static boolean doIntersect(Point p1, Point q1, Point p2, Point q2) {
        // Розрахунок чотирьох орієнтацій, необхідних для визначення загального випадку
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        // Загальний випадок
        if (o1 != o2 && o3 != o4)
            return true;

        // Спеціальні випадки
        // p1, q1 та p2 колінеарні та p2 лежить на відрізку p1q1
        if (o1 == 0 && onSegment(p1, p2, q1)) return true;

        // p1, q1 та q2 колінеарні та q2 лежить на відрізку p1q1
        if (o2 == 0 && onSegment(p1, q2, q1)) return true;

        // p2, q2 та p1 колінеарні та p1 лежить на відрізку p2q2
        if (o3 == 0 && onSegment(p2, p1, q2)) return true;

        // p2, q2 та q1 колінеарні та q1 лежить на відрізку p2q2
        if (o4 == 0 && onSegment(p2, q1, q2)) return true;

        return false; // Не перетинаються
    }

    // Для визначення орієнтації впорядкованої трійки точок
    // Функція повертає наступні значення:
    // 0 --> p, q та r колінеарні
    // 1 --> Годинникове напрямок
    // 2 --> Проти годинникового напрямку
    public static int orientation(Point p, Point q, Point r) {
        double val = (q.getY() - p.getY()) * (r.getX() - q.getX()) -
                (q.getX() - p.getX()) * (r.getY() - q.getY());

        if (val == 0) return 0;  // колінеарні
        return (val > 0)? 1: 2; // годинникове або проти годинникового напрямку
    }

    // Перевіряє, чи точка q лежить на відрізку pr
    public static boolean onSegment(Point p, Point q, Point r) {
        return q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX()) &&
                q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY());
    }
}

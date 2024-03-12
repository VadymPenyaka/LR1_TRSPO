package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PointsGenerator {

    public static List<Point> generatePoints (int numberOfPoints) {
        Random random = new Random();
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < numberOfPoints; i++) {
            points.add(new Point(random.nextInt(1000), random.nextInt(1000)));
        }
        return points;
    }
}

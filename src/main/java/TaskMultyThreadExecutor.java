import util.Point;

import java.util.List;
import java.util.concurrent.*;

class SeparateCheck implements Callable<Boolean>{
    private List<Point> points1;

    private List<Point> points2;

    public SeparateCheck(List<Point> points1, List<Point> points2) {
        this.points1 = points1;
        this.points2 = points2;
    }

    @Override
    public Boolean call() {
        boolean canSeparate = canSeparate(points1, points2);
//        System.out.println(canSeparate);
        return canSeparate;
    }

    public static boolean canSeparate (List<Point> points1, List<Point> points2) {
        List<Point> convex1 = GrahamScan.findConvexHull(points1);
        List<Point> convex2 = GrahamScan.findConvexHull(points2);

        return ConvexHullIntersection.areConvexHullsIntersecting(convex1, convex2);
    }
}

public class TaskMultyThreadExecutor {
    private List<Point> points1;
    private List<Point> points2;
    private int numberOfThreads;

    public TaskMultyThreadExecutor(List<Point> points1, List<Point> points2, int numberOfThreads) {
        this.points1 = points1;
        this.points2 = points2;
        this.numberOfThreads = numberOfThreads;
    }

    public void run() {
        boolean canSeparate = canSeparate(points1, points2, numberOfThreads);
    }

    public static boolean canSeparate(List<Point> points1, List<Point> points2, int numberOfThreads) {
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        Future<Boolean> set1 = executor.submit(new SeparateCheck(points1, points2));

        try {
            return set1.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        } finally {
            executor.shutdown();
        }

    }
}

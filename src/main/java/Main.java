import util.HistogramGenerator;
import util.InputOutputFromFile;
import util.Point;
import util.PointsGenerator;

import java.util.*;

public class Main {
    private static List<Point> pointsTrue1 = List.of(
            new Point(1, 2),
            new Point(2, 3),
            new Point(3, 1),
            new Point(4, 4),
            new Point(5, 2)
    );

    private static List<Point> pointsTrue2 = List.of(
                new Point(1, 2),
                new Point(2, 3),
                new Point(3, 1),
                new Point(4, 4),
                new Point(5, 2)
        );;
    private static List<Point> pointsFalse1 = List.of(
            new Point(1, 2),
            new Point(2, 3),
            new Point(3, 1)
    );
    private static List<Point> pointsFalse2 = List.of(
            new Point(4, 3),
            new Point(5, 2),
            new Point(5, 3)
    );

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//        InputOutputFromFile.inputDataToFile(PointsGenerator.generatePoints(5000000), "points1.txt");
//        InputOutputFromFile.inputDataToFile(PointsGenerator.generatePoints(5000000), "points2.txt");
        chooseAndPerform();

    }

    private static void chooseAndPerform() {
        System.out.println("ЛР-1 Пеняка");
        System.out.println("Виберіть дію:\n1 - вибрати випадкові випадкові значення для обчислення. \n2 - вибрати значення з файлу.");

        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                performAndGetThreadsExecutionTimeWithRandomData();
                break;
            case 2:
                performAndGetThreadsExecutionTimeWithFileData();
                break;
            default:
                System.out.println("Дії під таким номером не існує");

        }
    }

    public static void performAndGetThreadsExecutionTimeWithRandomData () {
        System.out.println("Введіть кількість точок:");
        long numberOfPoints = scanner.nextLong();
        if (numberOfPoints>3)
            performOperations(PointsGenerator.generatePoints(numberOfPoints), PointsGenerator.generatePoints(numberOfPoints));
        else
            System.out.println("Ви ввели замалу кількість точок.");
    }

    public static void performAndGetThreadsExecutionTimeWithFileData () {
        List<Point> points1 = InputOutputFromFile.outputDataFromFile("points1.txt");
        List<Point> points2 = InputOutputFromFile.outputDataFromFile("points2.txt");

        performOperations(points1, points2);
    }

    private static void performOperations (List<Point> points1, List<Point> points2) {
        TaskMultyThreadExecutor taskMultyThreadExecutor = new TaskMultyThreadExecutor(points1, points2, 2);

        taskMultyThreadExecutor.run();


        Map<Integer, Long> numberOfThreadsAndExecutionTimeMap = new HashMap<>();


        for (int i=1; i<10; i++) {
            long start = System.currentTimeMillis();
            TaskMultyThreadExecutor taskExecutor = new TaskMultyThreadExecutor(points1, points2, i);
            taskMultyThreadExecutor.run();
            numberOfThreadsAndExecutionTimeMap.put(i, (System.currentTimeMillis())-start);
        }

        new HistogramGenerator (numberOfThreadsAndExecutionTimeMap);
    }

}
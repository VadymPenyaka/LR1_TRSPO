package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputOutputFromFile {


    public static void inputDataToFile (List<Point> points, String fileName) {
        new File(fileName);
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(fileName);
            BufferedWriter b = new BufferedWriter(fileWriter);
            for (Point point:points) {
                fileWriter.write(point.toString() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static List<Point> outputDataFromFile (String fileName) {
        File file = new File(fileName);
        List<Point> points = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                points.add(new Point(Double.valueOf(scanner.next()), Double.valueOf(scanner.next())));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return points;
    }

}

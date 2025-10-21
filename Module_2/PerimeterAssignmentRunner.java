import java.io.*;
import java.util.*;

public class PerimeterAssignmentRunner {
    public static void main (String[] args) {
        processFiles("datatest", 7);
        processFiles("example", 5);
    }

    public static void processFiles(String fileType, int maxLength) {
        // init a ShapeProcessor
        ShapeProcessor shapeProcessor = new ShapeProcessor();

        // loop through files, create shape, and add it to ShapeProcessor
        for (int i = 1; i < maxLength; i++) {
            final String fileName = fileType + i + ".txt";
            Shape shape = new Shape(getPoints(fileName));
            shapeProcessor.addShape(fileName, shape);
        }

        // process shapes
        shapeProcessor.processShapes();

        // get data
        System.out.println("---------------------------------------------------------");
        System.out.println("Largest perimeter: "+ shapeProcessor.getLargestPerimeter());
        System.out.println("Largest perimeter file name: "+ shapeProcessor.getLargestPerimeterFileName());
    }

    private static List<Point> getPoints(String fileName) {
        List<Point> pointList = new ArrayList<>();
        File file = new File("dataFiles", fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(",");
                double x = Double.parseDouble(parts[0].trim());
                double y = Double.parseDouble(parts[1].trim());
                pointList.add(new Point(x, y));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return pointList;
    }
}

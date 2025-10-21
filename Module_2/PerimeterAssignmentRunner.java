import java.io.*;
import java.util.*;

class Point {
    double x, y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double distance(Point other) {
        return Math.sqrt(Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2));
    }
}

class Shape {
    List<Point> points;

    double averageLength;
    double longestSide;

    Shape(List<Point> points) {
        this.points = points;
        this.averageLength = 0;
        this.longestSide = 0;
    }

    double getPerimeter() {
        double p = 0, l = 0;
        for(int i = 0, length = points.size(); i < length; i++) {
            int nextIdx = i+1;
            Point one = points.get(i);
            Point two = nextIdx < length ? points.get(nextIdx) : points.getFirst();
            double distance = one.distance(two);
            if(distance > l) l = distance;
            p += distance;
        }
        averageLength = p / points.size();
        longestSide = l;
        return p;
    }

    double getAvgLength() {
        return averageLength;
    }

    double getLongestSide() {
        return longestSide;
    }
}

class ShapeProcessor {
    private Map<String, Shape> shapes;

    private String largestFileName;
    private double largestPerimeter;

    ShapeProcessor() {
        this.largestPerimeter = 0;
        this.largestFileName = "";

        this.shapes = new HashMap<>();
    }

    public void addShape(String fileName, Shape shape) {
        this.shapes.put(fileName, shape);
    }

    public void processShapes() {
        double lPerimeter = 0;
        String lPerimeterFN = "";
        for (Map.Entry<String, Shape> shape : shapes.entrySet()) {
            double perimeter = shape.getValue().getPerimeter();
            if (perimeter > largestPerimeter) {
                lPerimeter = perimeter;
                lPerimeterFN = shape.getKey();
            }
        }
        this.largestFileName = lPerimeterFN;
        this.largestPerimeter = lPerimeter;
    }

    public double getLargestPerimeter() {
        return largestPerimeter;
    }

    public String getLargestPerimeterFileName() {
        return largestFileName;
    }
}

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

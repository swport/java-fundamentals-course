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
            Point two = nextIdx < length ? points.get(nextIdx) : points.get(0);
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

public class PerimeterAssignmentRunner {
    public static void main (String[] args) {
        double largestPerimeter = 0;
        String largestPerimeterFn = "";
        for (int i = 1; i < 5; i++) {
            List<Point> points = getPoints("example"+i+".txt");
            Shape shape = new Shape(points);
            double perimeter = shape.getPerimeter();
            if (perimeter > largestPerimeter) {
                largestPerimeter = perimeter;
                largestPerimeterFn = "example"+i+".txt";
            }
        }

        System.out.println(largestPerimeterFn);
    }

    public static List<Point> getPoints(String fileName) {
        List<Point> pointList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
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

import java.util.HashMap;
import java.util.Map;

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

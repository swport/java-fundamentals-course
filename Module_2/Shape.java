import java.util.List;

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
        for (int i = 0, length = points.size(); i < length; i++) {
            int nextIdx = i + 1;
            Point one = points.get(i);
            Point two = nextIdx < length ? points.get(nextIdx) : points.getFirst();
            double distance = one.distance(two);
            if (distance > l) l = distance;
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

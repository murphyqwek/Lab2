package org.example.lab2.utils;

public class AreaCheckUtils {
    private final double x;
    private final double y;
    private final double r;

    public AreaCheckUtils(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public boolean isHit() {
        return checkSquare() || checkTriangle() || checkQuadrant();
    }

    private boolean checkQuadrant() {
        return x * x + y * y <= r * r && x <= 0 && y <= 0;
    }

    private boolean checkSquare() {
        return (x >= -r && x <= 0 && y >= 0 && y <= r);
    }

    private boolean checkTriangle() {
        double ax = 0, ay = 0;
        double bx = r/2, by = 0;
        double cx = 0, cy = r/2;

        double v1 = crossProduct(ax, ay, bx, by, x, y);
        double v2 = crossProduct(bx, by, cx, cy, x, y);
        double v3 = crossProduct(cx, cy, ax, ay, x, y);

        return (v1 >= 0 && v2 >= 0 && v3 >= 0) || (v1 <= 0 && v2 <= 0 && v3 <= 0);
    }

    private double crossProduct(double ax, double ay,
                                      double bx, double by,
                                      double cx, double cy) {
        return (bx - ax) * (cy - ay) - (by - ay) * (cx - ax);
    }
}

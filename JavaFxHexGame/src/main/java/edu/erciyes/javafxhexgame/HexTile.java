package edu.erciyes.javafxhexgame;


import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class HexTile extends Polygon {
    private Color strokeColor;

    public HexTile(double centerX, double centerY, double size) {
        super(getHexPoints(centerX, centerY, size));
        this.strokeColor = Color.BLACK; // Default stroke color

        setFill(Color.WHITE);
        setStroke(Color.BLACK);
        setStrokeWidth(2);
    }
    public void setStrokeColor(Color color) {
        this.strokeColor = color;
        this.setStroke(color);
    }


    public static double[] getHexPoints(double centerX, double centerY, double size) {
        double[] points = new double[12];
        for (int i = 0; i < 6; i++) {
            points[i * 2] = centerX + size * Math.cos(Math.PI / 3 * (i + 0.5)); // 90 derece kaydırma
            points[i * 2 + 1] = centerY + size * Math.sin(Math.PI / 3 * (i + 0.5));
        }
        return points;
}
}

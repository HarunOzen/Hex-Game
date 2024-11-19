package edu.erciyes.javafxhexgame;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;



public class HexBoard extends Pane {
    private int boardSize;
    private HexTile hexTile ;
    private double hexSize;
    private double offsetX;
    private double offsetY;
    private Polygon[][] hexagons;


    public HexBoard(int boardSize, double hexSize) {
        this.boardSize = boardSize;
        this.hexSize = hexSize;
        this.hexagons = new Polygon[boardSize][boardSize];

        double cellWidth = hexSize * 1.75;
        double cellHeight = hexSize * 1.5;
        double gridWidth = boardSize * cellWidth;
        double gridHeight = boardSize * cellHeight;
        this.offsetX = (1300 - gridWidth) / 2;
        this.offsetY = (1000 - gridHeight) / 2;

        createHexagonGrid();
    }

    private void createHexagonGrid() {
        double hexagonRadius = hexSize; // Altıgenlerin yarıçapını belirle
        double h = Math.sqrt(3) * hexagonRadius / 2; // Altıgenin yüksekliğini hesapla

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                double centerX = col * 2 * h + row * h + offsetX;
                double centerY = row * 1.5 * hexagonRadius + offsetY;

                HexTile hex = new HexTile(centerX, centerY, hexagonRadius);

                if (row == 0 || row == boardSize - 1) { // İlk ve son satırındaki altıgenler
                    hex.setStrokeColor(Color.BLUE); // Çevresini mavi renkte
                }
                if (col == 0 || col == boardSize - 1) { // İlk ve son sütundaki altıgenler
                    hex.setStrokeColor(Color.RED); // Çevresini kırmızı renkte
                }
                if ((row == 0 || row == boardSize - 1) && (col == 0 || col == boardSize - 1)) { // Suloklardaki altıgenler
                    hex.setStrokeColor(Color.BLACK); // Çevresini mavi renkte
                }



                this.getChildren().add(hex);
                hexagons[row][col] = hex;
            }
        }
    }
    public Polygon[][] getHexagons() {
        return hexagons;
    }

    public int getRow(Polygon hexagon) {
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (hexagons[row][col] == hexagon) {
                    return row;
                }
            }
        }
        return -1; // Not found
    }

    public int getCol(Polygon hexagon) {
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (hexagons[row][col] == hexagon) {
                    return col;
                }
            }
        }
        return -1; // Not found
    }



    public int getBoardSize() {
        return boardSize;
}
}

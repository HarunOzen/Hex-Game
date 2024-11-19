package edu.erciyes.javafxhexgame;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class MainApplication extends Application {
    private Label playerLabel;
    private Label hexagonStatusLabel;
    private HexBoard board;
    private GameCreator gameCreator;
    private static int boyut=5;
    private static int boardSizes=30;
    private StartPane startPane = new StartPane();
    private Players currentPlayer;


    @Override
    public void start(Stage primaryStage) {
        playerLabel = new Label("Turn Blue");
        playerLabel.setStyle("-fx-font-size: 25; -fx-font-weight:bold; -fx-text-fill: white; -fx-font-style: italic;");
        AnchorPane.setTopAnchor(playerLabel, 10.0);
        AnchorPane.setLeftAnchor(playerLabel, 10.0);

        hexagonStatusLabel = new Label();
        hexagonStatusLabel.setStyle("-fx-font-size: 18;");
        AnchorPane.setTopAnchor(hexagonStatusLabel, 40.0);
        AnchorPane.setLeftAnchor(hexagonStatusLabel, 10.0);

        board = new HexBoard(boyut, boardSizes); // 11x11 boyutunda ve 30 birimlik altıgenler
        gameCreator = new GameCreator(boyut); // Oyun modeli için aynı boyutu kullan

        // Dikdörtgeni oluştur
        Rectangle dikdortgenBlue = new Rectangle(150, 45);
        dikdortgenBlue.setFill(Color.BLUE);
        dikdortgenBlue.setStroke(Color.BLACK);



        Rectangle dikdortgenRed = new Rectangle(150, 45);
        dikdortgenRed.setFill(Color.RED);
        dikdortgenRed.setStroke(Color.BLACK);






        HBox hBox1= new HBox(dikdortgenBlue,dikdortgenRed);
        hBox1.setSpacing(2);





        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, new CornerRadii(15), Insets.EMPTY)));
        mainMenuButton.setTextFill(Color.WHITE);
        mainMenuButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        mainMenuButton.setOnAction(e -> showStartPane(primaryStage));
        mainMenuButton.setTranslateX(1400);
        mainMenuButton.setTranslateY(300);

        Button cancelButton = new Button("Exit");
        cancelButton.setBackground(new Background(new BackgroundFill(Color.DARKRED, new CornerRadii(15), Insets.EMPTY)));
        cancelButton.setTextFill(Color.WHITE);
        cancelButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        cancelButton.setOnAction(e -> Platform.exit());
        cancelButton.setTranslateX(1400);
        cancelButton.setTranslateY(350);


        VBox vbox=new VBox(playerLabel,hexagonStatusLabel,hBox1,mainMenuButton,cancelButton);



        AnchorPane root = new AnchorPane();




        root.getChildren().addAll(vbox, board, playerLabel, hexagonStatusLabel);



        // Altıgenlere erişmek için HexBoard sınıfının getHexagons() metodunu kullanın
        Polygon[][] hexagons = board.getHexagons();
        for (int row = 0; row < boyut; row++) {
            for (int col = 0; col <boyut; col++) {
                Polygon hex = hexagons[row][col];
                hex.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    int boardRow = board.getRow(hex);
                    int boardCol = board.getCol(hex);

                    if (gameCreator.isValidMove(boardRow, boardCol)) {
                        Players currentPlayer = gameCreator.getCurrentPlayer();
                        hex.setFill(currentPlayer.getPlayerColor());
                        gameCreator.makeMove(boardRow, boardCol);
                        playerLabel.setText(currentPlayer == Players.Blue ? "                        Turn Red" : "Turn Blue");
                        playerLabel.setStyle("-fx-font-size: 25; -fx-font-weight:bold; -fx-text-fill: white; -fx-font-style: italic;");
                        updateHexagonStatusLabel();

                        if (gameCreator.checkWinCondition(Players.Blue)) {
                            hexagonStatusLabel.setText("\nBLUE WON!");
                            hexagonStatusLabel.setStyle("-fx-font-size: 35; -fx-font-weight:bold; -fx-text-fill: blue; -fx-font-style: italic;");

                        } else if (gameCreator.checkWinCondition(Players.Red)) {
                            hexagonStatusLabel.setText("\nRED WON!");
                            hexagonStatusLabel.setStyle("-fx-font-size: 35; -fx-font-weight: bold; -fx-text-fill: red; -fx-font-style: italic;");

                        }
                    }
                });
            }
        }

        Scene scene = new Scene(root, 1920, 1000);
        primaryStage.setTitle("Hex Board");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateHexagonStatusLabel() {
        int totalHexagons = board.getBoardSize() * board.getBoardSize();
        int blueHexagons = gameCreator.getBlueMoves();
        int redHexagons = gameCreator.getRedMoves();
        int unplayedHexagons = totalHexagons - blueHexagons - redHexagons;

        hexagonStatusLabel.setText("\nNumber Of Laps " + blueHexagons + "\nEmpty Hexagon: " + unplayedHexagons);
//totalHexagons + "\nMavi Altıgen: " + blueHexagons + "\nKırmızı Altıgen: " +
    }

    public void setboyut(int newSize) {
        this.boyut = newSize;
    }
    public void setboardSizes(int newboard){
        this.boardSizes=newboard;
    }

    private void showStartPane(Stage primaryStage) {
        startPane.start(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

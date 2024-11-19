package edu.erciyes.javafxhexgame;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StartPane extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("StartPane");
        // Welcome etiketini oluştur
        Label welcomeLabel = new Label("Welcome To HexGame");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30)); // FontWeight.EXTRA_BOLD
        welcomeLabel.setAlignment(Pos.CENTER);
        welcomeLabel.setPrefSize(500,100);

        //Seçim için bilgilendirme mesajı
        Label mesage =new Label("Please select the board you want to play on");
        mesage.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        mesage.setAlignment(Pos.CENTER);
        welcomeLabel.setTextFill(Color.DARKBLUE); // Yazı rengini koyu mavi yap
        welcomeLabel.setPrefSize(400,100);
        Region spacer = new Region();
        spacer.setPrefHeight(100); // Boşluğun yüksekliğini ayarla


        Button button5x5 = new Button("5x5");
        button5x5.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, new CornerRadii(15), Insets.EMPTY)));
        button5x5.setPrefSize(230, 100);
        button5x5.setTextFill(Color.WHITE);
        button5x5.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Timeline colorChangeAnimation = new Timeline(
                new KeyFrame(Duration.ZERO, event -> button5x5.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(15))))),
                new KeyFrame(Duration.millis(500), event -> button5x5.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, new CornerRadii(15), BorderWidths.DEFAULT)))),
                new KeyFrame(Duration.millis(1000), event -> button5x5.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, new CornerRadii(15), BorderWidths.DEFAULT)))),
                new KeyFrame(Duration.millis(1500), event -> button5x5.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID, new CornerRadii(15), BorderWidths.DEFAULT))))
        );
        colorChangeAnimation.setCycleCount(Timeline.INDEFINITE); // Animasyonu sürekli hale getir
        colorChangeAnimation.play();

        button5x5.setOnAction(e -> {
            MainApplication hexGame = new MainApplication();
            hexGame.setboyut(5);
            hexGame.setboardSizes(60);
            hexGame.start(new Stage());
        });


        Button button11x11 = new Button("11x11");
        button11x11.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, new CornerRadii(15), Insets.EMPTY)));
        button11x11.setPrefSize(265, 150);
        button11x11.setTextFill(Color.WHITE);
        button11x11.setFont(Font.font("Arial", FontWeight.BOLD, 20));

// Kenar hattının rengini değiştirme animasyonu oluştur
        Timeline colorChangeAnimation2 = new Timeline(
                new KeyFrame(Duration.ZERO, event -> button11x11.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(20))))),
                new KeyFrame(Duration.millis(500), event -> button11x11.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, new CornerRadii(15), BorderWidths.DEFAULT)))),
                new KeyFrame(Duration.millis(1000), event -> button11x11.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, new CornerRadii(15), BorderWidths.DEFAULT)))),
                new KeyFrame(Duration.millis(1500), event -> button11x11.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID, new CornerRadii(15), BorderWidths.DEFAULT))))
        );
        colorChangeAnimation2.setCycleCount(Timeline.INDEFINITE); // Animasyonu sürekli hale getir
        colorChangeAnimation2.play();

        button11x11.setOnAction(e -> {

            MainApplication hexGame = new MainApplication();
            hexGame.setboyut(11);
            hexGame.setboardSizes(40);
            hexGame.start(new Stage());
        });


        Button button17x17 = new Button("17x17");
        button17x17.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, new CornerRadii(15), Insets.EMPTY)));
        button17x17.setPrefSize(300, 200);
        button17x17.setTextFill(Color.WHITE);
        button17x17.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Timeline colorChangeAnimation3 = new Timeline(
                new KeyFrame(Duration.ZERO, event -> button17x17.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(30))))),
                new KeyFrame(Duration.millis(500), event -> button17x17.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, new CornerRadii(15), BorderWidths.DEFAULT)))),
                new KeyFrame(Duration.millis(1000), event -> button17x17.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, new CornerRadii(15), BorderWidths.DEFAULT)))),
                new KeyFrame(Duration.millis(1500), event -> button17x17.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID, new CornerRadii(15), BorderWidths.DEFAULT))))
        );
        colorChangeAnimation3.setCycleCount(Timeline.INDEFINITE); // Animasyonu sürekli hale getir
        colorChangeAnimation3.play();

        button17x17.setOnAction(e -> {
            MainApplication hexGame = new MainApplication();
            hexGame.setboyut(17);
            hexGame.setboardSizes(25);
            hexGame.start(new Stage());
        });


        VBox vbox = new VBox(welcomeLabel,mesage,spacer,button5x5, button11x11, button17x17);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPrefSize(500,500);



        Scene scene = new Scene(vbox, 1900, 1000);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
    private Button createBoardButton(String label, double width, double height) {
        Button button = new Button(label);
        button.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, new CornerRadii(15), Insets.EMPTY)));
        button.setPrefSize(width, height);
        button.setTextFill(Color.WHITE);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        return button;
    }


    public static void main(String[] args) {
        launch(args);
}
}


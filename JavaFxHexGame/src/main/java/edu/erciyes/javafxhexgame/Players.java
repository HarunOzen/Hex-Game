package edu.erciyes.javafxhexgame;
import javafx.scene.paint.Color;

public enum Players {
    Blue(Color.BLUE),
    Red(Color.RED);

    private final Color playerColor;

    Players(Color playerColor) {
        this.playerColor = playerColor;
    }


    public Color getPlayerColor() {
        return playerColor;
    }
}

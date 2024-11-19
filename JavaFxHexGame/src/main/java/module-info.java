module edu.erciyes.javafxhexgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.erciyes.javafxhexgame to javafx.fxml;
    exports edu.erciyes.javafxhexgame;
}
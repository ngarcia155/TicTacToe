module game.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens game.tictactoe to javafx.fxml;
    exports game.tictactoe;
}
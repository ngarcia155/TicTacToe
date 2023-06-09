package game.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToeGame extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToeGame.class.getResource("tictactoe.fxml"));

        // Load the FXML file and obtain the root node
        Pane root = (Pane) fxmlLoader.load();

        // Create a new scene with the root node
        Scene scene = new Scene(root);

        // Set the title of the stage/window
        stage.setTitle("Nate's TicTacToe");

        // Set the scene for the stage
        stage.setScene(scene);

        // Set the application icon
        //Image icon = new Image(getClass().getResourceAsStream("calculator-icon.png"));
        //stage.getIcons().add(icon);

        // Display the stage/window
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
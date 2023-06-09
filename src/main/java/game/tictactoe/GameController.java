package game.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.swing.JOptionPane;


public class GameController {

    @FXML
    private TextField aiScore;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private Button resetButton;

    @FXML
    private TextField youScore;

    private Hashtable<String, Button> buttonHashtable;
    //private char[][] board = new char[3][3];
    // Game state representation
    char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    // Determine available moves
    List<int[]> getAvailableMoves(char[][] board) {
        List<int[]> moves = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    moves.add(new int[]{i, j});
                }
            }
        }
        return moves;
    }

    // Minimax algorithm
    int minimax(char[][] board, int depth, boolean maximizingPlayer) {
        // Base cases - terminal states
        // ...

        if (maximizingPlayer) { // AI player (maximizer)
            int bestScore = Integer.MIN_VALUE;
            for (int[] move : getAvailableMoves(board)) {
                // Make the move
                board[move[0]][move[1]] = 'X';

                // Recursive call
                int score = minimax(board, depth + 1, false);

                // Undo the move
                board[move[0]][move[1]] = ' ';

                bestScore = Math.max(score, bestScore);
            }
            return bestScore;
        } else { // Opponent (minimizer)
            int bestScore = Integer.MAX_VALUE;
            for (int[] move : getAvailableMoves(board)) {
                // Make the move
                board[move[0]][move[1]] = 'O';

                // Recursive call
                int score = minimax(board, depth + 1, true);

                // Undo the move
                board[move[0]][move[1]] = ' ';

                bestScore = Math.min(score, bestScore);
            }
            return bestScore;
        }
    }




    public void initialize() {
        buttonHashtable = new Hashtable<>();
        buttonHashtable.put("button1", button1);
        buttonHashtable.put("button2", button2);
        buttonHashtable.put("button3", button3);
        buttonHashtable.put("button4", button4);
        buttonHashtable.put("button5", button5);
        buttonHashtable.put("button6", button6);
        buttonHashtable.put("button7", button7);
        buttonHashtable.put("button8", button8);
        buttonHashtable.put("button9", button9);

        // Set styles for buttons
        for (Button button : buttonHashtable.values()) {
            button.setStyle("-fx-font-family: Candra; -fx-font-size: 40px;");
        }
    }



    @FXML
    void buttonClicked(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String buttonText = clickedButton.getId();
        int buttonIndex = Integer.parseInt(buttonText.substring(6)) - 1;

        // Human player's move
        handlePlayerMove(clickedButton, buttonIndex);

        // Check for game over condition
        if (isGameOver()) {
            return;
        }

        // AI player's move
        handleAIMove();

        // Check for game over condition after AI player's move
        isGameOver();
    }



    private void handlePlayerMove(Button clickedButton, int index) {
        // Update the GUI and the board array with the player's move
        clickedButton.setText("X");
        clickedButton.setDisable(true);
        board[index / 3][index % 3] = 'X';
    }

    private void handleAIMove() {
        // AI player's move - implement the minimax algorithm
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;
        for (int[] move : getAvailableMoves(board)) {
            board[move[0]][move[1]] = 'O';
            int score = minimax(board, 0, false);
            board[move[0]][move[1]] = ' ';
            if (score > bestScore) {
                bestScore = score;
                bestMove = move[0] * 3 + move[1];
            }
        }

        if (bestMove != -1) {
            Button aiButton = buttonHashtable.get("button" + (bestMove + 1));
            if (aiButton != null) {
                aiButton.setText("O");
                aiButton.setDisable(true);
                board[bestMove / 3][bestMove % 3] = 'O';
            }
        }
    }


    private boolean isGameOver() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
                displayResult(board[i][0] + " wins!");
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') {
                displayResult(board[0][i] + " wins!");
                return true;
            }
        }

        // Check diagonals
        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') ||
                (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ')) {
            displayResult(board[1][1] + " wins!");
            return true;
        }

        // Check for a tie
        if (getAvailableMoves(board).isEmpty()) {
            displayResult("It's a tie!");
            return true;
        }

        return false;
    }

    private void displayResult(String result) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Result");
        alert.setHeaderText(null);
        alert.setContentText(result);
        alert.showAndWait();
        System.exit(0);
    }




}
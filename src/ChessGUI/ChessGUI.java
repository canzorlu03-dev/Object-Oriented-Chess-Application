package ChessGUI;

import Chess_Assignment_3.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Can Zorlu
 */
public class ChessGUI extends Application {

    int startX = -1;  // Store starting row
    int startY = -1;  // Store starting column
    boolean firstClick = true;
    int count = 1;
    GridPane grid;

    BorderPane bp;

    @Override
    public void start(Stage primaryStage) {
        bp = new BorderPane(); // Initialize BorderPane

        welcomeScreen(); // Start with the welcome screen

        Scene scene = new Scene(bp);

        primaryStage.setTitle("Chess Game");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public void GuiBoard() {
        if (grid == null) {
            grid = new GridPane();
            grid.setGridLinesVisible(false);
            grid.setMinSize(500, 500);
            grid.setHgap(0);
            grid.setVgap(0);
            grid.setAlignment(Pos.CENTER);
            grid.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            bp.setCenter(grid);
        } else {
            grid.getChildren().clear(); // Clear the existing squares and pieces
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // Create alternating squares
                Rectangle square = new Rectangle(50, 50);
                if ((i + j) % 2 == 0) {
                    square.setFill(Color.PERU); // Dark square
                } else {
                    square.setFill(Color.BEIGE); // Light square
                }
                grid.add(square, j, i);

                // Add piece if one exists
                if (Board.board[i][j] != null) {
                    String imPath = Board.board[i][j].getColor() + ".png";
                    Image image = new Image(imPath);
                    ImageView im = new ImageView(image);
                    im.setFitWidth(50);  // Scale the image to fit within the square
                    im.setFitHeight(50);
                    grid.add(im, j, i); // Add the piece image to the square
                }
            }
        }
    }

    public void welcomeScreen() {
        Text welcomeText = new Text("Welcome to Chess Game!"
                + "\nPress 1 to start a new game."
                + "\nOR."
                + "\nEnter the name of the game you want to load:");

        TextField textField = new TextField();
        Button startButton = new Button("Start Game");
        textField.setMaxSize(70, 30);
        // Track the turn count locally in ChessGUI

        startButton.setOnAction(e -> {
            String input = textField.getText().trim() + ".ser";
            if (input.equals("1")) {
                // Reset turn count for new game
                gameScreen(1);
            } else {
                try {
                    int turnCount = SaveAndLoad.loadGame(input);  // Load game and update local count
                    gameScreen(turnCount);
                } catch (Exception ex) {
                    System.out.println("Error: " + ex.getMessage());
                    gameScreen(1);  // Start a new game on failure
                }
            }
        });

        VBox vbox = new VBox(20, welcomeText, textField, startButton); // Increase spacing for better layout
        vbox.setAlignment(Pos.CENTER);

        // Set background color
        vbox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, null)));

        // Remove or set appropriate size for VBox
        vbox.setPrefWidth(100); // Set a preferred width
        vbox.setPrefHeight(100); // Set a preferred height
        vbox.setPadding(new Insets(20));
        // Set VBox to be on the right side
        bp.setCenter(vbox);
    }

    public void playerMove(Text turnText) {
        // Refresh the board before handling player clicks
        GuiBoard();

        grid.setOnMouseClicked((MouseEvent event) -> {
            // Get the mouse click coordinates
            double mouseX = event.getX();
            double mouseY = event.getY();

            // Iterate over the children of the GridPane to determine which square was clicked
            for (javafx.scene.Node node : grid.getChildren()) {
                if (node instanceof Rectangle) {  // Check for the squares
                    if (node.getBoundsInParent().contains(mouseX, mouseY)) {
                        int column = GridPane.getColumnIndex(node);
                        int row = GridPane.getRowIndex(node);

                        // Debugging: Print the mouse click position
                        System.out.println("Mouse clicked at: (" + row + "," + column + ")");

                        // First click: select a piece
                        if (firstClick) {
                            if (Board.board[row][column] != null) {  // Check if a piece is present at the clicked square
                                startX = row;
                                startY = column;
                                firstClick = false;  // Switch to second click mode
                                System.out.println("Piece selected at: (" + startX + "," + startY + ")");
                                System.out.println(firstClick);
                            } else {
                                System.out.println("No piece selected at: (" + row + "," + column + ")");
                            }
                        } // Second click: move the piece
                        else {
                              // Only allow move to empty square
                                // Debugging: Print the piece move
                                System.out.println("Moving piece from (" + startX + "," + startY + ") to (" + row + "," + column + ")");

                                // Move the piece on the board
                                Board.board[startX][startY].move(row, column);  // Move piece to new square

                                System.out.println("Piece moved to: (" + row + "," + column + ")");
                                GuiBoard();

                                // Reset firstClick to allow the next selection
                                firstClick = true;
                                System.out.println(firstClick);
                                updateTurnText(turnText);

                                count++;
                            } 
                        }
                    }
                }

            
        });
    }

    public void gameScreen(int turn) {
        Text rulesText = new Text("To move a piece, click it , then click the Empty Square\n");
        count = turn;
        Button exitButton = new Button("Exit Game");
        exitButton.setAlignment(Pos.CENTER);
        exitButton.setOnAction(e -> {
            exitScreen();
        });
        Text turnText = new Text(); // Create a Text object for dynamic updates
        updateTurnText(turnText);
        VBox vbox = new VBox(20, rulesText,turnText, exitButton); // Increase spacing for better layout
        vbox.setAlignment(Pos.CENTER);
        
        // Set background color
        vbox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, null)));

        // Remove or set appropriate size for VBox
        vbox.setPrefWidth(100); // Set a preferred width
        vbox.setPrefHeight(500); // Set a preferred height
        vbox.setPadding(new Insets(20));
        // Set VBox to be on the right side
        bp.setRight(vbox);
        playerMove(turnText);

    }

    private void updateTurnText(Text turnText) {
        turnText.setText(count % 2 != 0 ? "WHITE'S TURN" : "BLACK'S TURN");
    }

    public void exitScreen() {
        Text exitText = new Text("To save the game,"
                + "\ntype the name of the game and click 'Save'.");

        TextField textField = new TextField();
        Button saveButton = new Button("Save");
        textField.setMaxSize(70, 30);
        saveButton.setAlignment(Pos.CENTER);
        textField.setAlignment(Pos.CENTER);
        saveButton.setOnAction(e -> {
            SaveAndLoad.saveGame(textField.getText(), count);
        });
        VBox vbox = new VBox(20, exitText, textField, saveButton); // Increase spacing for better layout
        vbox.setAlignment(Pos.CENTER);

        // Set background color
        vbox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, null)));

        // Remove or set appropriate size for VBox
        vbox.setPrefWidth(100); // Set a preferred width
        vbox.setPrefHeight(500); // Set a preferred height
        vbox.setPadding(new Insets(20));
        // Set VBox to be on the right side
        bp.setRight(vbox);
    }

    public static void main(String[] args) {
        Board.setBoard();

        launch(args);

    }

}

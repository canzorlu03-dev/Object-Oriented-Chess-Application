package Chess_Assignment_3;
import java.io.Serializable;
public class Board implements Serializable{

    public static Piece[][] board = new Piece[8][8]; //creating a new 8x8 array

    public static void setBoard() {

        // Placing Pawn  by creating instnaces of Pawn class
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(6, i, "BP");
            board[1][i] = new Pawn(1, i, "WP");
        }

        // Placing rooks by creating instnaces of Rook class
        board[7][0] = new Rook(7, 0, "BR");
        board[7][7] = new Rook(7, 7, "BR");
        board[0][0] = new Rook(0, 0, "WR");
        board[0][7] = new Rook(0, 7, "WR");

        // Placing Bishop by creating instnaces of Bishop class
        board[0][2] = new Bishop(0, 2, "WB");
        board[0][5] = new Bishop(0, 5, "WB");
        board[7][2] = new Bishop(7, 2, "BB");
        board[7][5] = new Bishop(7, 5, "BB");

        // Placing Knight by creating instnaces of Knight class
        board[0][1] = new Knight(0, 1, "WN");
        board[0][6] = new Knight(0, 6, "WN");
        board[7][1] = new Knight(7, 1, "BN");
        board[7][6] = new Knight(7, 6, "BN");

        // Placing Queen by creating instnaces of Queen class
        board[0][3] = new Queen(0, 3, "WQ");
        board[7][3] = new Queen(7, 3, "BQ");

        // Placing Kings by creating instances of King class
        board[0][4] = new King(0, 4, "WK");
        board[7][4] = new King(7, 4, "BK");
    }
    

    public static void printBoard() {
    // Print the column headings (0 to 7)
    System.out.println(" 0   1   2   3   4   5   6   7 "); 
    System.out.println("_________________________________");

    // Loop through each row of the board
    for (int i = 0; i < 8; i++) {
        // Loop through each column in the current row
        for (int j = 0; j < 8; j++) {
            // Check if the current square is empty and determine its color based on position
            if (board[i][j] == null && ((j + i) % 2) != 0) { // Black square
                System.out.print("|**|"); // Print black square indicator
            } else if (board[i][j] == null && ((j + i) % 2) == 0) { // White square
                System.out.print("|  |"); // Print white square indicator
            } else if (board[i][j] != null) { // If the square is occupied by a piece
                System.out.print("|" + board[i][j].color + "|"); // Print the piece's color
            }
        }
        System.out.println(i); // Print the row number at the end of the row
    }
    System.out.println("---------------------------------"); // Print a line separator for visual clarity
}}

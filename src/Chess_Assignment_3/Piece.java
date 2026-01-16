package Chess_Assignment_3;

// Abstract class representing a chess piece

import java.io.Serializable;

abstract public class Piece implements Serializable{

    protected int x; // Current x-coordinate of the piece
    protected int y; // Current y-coordinate of the piece
    protected String color; // Color of the piece (e.g., "white" or "black")

    // Constructor to initialize the piece's position and color
    public Piece(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    // Abstract method to determine if the piece can move to a new position
    abstract public boolean canMove(int newX, int newY);

    // Method to move the piece to a new position
    public void move(int newX, int newY) {
        // Check if the piece can move to the new position
        if (this.canMove(newX, newY)) {
            // Clear the current position on the board
            Board.board[this.x][this.y] = null;
            // Update the piece's coordinates
            this.y = newY;
            this.x = newX;
            // Place the piece at the new position on the board
            Board.board[newX][newY] = this;
        } else {
            // Print an error message for an invalid move
            System.out.println("invalid move ");
        }
    }

    public String getColor() {
        return this.color;
    }
    
    // Method to get the color of the piece, represented by its first letter
    public String PieceColor() {
        return this.color.substring(0, 1); // Return the first letter of the color
    }
}


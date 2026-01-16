/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chess_Assignment_3; // Package containing classes and logic for the Chess assignment

/**
 *
 * @author Can Zorlu
 */
public class Knight extends Piece {

    // Constructor to initialize the Knight's position and color
    public Knight(int x, int y, String color) {
        super(x, y, color); // Call the superclass (Piece) constructor
    }

    // Method to determine if the Knight can move to a new position
    @Override
    public boolean canMove(int newX, int newY) {
        // Check if the new position is out of board boundaries
        if (newX < 0 || newX > 7 || newY < 0 || newY > 7) {
            return false; // Invalid move if out of bounds
        } 
        // Check if the destination square is occupied by a piece of the same color
        else if (Board.board[newX][newY] != null && Board.board[newX][newY].PieceColor().equals(this.PieceColor())) {
            return false; // Invalid move if the destination has a piece of the same color
        } 
        // Check for the valid L-shaped movement of the Knight
        else if ((Math.abs(newX - x) == 2 && Math.abs(newY - y) == 1)
                || (Math.abs(newX - x) == 1 && Math.abs(newY - y) == 2)) {
            return true; // Valid Knight move
        } else {
            return false; // Return false if the move does not conform to Knight's movement rules
        }
    }
}

package Chess_Assignment_3; // Package containing classes and logic for the Chess assignment

/**
 *
 * @author Can Zorlu
 */
public class Bishop extends Piece {

    // Constructor to initialize the Bishop's position and color
    public Bishop(int x, int y, String color) {
        super(x, y, color); // Call the superclass (Piece) constructor
    }

    // Method to determine if the Bishop can move to a new position
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

        // Check for diagonal movement downwards to the right
        else if (Math.abs(newX - x) == Math.abs(newY - y) && newX > x && newY > y) {
            for (int i = x + 1, j = y + 1; i < newX && j < newY; i++, j++) {
                if (Board.board[i][j] != null) {
                    return false; // Invalid move if there are pieces in the diagonal path
                }
            }
            return true; // Valid move if the path is clear
        } 
        // Check for diagonal movement upwards to the right
        else if (Math.abs(newX - x) == Math.abs(newY - y) && newX < x && newY > y) {
            for (int i = x - 1, j = y + 1; i > newX && j < newY; i--, j++) {
                if (Board.board[i][j] != null) {
                    return false; // Invalid move if there are pieces in the diagonal path
                }
            }
            return true; // Valid move if the path is clear
        } 
        // Check for diagonal movement downwards to the left
        else if (Math.abs(newX - x) == Math.abs(newY - y) && newX > x && newY < y) {
            for (int i = x + 1, j = y - 1; i < newX && j > newY; i++, j--) {
                if (Board.board[i][j] != null) {
                    return false; // Invalid move if there are pieces in the diagonal path
                }
            }
            return true; // Valid move if the path is clear
        } 
        // Check for diagonal movement upwards to the left
        else if (Math.abs(newX - x) == Math.abs(newY - y) && newX < x && newY < y) {
            for (int i = x - 1, j = y - 1; i > newX && j > newY; i--, j--) {
                if (Board.board[i][j] != null) {
                    return false; // Invalid move if there are pieces in the diagonal path
                }
            }
            return true; // Valid move if the path is clear
        }
        
        return false; // Return false if none of the movement conditions are met
    }
}


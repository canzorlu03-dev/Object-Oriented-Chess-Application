package Chess_Assignment_3; // Package containing classes and logic for the Chess assignment

public class Pawn extends Piece {

    // Constructor to initialize the Pawn's position and color
    public Pawn(int x, int y, String color) {
        super(x, y, color); // Call the superclass (Piece) constructor
    }

    // Method to determine if the Pawn can move to a new position
    @Override
    public boolean canMove(int newX, int newY) {
        // Check if the new position is out of board boundaries
        if (newX < 0 || newX > 7 || newY < 0 || newY > 7) {
            return false; // Invalid move if out of bounds
        } 
        // Check for moving one step forward if the path is clear (white pawn moving down, black pawn moving up)
        else if ((newX - x == 1 && Math.abs(newY - y) == 0 && Board.board[newX][newY] == null && Board.board[x][y].color.equals("WP"))
                || (newX - x == -1 && Math.abs(newY - y) == 0 && Board.board[newX][newY] == null && Board.board[x][y].color.equals("BP"))) {
            return true; // Valid move if moving straight forward into an empty square

        } 
        // Check for capturing an opponent's piece diagonally
        else if ((Board.board[newX][newY] != null && !Board.board[newX][newY].PieceColor().equals(this.PieceColor()) && Math.abs(newX - x) == 1 && Math.abs(newY - y) == 1)) {
            return true; // Valid move if capturing diagonally
        } 
        // If none of the conditions are met, the move is invalid
        else {
            return false;
        }
    }
}

package Chess_Assignment_3;

import java.io.*;
import static Chess_Assignment_3.Board.board;
import static Chess_Assignment_3.Board.printBoard;
import static Chess_Assignment_3.Board.setBoard;
import java.util.InputMismatchException;


import java.util.Scanner;

/**
 *
 * DONE BY : CAN ZORLU
 *
 */
public class Game implements Serializable {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in); // Create a scanner for user input
      
        try{
            System.out.println("Press 1 to start a new game.");
            System.out.println("Press 2 to load a saved game.");
            int choice = scan.nextInt(); // Read the user's choice as an integer
            scan.nextLine(); // Consume the newline character after the integer input

            if (choice == 1) {
                // Start a new game (passing '1' as the initial turn count)
                player(1);
            } else if (choice == 2) {
                System.out.println("Enter the name of the game you want to load:");
                String fileName = scan.nextLine(); // Get the file name for the saved game
                SaveAndLoad.loadGame(fileName + ".ser"); // Load the saved game
            } else {
                // If the user doesn't enter 1 or 2, print an error message
                System.out.println("Invalid choice. Please press 1 or 2.");
            }}catch(InputMismatchException e){
                System.out.println("please input the corresponding number.");
            }
        

        
    }

    public static void player(int count) {
        // Initialize the chess board
        if (count==1){setBoard();}
        // Print the initial state of the board
        printBoard();
        
        Scanner scan = new Scanner(System.in); // Create a scanner for user input

        while (true) {
            try {// Determine which player's turn it is based on the count
                if (count % 2 == 0) {
                    System.out.println("*BLACK'S TURN*");
                } else {
                    System.out.println("*WHITE'S TURN*");
                }

                // Prompt the user for input and provide an option to quit
                System.out.println("Type 'esc' if you want to quit or 'save' if you want to save.");
                System.out.print("Please input the current X and Y coordinate (X Y) of piece you want to move: ");
                String currentInp = scan.nextLine();
                if (currentInp.equalsIgnoreCase("esc")) {
                    break; // Exit the loop if user types 'esc'
                }
                if (currentInp.equalsIgnoreCase("save")) {
                    System.out.println("name of the saved file");
                    String fileName = scan.nextLine();
                    SaveAndLoad.saveGame(fileName, count);
                    System.out.println("game saved");
                    break;

                }

                // Split the input into an array for current piece position
                String[] input1 = currentInp.split(" ");
                // Check if the input is valid
                if (input1.length != 2) {
                    System.out.println("incorrect input.");
                    continue; // Ask for input again if invalid
                }

                // Parse the current piece position
                int x = Integer.parseInt(input1[1]);
                int y = Integer.parseInt(input1[0]);

                // Check if a piece exists at the specified position
                if (board[x][y] == null) {
                    System.out.println("Piece does not exist.");
                    continue; // Ask for input again if no piece is found
                }

                // Prompt for the new position to move the piece
                System.out.print("Please input the new X Y coordinate (X Y) of piece you want to move: ");
                String newInp = scan.nextLine();
                String[] input2 = newInp.split(" ");
                // Check if the input is valid
                if (input2.length != 2) {
                    System.out.println("incorrect input.");
                    continue; // Ask for input again if invalid
                }

                // Parse the new position
                int newX = Integer.parseInt(input2[1]);
                int newY = Integer.parseInt(input2[0]);
                // Attempt to move the piece to the new position
                board[x][y].move(newX, newY);
                // Print the board after the move
                printBoard();
                count++; // Increment the turn counter
            } catch (NumberFormatException e) {
                System.out.println("make sure all the inputs are numbers.");

            }
        }

    }

}

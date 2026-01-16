package Chess_Assignment_3;

import static Chess_Assignment_3.Board.board;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveAndLoad {
    
    public static void saveGame(String fileName, int count) {
    try (
        FileOutputStream fileOut = new FileOutputStream(fileName + ".ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut)
    ) {
        out.writeObject(Board.board);
        out.writeInt(count);
        System.out.println("Game saved successfully to " + fileName + ".ser");
    } catch (IOException e) {
        System.out.println("Error saving the game: " + e.getMessage());
    }
}

public static int loadGame(String fileName) {
    try (
        FileInputStream fileIn = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(fileIn)
    ) {
        Board.board = (Piece[][]) in.readObject();
        int count = in.readInt();
        System.out.println("Game loaded successfully from " + fileName + ".ser");
        Game.player(count);
        return count;
    } catch (IOException e) {
        System.out.println("Error loading the game: " + e.getMessage());
        return 0;
    } catch (ClassNotFoundException e) {
        System.out.println("Error: " + e.getMessage());
        return 0;
    }
}


}

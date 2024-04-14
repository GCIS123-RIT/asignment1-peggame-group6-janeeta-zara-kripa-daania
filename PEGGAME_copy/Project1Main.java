package PEGGAME_copy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

import org.junit.platform.console.shadow.picocli.CommandLine.Model;

public class Project1Main  {

    public static void main(String[] args) throws Exception {
        System.out.println("Enter a filename:");
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.nextLine();

        // Use the filename to create an instance of your PegGame implementation
        GameModule pegGame = new GameModule(ReadFile.readFile(filename));

        gameCLI.playPegGameCLI(pegGame);

        scanner.close();
    }


    
    /**
     * 
     * @param filename
     * @return
     */
//     public static PegGame createPegGameFromFile(String filename) {
//         try {
//             // Open the file
//             File file = new File(filename);
//             Scanner scanner = new Scanner(file);
    
//             // Read the size of the board
//             int size = Integer.parseInt(scanner.nextLine().trim());
    
//             // Read the content of the file and construct the game board
//             String [][] gameBoard = String[size][size];
//             while (scanner.hasNextLine()) {
//                 String line = scanner.nextLine();
//                 List<String> row = new ArrayList<>();
//                 for (int i = 0; i < size; i++) {
//                     for (int j = 0; j < gameBoard.length; j++) {
                        
//                     }
//                 }
               
//             }
    
//             Create an instance of PegGame (replace GameModule with your implementation)
//             PegGame pegGame = new GameModule(gameBoard);
//             // Print file path to check if it's correct
// System.out.println("File path: " + filename);

// // Print lines read from the file
// while (scanner.hasNextLine()) {
//     String line = scanner.nextLine();
//     System.out.println("Read line: " + line);
//     // Parse the line and print individual elements for verification
// }

// // Print the parsed game board*
// System.out.println("Parsed game board: " + gameBoard);

    
//             // Close the scanner
//             scanner.close();
    
//             // Return the initialized PegGame instance
//             return pegGame;
//         } catch (FileNotFoundException e) {
//             // Handle file not found exception
//             e.printStackTrace();
//             return null;
//         }
//     }
    

}





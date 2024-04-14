package PEGGAME_copy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A file reader and buffered reader which reads the given file
 */
public class ReadFile {
    public static String[][] readFile(String fileName) {
        String[][] board = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);
            
            String line = reader.readLine();
            int size = Integer.valueOf(line);
    
                // 2d array with sizes 
                board = new String[size][size];
                
                // Read each line from the file and initialize the board
                for (int row = 0; row < size; row++) {
                    line = reader.readLine();
                    for (int col = 0; col < size; col++) {
                        board[row][col] = String.valueOf(line.charAt(col));
                    }
                    
                }
                // closing the file reader and buffered reader
                reader.close();
                fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return board;
        }
    }


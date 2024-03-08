package peggame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A file reader and buffered reader which reads the given file
 */
public class ReadFile {
    public void readFile(String fileName) throws IOException {
        FileReader fileReader = new FileReader(fileName);
        BufferedReader reader = new BufferedReader(fileReader);
        
        String line = reader.readLine();
       int size = Integer.parseInt(line.trim());

            // 2d array with sizes 
            String[][] board = new String[size][size];
            
            // Read each line from the file and initialize the board
            for (int row = 0; row < size; row++) {
                String now_line = reader.readLine();
                for (int col = 0; col < now_line.length() && col < size; col++) {
                    board[row][col] = String.valueOf(now_line.charAt(col));
                }
                
            }
            // closing the file reader and buffered reader
            reader.close();
            fileReader.close();
        }
    }


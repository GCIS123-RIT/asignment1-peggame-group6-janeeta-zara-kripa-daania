package peggame;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class GameModule implements PegGame {
    private GameState gameState;
    private int pegsRemaining; 
    private List<List<String>> gameBoard = new ArrayList<>(); //2D LIST OF STRINGS

    /**
     * Game module constructor
     * @param gameBoard
     */
    public GameModule(List<List<String>> gameBoard) {
        this.gameState = GameState.NOT_STARTED;
        this.gameBoard = gameBoard;
        
        for(List<String> row : gameBoard){
            for(String element : row){
                if (element.equals("o")){
                    pegsRemaining++;
                }
            }
        }
    }
   
    /**
     * all possible moves in all valid directions are coded
     * @return
     */
    public Collection<Move> getPossibleMoves() {

        Collection<Move> possibleMoves = new ArrayList<>();

        for(int row = 0; row < gameBoard.size(); row++){
            for (int column = 0; column < gameBoard.get(row).size(); column++){

                // Right Moves
                if ( column < gameBoard.get(0).size() - 2 && 
                gameBoard.get(row).get(column + 1).equals("o") && 
                gameBoard.get(row).get(column + 2).equals(".")  ) { // checking right side of peg
                    possibleMoves.add(new Move(new Location(row, column), new Location(row, column + 2)));
                    
                }

                // Left Moves
               if (column > 1 && gameBoard.get(row).get(column - 1).equals("o") 
               && gameBoard.get(row).get(column - 2).equals(".") ) {
                possibleMoves.add(new Move(new Location(row, column), new Location(row, column - 2)));
            }

                //Top Moves
               if (row > 1 && gameBoard.get(row - 1).get(column).equals("o") && 
               gameBoard.get(row - 2).get(column).equals(".") ) {
                    possibleMoves.add(new Move(new Location(row, column), new Location(row - 2, column)));
               }
            
            
                // Bottom Moves
                if (row < gameBoard.size() - 2 && 
                gameBoard.get(row + 1).get(column).equals("o") && 
                gameBoard.get(row + 2).get(column).equals(".") ) {
                    possibleMoves.add(new Move(new Location(row, column), new Location(row + 2, column)));
                }
                
                // Diagonal Top Right Moves
                if (row > 1 && column < gameBoard.get(0).size() - 2 &&
                gameBoard.get(row - 1).get(column + 1).equals("o") &&
                gameBoard.get(row - 2).get(column + 2).equals(".")) {
                possibleMoves.add(new Move(new Location(row, column), new Location(row - 2, column + 2)));
                    }

                // Diagonal Top Left Moves
                if (row > 1 && column > 1 &&
                gameBoard.get(row - 1).get(column - 1).equals("o") &&
                gameBoard.get(row - 2).get(column - 2).equals(".")) {
                possibleMoves.add(new Move(new Location(row, column), new Location(row - 2, column - 2)));
                }

                // Diagonal Bottom Right Moves
                if (row < gameBoard.size() - 2 && column < gameBoard.get(0).size() - 2 &&
                gameBoard.get(row + 1).get(column + 1).equals("o") &&
                gameBoard.get(row + 2).get(column + 2).equals(".")) {
                possibleMoves.add(new Move(new Location(row, column), new Location(row + 2, column + 2)));
                }

                // Diagonal Bottom Left Moves
                if (row < gameBoard.size() - 2 && column > 1 &&
                gameBoard.get(row + 1).get(column - 1).equals("o") &&
                gameBoard.get(row + 2).get(column - 2).equals(".")) {
                possibleMoves.add(new Move(new Location(row, column), new Location(row + 2, column - 2)));
                }
                
            }
        }
         return possibleMoves;
    }

    /**
     * method to check the state of the game
     * @return
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * method to make a move- checks for invalid moves as well
     * @param move
     * @throws PegGameException
     */
   
    public void makeMove(Move move) throws PegGameException {
        Collection<Move> x = getPossibleMoves();
        if (x.contains(move)){
            throw new PegGameException("Invalid Move!");
        }
        
        if (pegsRemaining <= 0) {
            throw new PegGameException("No pegs remaining.");
        }

        int middlePegRow = (move.getFrom().getRow() + move.getTo().getRow())/2;
        int middlePegColumn = (move.getFrom().getCols() + move.getTo().getCols())/2;

        gameBoard.get(move.getFrom().getRow()).set(move.getFrom().getCols(), ".");
        gameBoard.get(middlePegRow).set(middlePegColumn, ".");
        gameBoard.get(move.getTo().getRow()).set(move.getTo().getCols(), "o");

        pegsRemaining--;

        // Update game state
        updateGameState();
    }
    // a  game that ends when the last valid move has been made (either a stalemate or a win)
    private void updateGameState() {
        if (pegsRemaining == 1) {
            gameState = GameState.WON;
        } else if (pegsRemaining > 1 && getPossibleMoves().isEmpty()) {
            gameState = GameState.STALEMATE;
        } else if (pegsRemaining > 0) {
            gameState = GameState.IN_PROGRESS;
        }
    }

    @Override
    public String toString(){
        String boardString = "";
        for (List<String> row : gameBoard){
            for (String element : row){
                if (element.equals(".")){
                    boardString += "-";
                }
                else{
                    boardString += element;
                }
            }
            boardString += "\n";
        }
     return boardString;
    }

    @Override
  
    public String getBoardState() {
        StringBuilder boardState = new StringBuilder();
        for (List<String> row : gameBoard) {
            for (String cell : row) {
                boardState.append(cell).append(" ");
            }
            boardState.append("\n");
        }
        return boardState.toString();
    }
    
}


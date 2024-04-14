package PEGGAME_copy;

import java.util.Collection;
import java.util.ArrayList;

public class GameModule implements PegGame {
    private GameState gameState;
    private int pegsRemaining;
    private String[][] gameBoard ; // 2D LIST OF STRINGS

    /**
     * Game module constructor
     *
     * @param gameBoard
     */
    public GameModule(String[][] gameBoard) {
        this.gameState = GameState.NOT_STARTED;
        this.gameBoard = gameBoard;
    }

    public String[][] getBoard() {
        return this.gameBoard;
    }

    public int getPegRemaing() {
        int num = 0;
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                if (this.gameBoard[i][j].equals("o")) {
                    num++;
                }
            }
        }
        return num;
    }

    @Override
    public Collection<Move> getPossibleMoves() {
        Collection<Move> movecollection = new ArrayList<Move>();
        for (int i = 0; i < this.gameBoard.length; i++) { // row
            for (int j = 0; j < this.gameBoard.length; j++) { // column
                if (this.gameBoard[i][j].equals("o")) {
                    if (j + 2 < this.gameBoard.length) {// right
                        if (this.gameBoard[i][j+2].equals(".") && this.gameBoard[i][j+1].equals("o")) {
                            movecollection.add(new Move(new Location(i, j), new Location(i, j + 2)));
                        }
                    }
                    if (i + 2 < this.gameBoard.length && j + 2 < this.gameBoard.length) {// bottom right (corner)
                        if (this.gameBoard[i+2][j+2].equals(".") && this.gameBoard[i+1][j+1].equals("o")) {
                            movecollection.add(new Move(new Location(i, j), new Location(i + 2, j + 2)));
                        }
                    }
                    if (i + 2 < this.gameBoard.length) { // bottom
                        if (this.gameBoard[i+2][j].equals(".") && this.gameBoard[i+1][j].equals("o")) {
                            movecollection.add(new Move(new Location(i, j), new Location(i + 2, j)));
                        }
                    }
                    if (i + 2 < this.gameBoard.length && j - 2 >= 0) { // bottom left (corner)
                        if (this.gameBoard[i+2][j-2].equals(".") && this.gameBoard[i+1][j-1].equals("o")) {
                            movecollection.add(new Move(new Location(i, j), new Location(i + 2, j - 2)));
                        }
                    }
                    if (j - 2 >= 0) { // left
                        if (this.gameBoard[i][j - 2].equals(".") && this.gameBoard[i][j - 1].equals("o")) {
                            movecollection.add(new Move(new Location(i, j), new Location(i, j - 2)));
                        }
                    }
                    if (i - 2 >= 0 && j - 2 >= 0) { // top left (corner)
                        if (this.gameBoard[i-2][j-2].equals(".") && this.gameBoard[i-1][j-1].equals("o")) {
                            movecollection.add(new Move(new Location(i, j), new Location(i - 2, j - 2)));
                        }
                    }
                    if (i - 2 >= 0) { // top
                        if (this.gameBoard[i-2][j].equals(".") && this.gameBoard[i-1][j].equals("o")) {
                            movecollection.add(new Move(new Location(i, j), new Location(i - 2, j)));
                        }
                    }
                    if (i - 2 >= 0 && j + 2 < this.gameBoard.length) { // top right (corner)
                        if (this.gameBoard[i - 2][j + 2].equals(".") && this.gameBoard[i - 1][j + 1].equals("o")) {
                            movecollection.add(new Move(new Location(i, j), new Location(i - 2, j + 2)));
                        }
                    }
                }
            }
        }
        return movecollection;
    }

    // a game that ends when the last valid move has been made (either a stalemate or a win)
    public void updateGameState() {
        if (this.getPegRemaing() == 1) {
            gameState = GameState.WON;
        } else if (getPossibleMoves().isEmpty()) {
            gameState = GameState.STALEMATE;
        } else {
            gameState = GameState.IN_PROGRESS;
        }
    }

    @Override
    public String toString() {
        String boardState = "";
        for (int row = 0; row < this.gameBoard.length; row++) {
            for (int cols = 0; cols < this.gameBoard.length; cols++) {
                boardState += this.gameBoard[row][cols];
            }
            boardState += "\n";
        }
        return boardState;
    }

    @Override
    public String getBoardState() {
        String boardState = "";
        for (int row = 0; row < this.gameBoard.length; row++) {
            for (int cols = 0; cols < this.gameBoard.length; cols++) {
                boardState += this.gameBoard[row][cols];
            }
            boardState += "\n";
        }
        return boardState;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public void makeMove(Move move) throws PegGameException {
        Collection<Move> possibleMoves = getPossibleMoves();
        if (!possibleMoves.contains(move)) {
            throw new PegGameException("Invalid Move!");
        }

        int middlePegRow = (move.getFrom().getRow() + move.getTo().getRow()) / 2;
        int middlePegColumn = (move.getFrom().getCols() + move.getTo().getCols()) / 2;

        this.gameBoard[move.getFrom().getRow()][move.getFrom().getCols()] = ".";
        this.gameBoard[middlePegRow][middlePegColumn] = ".";
        this.gameBoard[move.getTo().getRow()][move.getTo().getCols()]= "o";

        pegsRemaining--;

        // Update game state
        updateGameState();
    }
}

package PEGGAME_copy;

import java.util.Collection;

/**
 * Peg game interface with methods which get possible moves, gets game state and make move
 */
public interface PegGame {
  
    public Collection<Move> getPossibleMoves();

  
    public GameState getGameState();

    public void makeMove(Move move) throws Exception;

    public String getBoardState();
}

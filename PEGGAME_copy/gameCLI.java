package PEGGAME_copy;

// import java.util.Collection;
import java.util.Scanner;

public class gameCLI {
    /**
     * 
     * @param pegGame
     * @throws Exception
     */
    public static void playPegGameCLI(PegGame pegGame) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("PegGame Board!");

        String input = "";
        while (!input.equals("Quit")) {
            System.out.println("Enter your move (move r1 c1 r2 c2 / quit):");
            input = scanner.nextLine();

            if (input.equals("Leave game")) {
                System.out.println("GoodBye!");
            } else if (input.equals("moves")) {
                System.out.println(pegGame.getPossibleMoves());
                        } else if (input.startsWith("move")) {
                handleMoveCommand(pegGame, input);
            }
            else if (input.startsWith("possible")) {
                System.out.println(pegGame.getPossibleMoves());
            } 
            else {
                System.out.println("Please enter a valid command!");
            }
        }
        scanner.close();
    }

   
    // }
    
    /**
     * 
     * @param pegGame
     * @param input
     * @throws Exception
     */
    private static void handleMoveCommand(PegGame pegGame, String input) {
        String[] parts = input.split(" ");
        System.out.println("r1: " + parts[1] + ", c1: " + parts[2] + ", r2: " + parts[3] + ", c2: " + parts[4]);
    
        if (parts.length == 5) {
            int r1 = Integer.valueOf(parts[1]);
            int c1 = Integer.valueOf(parts[2]);
            int r2 = Integer.valueOf(parts[3]);
            int c2 = Integer.valueOf(parts[4]);
            
            System.out.println("Parsed r1: " + r1 + ", Parsed c1: " + c1 + ", Parsed r2: " + r2 + ", Parsed c2: " + c2);
    
            Location initialLocation = new Location(r1, c1);
            Location finalLocation = new Location(r2, c2);
            Move playerMove = new Move(initialLocation, finalLocation);
    
            try {
                pegGame.makeMove(playerMove);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("PegGame Board:");
            System.out.println(pegGame.getBoardState());
    
            // Check if the game has ended
            if (pegGame.getGameState() == GameState.STALEMATE || pegGame.getGameState() == GameState.WON) {
                System.out.println("Game Over: " + pegGame.getGameState());
            }
        } else {
            System.out.println("Invalid input format. Please enter a valid move.");
        }
    }
}    


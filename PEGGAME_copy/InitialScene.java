package PEGGAME_copy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Creating the initial scene where the user chooses the file to play the game
 */
public class InitialScene extends Application {

    public Stage stage;
    public static GridPane gameBoard;
    public GameState gamestate;
    public GameModule peggame;
    public Label l1, l2, errorLabel; // Added errorLabel
    public static Label l3, l4;
    public static TextField fileName;
    public static Button exitButton;
    public Button enterButton;
    public static Button saveButton;
    public HBox alert;
    public static Location selectedLocation;
    public static GridPane gp;
    public static VBox view;
    

    /**
 * This method initializes and sets up the initial scene of the PegGame application.
 * It creates UI elements such as labels, text fields, and buttons for entering the filename,
 * as well as handles user interactions with these elements.
 * 
 * @param stage The primary stage of the JavaFX application.
 * @throws Exception If there is an exception during the setup of the initial scene.
 */
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        l1 = makeLabel("Welcome to PegGame!!!", Color.WHITE, Color.MAROON); //welcome label
        l2 = makeLabel("Enter your file name : ", Color.WHITE, Color.MAROON); //prompt for user input-filename
        
        fileName = new TextField();

        enterButton = new Button("Enter"); //enter button to click on after entering filename
        enterButton.setFont(new Font(30));

        exitButton = new Button("Exit"); // exit button to exit at any given moment
        exitButton.setFont(new Font(30));

        saveButton= new Button ("Save the Game");
        saveButton.setFont(new Font(30));

        errorLabel = new Label(""); // Initializing errorLabel with empty text
        errorLabel.setTextFill(Color.RED);

        VBox vbox = new VBox(20); // spacing between elements
        vbox.setAlignment(Pos.CENTER); //aligning to the center
        vbox.setPadding(new Insets(20));
        vbox.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
        //vbox to  align the 2 labels and 2 buttons vertically
        vbox.getChildren().addAll(l1, l2, fileName, errorLabel, enterButton, exitButton); 
        
        exitButton.setOnAction(e->{
            stage.close();
        });
        
        /**
         * Event handler for the Enter button
         */
        enterButton.setOnAction(e -> {
        String fileNameText = fileName.getText();
            if (isValidFileName(fileNameText)) {
                System.out.println("Valid filename entered: " + fileNameText);
                //Transition to a new scene with a label displaying the filename
         
                Label fileLabel = new Label("File: " + fileNameText);
                VBox newSceneLayout = new VBox(20);
                newSceneLayout.getChildren().add(fileLabel);

                GameModule game = new GameModule(ReadFile.readFile("peggame/"+ fileNameText));

                l3 = new Label("");
                // FOR THE POPUP/INVISIBLE HBOX

                l4 = new Label("IN PROGRESS"); //indicates that game is in progress

                view = new VBox(makeBoardPane(game.getBoard()),l3,l4);

                Scene gameScene = new Scene(view, 400, 400);
                stage.setScene(gameScene);
            } 
        else {
        // If filename is invalid, display an error message
        errorLabel.setText("Invalid filename entered! Please enter a valid filename (e.g., fourByFour.txt, fiveByFive.txt, sixbySix.txt)");
        }});

    Scene scene = new Scene(vbox, 400, 400);
    stage.setScene(scene);
    stage.setTitle("PegGame");
    stage.show();
    }

    /**
     * makeLabel method with preset alignments, fonts, background colors, and text colours for uniformity in labels
     * @param content
     * @param textColor
     * @param backgroundColor
     * @return
     */
    public static Label makeLabel(String content, Color textColor, Color backgroundColor) {
        Label label = new Label(content);
        label.setFont(new Font("Comic Sans",14 ));
        label.setTextFill(textColor);
        label.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
        label.setAlignment(Pos.CENTER);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THIN)));
        return label;
    }

    /**
     * method to check if the file name entered by user is an existing and valid file
     * @param fileName- name of file to be read
     * @return
     */
    public boolean isValidFileName(String fileName) {
    File file = new File("peggame/" + fileName);
    if (file.exists()) {
        if (fileName.equals("")) {
            return false;
        } else {
            return true;
        }
    } else {
        return false;
    }
}

    
    /**
     * Creating the gameboard for peggame using gridpane
     * images are entered within each pane to signify he presence or absence of the peg
     * in this case-> seashell: peg, pearl: no peg
     * @param board- the gripane with board
     * @return
     */
    public GridPane makeBoardPane(String[][] board){
        gp = new GridPane();
        selectedLocation = null;
        peggame = new GameModule(board);
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board [i][j].equals("o")){
                    ImageView pegImage = new ImageView(new Image("peggame/peg.png"));
                    int row = i;
                    int cols = j;
                    pegImage.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> selectPeg(pegImage, row, cols, peggame));
                    pegImage.setPreserveRatio(true);
                    pegImage.setFitHeight(50);
                    gp.add(pegImage,j,i);
                   
                }
                else{
                    ImageView holeImage = new ImageView(new Image("peggame/empty.png"));
                    holeImage.setPreserveRatio(true);
                    holeImage.setFitHeight(50);
                    gp.add(holeImage,j,i);
                }
            } 
        }
        return gp;
        }

    /**
     * this method allows user to click and select a pegs to pleay the game
     * event handlers are used for this purpose
     * upon being clicked, the pegs change colours to show that they have been clicked
     * when invalid moves are made, a message pops up letting the users know that the move is invalid
     * @param image- the image of the peg and nopeg to be put in the gridpane
     * @param fromRow- the row the peg is present before the move
     * @param fromCols- the colum in which the peg is present before the move
     * @param peggame
     */
    public static void selectPeg(ImageView image, int fromRow, int fromCols, GameModule peggame) {
        String[][] board = peggame.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board [i][j].equals("o")){
                    ImageView pegImage = new ImageView(new Image("peggame/peg.png"));
                    int row = i;
                    int cols = j;
                    pegImage.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> selectPeg(pegImage, row, cols, peggame));
                    pegImage.setPreserveRatio(true);
                    pegImage.setFitHeight(50);
                    gp.add(pegImage,j,i);
                }
                else{
                    ImageView holeImage = new ImageView(new Image("peggame/empty.png"));
                    int a = i;
                    int b = j;
                    holeImage.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> movePeg(a, b, peggame));
                    holeImage.setPreserveRatio(true);
                    holeImage.setFitHeight(50);
                    gp.add(holeImage,j,i);
                }
            } 
        }
        ImageView pegImage = new ImageView(new Image("peggame/pegselected.png"));
        pegImage.setPreserveRatio(true);
        pegImage.setFitHeight(50);
        gp.add(pegImage,fromCols,fromRow);
        selectedLocation = new Location(fromRow, fromCols);
        view.getChildren().setAll(gp, l3, l4);
    }

    /**
     * this method facilitates the movement of pegs around the board
     * it also show sthe state of the game at all given times
     * @param toRow- the row the peg goes to
     * @param toCols- the column the peg goes to
     * @param peggame
     */
    public static void movePeg(int toRow, int toCols, GameModule peggame) {
        Location toLocation = new Location(toRow, toCols);
        try {
            peggame.makeMove(new Move(selectedLocation, toLocation));
            resetBoard(peggame);
            l3.setText("Good Move!!!!");
            if (peggame.getGameState() == GameState.WON) {
                l4.setText("YOU WON!!!!");
            } else if (peggame.getGameState() == GameState.STALEMATE) {
                l3.setText("Bad Move");
                l4.setText("YOU LOSE !!!");
            }
        } catch (Exception e) {
            l3.setText("Invalid Move!");
        }
    }
    /**
     * this method resets the board
     * @param peggame
     */
    public static void resetBoard(GameModule peggame) {
        gp = new GridPane();
        String[][] board = peggame.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board [i][j].equals("o")){
                    ImageView pegImage = new ImageView(new Image("peggame/peg.png"));
                    int row = i;
                    int cols = j;
                    pegImage.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> selectPeg(pegImage, row, cols, peggame));
                    pegImage.setPreserveRatio(true);
                    pegImage.setFitHeight(50);
                    gp.add(pegImage,j,i);
                }
                else{
                    ImageView holeImage = new ImageView(new Image("peggame/empty.png"));
                    holeImage.setPreserveRatio(true);
                    holeImage.setFitHeight(50);
                    gp.add(holeImage,j,i);
                }
            } 
        }
        view.getChildren().setAll(gp, l3, l4, saveButton,exitButton);
    }
      public static void saveBoard(char[][] board, String fileName) {
        int boardSize = board.length;
        String text = boardSize + "\n";
        try {
            FileWriter myWriter = new FileWriter("peggame/boards/" + fileName);
            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    text += board[i][j];
                }
                text += "\n";
            }
            myWriter.write(text);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
      }
  
    
  
    public static void main(String[] args) {
        launch(args);
    }
}

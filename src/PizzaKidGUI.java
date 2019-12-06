import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PizzaKidGUI extends Application {

	// Images used in the game play:
	static Image house1 = new Image("/HouseGrass.png", 90.0, 32.0, true, true); // uses House1.png image from folder
	static Image customerHouse = new Image("/CustomerGrass.png", 90.0, 32.0, true, true);
	static Image pizzaCar = new Image("/PizzaCar (1).png", 50.0, 25.0, true, true);
	static Image tree = new Image("/Trees.png", 90.0, 45.0, true, true);
	static Image hole = new Image("/Hole.png", 30.0, 32.0, true, true);
	static Image road = new Image("/Road.png", 30.0, 32.0, true, true);

	public static PizzaKid game = new PizzaKid();
	public static Scanner input = new Scanner(System.in);

	// GUI related variables
	static int height = 700;
	static int width = 1000;

	static int buttonHeight = 80;
	static int buttonWidth = 100;

	static int mapHeight = 540; // makes each tile size 45x45 pixels
	static int mapWidth = 540;

	StackPane root = new StackPane();
	static VBox startScreen = new VBox();
	static BorderPane playScreen = new BorderPane();

	boolean isPlaying = false;

	static int timeDisplayInSeconds = 0;

	public static void main(String[] args) {
		game.map = new Map(new Avatar(), 17, PizzaKid.createTilesFor17());
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		gameOver = true;

		initializeStartScreen();
		initializePlayScreen();

		root.getChildren().add(playScreen);
		root.getChildren().add(startScreen);

		Scene scene = new Scene(root, width, height);
		stage.setTitle("PizzaKid");
		stage.setScene(scene);
		stage.show();

	}

	/**
	 * Puts in and defines the elements in the start screen (title and button)
	 */
	public void initializeStartScreen() {

		// start screen style
		setStartScreenStyle();

		// title
		HBox top = new HBox();
		setStartingTitle(top);

		VBox bottom = new VBox();

		// start button
		HBox button = new HBox();
		setButtons(button);

		bottom.getChildren().add(button);

		startScreen.getChildren().add(top);
		startScreen.getChildren().add(bottom);

	}

	/**
	 * Sets the style of the start screen
	 */
	public void setStartScreenStyle() {
		// String style1 = "-fx-background-color: #ffb240;"; //sets background to orange
		Image pizzaBack = new Image("/backgroundP.png");
		BackgroundImage backgroundImage = new BackgroundImage(pizzaBack, null, null, null, null);
		Background background = new Background(backgroundImage);
		startScreen.setBackground(background);
	}

	/**
	 * Sets the style of the title and adds it to top part of start screen
	 * 
	 * @param top - the top part of the start screen where the title is placed
	 */
	public void setStartingTitle(HBox top) {
		top.setAlignment(Pos.CENTER);
		top.setMinHeight(height / 2);

		Label opening = new Label("");
		opening.setFont(Font.font("Courier", 60));
		opening.setTextFill(Color.WHITE);

		top.getChildren().add(opening);
	}

	/**
	 * Sets the style and the functionality of the button and adds it to bottom part
	 * of start screen
	 * 
	 * @param bottom - the bottom part of the start screen where the button is
	 *               placed
	 */
	public void setButtons(HBox bottom) {
		bottom.setAlignment(Pos.CENTER);
		bottom.setMinHeight(height / 2);

		Button start = new Button("START");
		start.setFont(Font.font("Courier New", 30));
		start.setMinSize(buttonWidth, buttonHeight);

		Button howTo = new Button("HOW TO PLAY");
		howTo.setFont(Font.font("Courier New", 30));
		howTo.setMinSize(buttonWidth, buttonHeight);

		// event in start button
		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				startScreen.setVisible(false);
				playScreen.setVisible(true);
				isPlaying = true;
				playScreen.toFront();
				gameOver = false;
				timer.start();
			}
		});

		howTo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				setHowToPlayScreen();
			}
		});

		bottom.getChildren().add(start);
		bottom.getChildren().add(howTo);
	}


	public static void setHowToPlayScreen()
	// this method makes a window pop-up when button for how to play is clicked
	{
		Stage stage = new Stage();
		stage.setTitle("How To Play");
		
		VBox howToPlayScreen = new VBox();
		
		Label instructions = new Label("Instructions:");
		Label first = new Label("- Use the arrow keys to move");
		Label second = new Label("- Deliver pizza on time to get tips");
		Label third = new Label("- Earn the most tips in 1 minute!");
		Label fourth = new Label("- Hit an obstacle earn a strike!");
		Label fifth = new Label("- Run out of time earn a strike!");
		Label sixth = new Label("- 3 strikes = Game Over!");
		Label seventh = new Label("Good luck!");

		Button quitButton = new Button("Close");

		quitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stage.close();
			}
		});

		howToPlayScreen.getChildren().add(instructions);
		howToPlayScreen.getChildren().add(first);
		howToPlayScreen.getChildren().add(second);
		howToPlayScreen.getChildren().add(third);
		howToPlayScreen.getChildren().add(fourth);
		howToPlayScreen.getChildren().add(fifth);
		howToPlayScreen.getChildren().add(sixth);
		howToPlayScreen.getChildren().add(seventh);
		howToPlayScreen.getChildren().add(state);
		howToPlayScreen.getChildren().add(timeLeftForOrder);
		howToPlayScreen.getChildren().add(quitButton);

		// instructions
		instructions.setFont(Font.font("Arial", 15));
		instructions.setTextFill(Color.BLACK);
		instructions.setAlignment(Pos.CENTER);
		instructions.setMinWidth(width / 4);
		
		Scene sceneII = new Scene(howToPlayScreen, 300, 300);
		stage.setScene(sceneII);
		stage.show();
	}

	// variables are outside since they need constant updating
	static HBox heading = new HBox();
	static GridPane mapGUI = new GridPane();
	static HBox footer = new HBox();
	static VBox right = new VBox();
	static VBox left = new VBox();

	// state is a text that communicates the state of the game to the user
	static Label state;
	// timeLeftForOrder is continuously updated to let user know the time left to
	// deliver the order
	static Label timeLeftForOrder;

	/**
	 * Initializes the physical parts of the play screen (creates a map, setting
	 * header, footer, left side and right side)
	 */
	public static void initializePlayScreen() {

		// playScreen
		setPlayScreenStyle();

		// setting heading containing title, time, and tips
		setHeadings(heading);

		// setting map
		game.map = new Map(new Avatar(), 17, PizzaKid.createTilesFor17());
		game.map.generateCustomer();
		game.map.getCustomer().birthTime = game.collectibles.getTime();
		game.map.generateObstacles();
		setMap(mapGUI);

		// setting footer containing quit button
		setFooter(footer);

		// setting right panel containing strike counter
		setRight(right);

		// setting left panel containing instructions
		setLeft(left);

		playScreen.setTop(heading);
		playScreen.setCenter(mapGUI);
		playScreen.setBottom(footer);
		playScreen.setRight(right);
		playScreen.setLeft(left);

		playGame(playScreen);

	}

	/**
	 * Sets the style of the play screen
	 */
	public static void setPlayScreenStyle() {
		playScreen.setMinHeight(height);
		playScreen.setMinWidth(width);
		String style2 = "-fx-background-color: #ADD8E6;"; // light blue bkgrd
		String style3 = "-fx-background-color: #576A75"; // same color as roads
		playScreen.setStyle(style2); // sets play screen to blue
		mapGUI.setStyle(style3); // set background of map to road color
	}

	/**
	 * Creates heading elements and sets their style
	 * 
	 * @param heading - HBox containing the elements of the heading
	 */
	public static void setHeadings(HBox heading) {

		Label title = new Label("PizzaKid");
		Label time = new Label("Time left: " + timeDisplayInSeconds);
		Label tips = new Label("Tips: $" + game.collectibles.getTipMoney());

		heading.getChildren().add(title);
		heading.getChildren().add(time);
		heading.getChildren().add(tips);

		heading.setAlignment(Pos.CENTER);

		// title
		title.setFont(Font.font("Arial", 30));
		title.setTextFill(Color.BLACK);
		title.setAlignment(Pos.CENTER_LEFT);
		title.setMinWidth(width / 2);
		title.setPadding(new Insets(10, 0, 0, 50));

		// time
		time.setFont(Font.font("Arial", 15));
		time.setTextFill(Color.BLACK);
		time.setAlignment(Pos.CENTER);
		time.setMinWidth(width / 4);

		// tips
		tips.setFont(Font.font("Arial", 20));
		tips.setTextFill(Color.DARKGREEN);
		tips.setAlignment(Pos.CENTER);
		tips.setMinWidth(width / 4);
	}

	/**
	 * Sets the style of the map and calls showGUIMap to put in map elements
	 * 
	 * @param map - GridMap which contains the graphical map
	 */
	public static void setMap(GridPane map) {
		map.setVgap(5);
		map.setHgap(5);
		map.setPadding(new Insets(10, 10, 10, 10));

		showGUIMap(map);

		map.setAlignment(Pos.CENTER);
	}

	/**
	 * Sets the style and functionality of the button and adds it to the footer
	 * 
	 * @param footer - HBox containing button
	 */
	public static void setFooter(HBox footer) {

		footer.setMinWidth(width);

		Button quit = new Button("Quit");
		quit.setFont(Font.font("Arial Black", 15));
		quit.setMinSize(buttonWidth / 2, buttonHeight / 2);
		quit.setAlignment(Pos.CENTER);

		// event in quit button
		quit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				resetMap();
				playScreen.setVisible(false);
				startScreen.setVisible(true);
				startScreen.toFront();
				gameOver = true;
			}
		});

		Button reset = new Button("Reset");
		reset.setFont(Font.font("Arial Black", 15));
		reset.setMinSize(buttonWidth / 2, buttonHeight / 2);
		reset.setAlignment(Pos.CENTER);

		// event in quit button
		reset.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				resetMap();
				timer.start();
			}
		});

		footer.setAlignment(Pos.CENTER_RIGHT);
		footer.setPadding(new Insets(10, 50, 20, 10));
		footer.getChildren().add(quit);
		footer.getChildren().add(reset);
	}

	/**
	 * Sets style and elements on the right side (number of strikes)
	 * 
	 * @param right - VBox containing right elements
	 */
	public static void setRight(VBox right) {
		Label strike = new Label("Strikes:");

		// strike
		strike.setFont(Font.font("Arial", 15));
		strike.setTextFill(Color.BLACK);
		strike.setAlignment(Pos.CENTER);
		strike.setMinWidth(width / 4);

		right.getChildren().add(strike);
	}

	/**
	 * Sets style and elements on the left side (instructions)
	 * 
	 * @param right - VBox containing left elements
	 */
	public static void setLeft(VBox left) {
		Label instructions = new Label("");
		Label first = new Label("");
		Label second = new Label("");
		Label third = new Label("                                 ");
		Label fourth = new Label("");
		Label fifth = new Label("");
		Label sixth = new Label("");
		Label seventh = new Label("");

		state = new Label("Start Playing!");
		timeLeftForOrder = new Label("");

		left.getChildren().add(instructions);
		left.getChildren().add(first);
		left.getChildren().add(second);
		left.getChildren().add(third);
		left.getChildren().add(fourth);
		left.getChildren().add(fifth);
		left.getChildren().add(sixth);
		left.getChildren().add(seventh);
		left.getChildren().add(state);
		left.getChildren().add(timeLeftForOrder);

		// instructions
		instructions.setFont(Font.font("Arial", 15));
		instructions.setTextFill(Color.BLACK);
		instructions.setAlignment(Pos.CENTER);
		instructions.setMinWidth(width / 4);

		// state
		state.setFont(Font.font("Arial", 15));
		state.setTextFill(Color.BLACK);
		state.setAlignment(Pos.CENTER);
		state.setMinWidth(width / 4);

		// time left
		timeLeftForOrder.setFont(Font.font("Arial", 15));
		timeLeftForOrder.setTextFill(Color.INDIANRED);
		timeLeftForOrder.setAlignment(Pos.CENTER);
		timeLeftForOrder.setMinWidth(width / 4);

	}

	/**
	 * Sets elements of the map on the graphical map
	 * 
	 * @param map - GridPane that represents the map graphically
	 */
	public static void showGUIMap(GridPane map) {
		map.getChildren().clear();

		for (int i = 0; i < game.map.getSize(); i++) {
			for (int j = 0; j < game.map.getSize(); j++) {
				Label x;
				if (i == game.map.getPlayer().getRow() && j == game.map.getPlayer().getCol()) {
					x = new Label();
					x.setGraphic(new ImageView(pizzaCar));
				} else if (game.map.getTiles()[i][j] instanceof Customer) {
					x = new Label();
					x.setGraphic(new ImageView(customerHouse));
				} else if (game.map.getTiles()[i][j] instanceof House) {
					x = new Label();
					x.setGraphic(new ImageView(house1));
				} else if (game.map.getTiles()[i][j] instanceof Trees) {
					x = new Label();
					x.setGraphic(new ImageView(tree));
				} else if (game.map.getTiles()[i][j] instanceof Pothole) {
					x = new Label();
					x.setGraphic(new ImageView(hole));
				} else {
					x = new Label(""); // shows empty if nothing else in tile
				}
				x.setAlignment(Pos.CENTER);
				x.setMinHeight(mapHeight / game.map.getSize());
				x.setMinWidth(mapWidth / game.map.getSize());
				map.add(x, j, i);
			}
			map.setPadding(new Insets(0, 0, 0, 0));
		}

	}

	// variables related to game play
	public static boolean gameOver = false;
	public static int timeForEachDelivery = 10; // in seconds
	public static double tipDeduction = 0.5; // tip deduction for each second that passed
	public static double currentTip = 5;

	public static GameTimer timer = new GameTimer();

	/**
	 * Sets the controls needed for the game
	 */
	public static void playGame(BorderPane playScreen) {

		// controls - actions to be done when a key is pressed
		playScreen.setOnKeyPressed(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent ke) {

				if (gameOver == false) {

					if (ke.getCode() == KeyCode.UP) {
						if (game.checkIfValidMove(1, currentTip)) {
							game.move(1);
							state.setText("Keep going!");
						} else {
							checkIfStrikeOrTip(1);
						}
					} else if (ke.getCode() == KeyCode.LEFT) {
						if (game.checkIfValidMove(2, currentTip)) {
							state.setText("Keep going!");
							game.move(2);
						} else {
							checkIfStrikeOrTip(2);
						}
					} else if (ke.getCode() == KeyCode.DOWN) {
						if (game.checkIfValidMove(3, currentTip)) {
							state.setText("Keep going!");
							game.move(3);
						} else {
							checkIfStrikeOrTip(3);
						}
					} else if (ke.getCode() == KeyCode.RIGHT) {
						if (game.checkIfValidMove(4, currentTip)) {
							state.setText("Keep going!");
							game.move(4);
						} else {
							checkIfStrikeOrTip(4);
						}
					}

					showGUIMap(mapGUI);

				}

			}

		});

	}

	/**
	 * Check why the player can't move - either player has delivered or has hit an
	 * obstacle If delivered - tips is updated If hit an obstacle - strikes is
	 * updated
	 * 
	 * @param direction
	 */
	public static void checkIfStrikeOrTip(int direction) {

		// updates tips
		((Labeled) heading.getChildren().get(2)).setText("Tips: $" + game.collectibles.getTipMoney());

		if (game.checkSurroundings(direction) instanceof Customer) {
			game.map.getPlayer().setPizzaDelivered(true);
		}

		if (game.checkSurroundings(direction) instanceof Obstacle) {
			// updates strikes
			displayStrike();
		}
	}

	/**
	 * Displays a strike warning on the right side of the screen
	 */
	public static void displayStrike() {
		Label strike = new Label("X");
		// strike.setForeground(Color.RED);
		state.setText("You hit an obstacle! 1 strike!");
		right.getChildren().add(strike);

		strike.setFont(Font.font("Arial", 15));
		strike.setTextFill(Color.BLACK);
		strike.setAlignment(Pos.CENTER);
		strike.setMinWidth(width / 4);
	}

	/**
	 * Resets the physical map by clearing each pane and making a new map
	 */
	public static void resetMap() {

		gameOver = false;

		game = new PizzaKid();
		game.map = new Map(new Avatar(), 17, PizzaKid.createTilesFor17());
		game.collectibles = new Collectibles(0, 0);

		game.map.generateCustomer();
		game.map.getCustomer().birthTime = game.collectibles.getTime();
		game.map.generateObstacles();

		heading.getChildren().clear();
		footer.getChildren().clear();
		right.getChildren().clear();
		left.getChildren().clear();

		initializePlayScreen();

		showGUIMap(mapGUI);
	}

	public static void getInitials() 
	//this method makes a window pop-up after the game is over, which asks for the player's initials so that the player's score can be recorded
	{

		
		VBox vbox = new VBox();
		Stage stage = new Stage();
		stage.setTitle("Game Over");
		
		//labels that show on the screen
		Label gameOverLabel = new Label("Game's Over!");
		String scoreText = "You earned $" + game.collectibles.getTipMoney() + ".";
		Label showScore = new Label(scoreText);
		Label enterInitials = new Label("Enter your 3 initials below: ");
		
		
		
		//style of the labels
		enterInitials.setFont(Font.font("Consolas", 15));
		enterInitials.setTextFill(Color.BLACK);
		enterInitials.setAlignment(Pos.CENTER);

		
		gameOverLabel.setFont(Font.font("Consolas", 20));
		gameOverLabel.setTextFill(Color.BLACK);
		gameOverLabel.setAlignment(Pos.CENTER);

		
		showScore.setFont(Font.font("Consolas", 15));
		showScore.setTextFill(Color.BLACK);
		showScore.setAlignment(Pos.CENTER);
		
//		
		TextField enterText = new TextField();
		double textFieldWidth = 100.0;
		enterText.setMaxWidth(textFieldWidth);

		Scene sceneI = new Scene(vbox, 300, 300); 
		stage.setScene(sceneI); 
		stage.show();
		
		//Action event for pressing Save button
		//Creates new 'initials' variable which is just what the player enters.
		//Adds underscores to player's initials if they entered less than 3 chars.
		//Only saves the first 3 chars if player enters more than 3 chars. Also sets to upper case.
		//Also closes the Game Over screen and returns player to start screen.
        EventHandler<ActionEvent> saveInitials = new EventHandler<ActionEvent>() 
        { 
            public void handle(ActionEvent e) 
            { 
            	String initials = enterText.getText();
            	
            	while(initials.length() < 3)
            	{
            		initials += "_";
            	}

            	PizzaKid.addScore(initials.toUpperCase().substring(0, 3), game.collectibles.getTipMoney());
            	
            	stage.close();
            	resetMap();
				playScreen.setVisible(false);
				startScreen.setVisible(true);
				startScreen.toFront();
				gameOver = true;
            	
            } 
        }; 
        
  
        Button saveInfo = new Button("Save");
        saveInfo.setFont(Font.font("Consolas", 18));

        vbox.getChildren().add(gameOverLabel);  //adds label "Game's Over" onto screen
        vbox.getChildren().add(showScore);
		vbox.getChildren().add(enterInitials); //adds instructions to enter player's intials
		vbox.getChildren().add(enterText); //adds textfield onto screen
		vbox.getChildren().add(saveInfo);//adds Save button to screen
        
        // when enter is pressed, sends player's score and initials to the scores.txt file, and closes the little window.
        saveInfo.setOnAction(saveInitials);
        
		
	}
	/**
	 * Makes the gameOver variable true, shows results to user, and resets the map
	 * 
	 * @param statement - reason why the game is over (time is up or 3 strikes
	 *                  reached)
	 */
	public static void gameIsOver(String statement) {
		gameOver = true;
		timeLeftForOrder.setText("");
	}

}

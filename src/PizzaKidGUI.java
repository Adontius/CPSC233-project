
import java.util.Scanner;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PizzaKidGUI extends Application {

	Image house1 = new Image("/HouseGrass.png", 75.0, 45.0, true, true); // uses House1.png image from folder
	Image customerHouse = new Image("/CustomerGrass.png", 76.0, 40.0, true, true);
	Image pizzaCar = new Image("/PizzaCar (1).png", 50.0, 25.0, true, true);
	Image tree = new Image("/Trees.png", 75.0, 55.0, true, true);
	Image hole = new Image("/Hole.png", 55.0, 55.0, true, true);
	Image road = new Image("/Road.png", 55.0, 55.0, true, true);

	private static PizzaKid game = new PizzaKid();
	private static Scanner input = new Scanner(System.in);

	// GUI related variables
	int height = 700;
	int width = 1000;

	int buttonHeight = 80;
	int buttonWidth = 100;

	int mapHeight = 540; // makes each tile size 45x45 pixels
	int mapWidth = 540;

	StackPane root = new StackPane();
	VBox startScreen = new VBox();
	BorderPane playScreen = new BorderPane();
	VBox instructionScreen = new VBox();

	boolean isPlaying = false;

	int timeDisplayInSeconds = 0;

	public static void main(String[] args) {
		game.map = new Map(new Avatar(), 12, PizzaKid.createTilesFor12());
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		gameOver = true;

		initializeStartScreen();
		initializePlayScreen();
		initializeInstructionScreen();

		root.getChildren().add(instructionScreen);
		root.getChildren().add(playScreen);
		root.getChildren().add(startScreen);

		Scene scene = new Scene(root, width, height);
		stage.setTitle("PizzaKid");
		stage.setScene(scene);
		stage.show();

	}

	/**
	 * Puts in and defines the elements in the start screen (title, start button, instructions button)
	 */
	public void initializeStartScreen() {

		// start screen style
		setStartScreenStyle();

		// title
		HBox top = new HBox();
		setStartingTitle(top);

		// start button
		HBox bottom = new HBox();
		setStartButton(bottom);
		
		// instructions button
		HBox feet = new HBox();
		setInstructionsButton(feet);

		startScreen.getChildren().add(top);
		startScreen.getChildren().add(bottom);
		startScreen.getChildren().add(feet);

	}

	/**
	 * Sets the style of the start screen
	 */
	public void setStartScreenStyle() {
		String style1 = "-fx-background-color: #ffb240;"; //sets start screen to orange
		startScreen.setStyle(style1);
	}

	/**
	 * Sets the style of the title and adds it to top part of start screen
	 * 
	 * @param top - the top part of the start screen where the title is placed
	 */
	public void setStartingTitle(HBox top) {
		top.setAlignment(Pos.CENTER);
		top.setMinHeight(height / 3);

		Label opening = new Label("PizzaKid");
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
	public void setStartButton(HBox bottom) {
		bottom.setAlignment(Pos.CENTER);
		bottom.setMinHeight(height / 3);

		Button start = new Button("Start");
		start.setFont(Font.font("Courier", 20));
		start.setMinSize(buttonWidth, buttonHeight);

		// event in start button
		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				startScreen.setVisible(false);
				playScreen.setVisible(true);
				isPlaying = true;
				playScreen.toFront();
				gameOver = false;
			}
		});

		bottom.getChildren().add(start);
	}
	

	/**
	 * Sets the instructions button's functionalities
	 * @param feet - the most bottom part of the start screen
	 */
	public void setInstructionsButton(HBox feet) {
		feet.setAlignment(Pos.CENTER);
		feet.setMinHeight(height / 3);

		Button instructions = new Button("Instructions");
		instructions.setFont(Font.font("Courier", 20));
		instructions.setMinSize(buttonWidth, buttonHeight);

		// event in start button
		instructions.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				startScreen.setVisible(false);
				playScreen.setVisible(false);
				instructionScreen.setVisible(true);
				isPlaying = false;
				instructionScreen.toFront();
				gameOver = true;
			}
		});

		feet.getChildren().add(instructions);
	}

	public void setHowToButton(HBox b)///alice attempt at making how to button on start screen plz help
	{
		b.setAlignment(Pos.BOTTOM_CENTER);
		b.setMinHeight(height / 3);
		
		Button howTo = new Button("How to Play");
		howTo.setFont(Font.font("Courier", 20));
		howTo.setMinSize(buttonWidth, buttonHeight);
		
		
	}
	
	// variables are outside since they need constant updating
	HBox heading = new HBox();
	GridPane mapGUI = new GridPane();
	HBox footer = new HBox();
	VBox right = new VBox();
	VBox left = new VBox();

	// state is a text that communicates the state of the game to the user
	Label state;
	// timeLeftForOrder is continuously updated to let user know the time left to
	// deliver the order
	Label timeLeftForOrder;

	public void initializePlayScreen() {

		// playScreen
		setPlayScreenStyle();

		// setting heading containing title, time, and tips
		setHeadings(heading);

		// setting map
		game.map = new Map(new Avatar(), 12, PizzaKid.createTilesFor12());
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
	public void setPlayScreenStyle() {
		playScreen.setMinHeight(height);
		playScreen.setMinWidth(width);
		String style2 = "-fx-background-color: #ADD8E6;";
		String style3 = "-fx-background-color: #576A75"; //same color as roads
		playScreen.setStyle(style2); /// set play screen color to light blue
		mapGUI.setStyle(style3); //set background of map to road color
	}

	/**
	 * Creates heading elements and sets their style
	 * 
	 * @param heading - HBox containing the elements of the heading
	 */
	public void setHeadings(HBox heading) {

		Label title = new Label("PizzaKid");
		Label time = new Label("Time left: " + timeDisplayInSeconds);
		Label tips = new Label("Tips: " + game.collectibles.getTipMoney());

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
		tips.setFont(Font.font("Arial", 15));
		tips.setTextFill(Color.BLACK);
		tips.setAlignment(Pos.CENTER);
		tips.setMinWidth(width / 4);
	}

	/**
	 * Sets the style of the map and calls showGUIMap to put in map elements
	 * 
	 * @param map - GridMap which contains the graphical map
	 */
	public void setMap(GridPane map) {
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
	public void setFooter(HBox footer) {

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
	public void setRight(VBox right) {
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
	public void setLeft(VBox left) {

		state = new Label("Start Playing!");
		timeLeftForOrder = new Label("");
		left.getChildren().add(state);
		left.getChildren().add(timeLeftForOrder);

		// state
		state.setFont(Font.font("Arial", 15));
		state.setTextFill(Color.BLACK);
		state.setAlignment(Pos.CENTER);
		state.setMinWidth(width / 4);

		// time left
		timeLeftForOrder.setFont(Font.font("Arial", 15));
		timeLeftForOrder.setTextFill(Color.BLACK);
		timeLeftForOrder.setAlignment(Pos.CENTER);
		timeLeftForOrder.setMinWidth(width / 4);

	}

	/**
	 * Initializes the Instruction Screen by putting in the instructions to the instruction VBox
	 */
	public void initializeInstructionScreen() {
		Label instructions = new Label("Instructions:");
		Label first = new Label("- Use the arrow keys to move");
		Label second = new Label("- Deliver pizza on time to get tips");
		Label third = new Label("- Earn the most tips in 1 minute!");
		Label fourth = new Label("- Hit an obstacle earn a strike!");
		Label fifth = new Label("- Run out of time earn a strike!");
		Label sixth = new Label("- 3 strikes = Game Over!");
		Label seventh = new Label("Good luck!");
		
		Button quit = new Button("Quit");
		setQuitInstructionsButton(quit);

		instructionScreen.getChildren().add(instructions);
		instructionScreen.getChildren().add(first);
		instructionScreen.getChildren().add(second);
		instructionScreen.getChildren().add(third);
		instructionScreen.getChildren().add(fourth);
		instructionScreen.getChildren().add(fifth);
		instructionScreen.getChildren().add(sixth);
		instructionScreen.getChildren().add(seventh);
		instructionScreen.getChildren().add(quit);

		instructions.setFont(Font.font("Arial", 15));
		instructions.setTextFill(Color.BLACK);
		instructions.setAlignment(Pos.CENTER);
		instructions.setMinWidth(width / 4);
	}
	
	/**
	 * Sets the quit button in the instructions screen
	 * @param button - quit button object
	 */
	public void setQuitInstructionsButton(Button button) {
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				startScreen.setVisible(true);
				playScreen.setVisible(false);
				instructionScreen.setVisible(false);
				startScreen.toFront();
			}
		});
	}

	/**
	 * Sets elements of the map on the graphical map
	 * 
	 * @param map - GridPane that represents the map graphically
	 */
	public void showGUIMap(GridPane map) {
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
					x = new Label(""); //shows empty if nothing else in tile
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

	public void playGame(BorderPane playScreen) {

		playScreen.setOnKeyPressed(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent ke) {

				if (gameOver == false) {

					if (game.map.getPlayer().getPizzaDelivered() == true
							&& game.map.getPlayer().getHitObstacle() == false) {
						game.map.generateCustomer();
						game.map.getCustomer().birthTime = game.collectibles.getTime();
						game.map.getPlayer().setPizzaDelivered(false); // resets pizzaDelivered after player delivers
																		// pizza
																		// to a customer.
						state.setText("You delivered a pizza!");
					}

					// when the player hits an obstacle, the obstacles are removed and regenerated
					// and the player is put back to starting position
					if (game.map.getPlayer().getHitObstacle() == true) {
						game.map.removeObstacle();
						game.map.getPlayer().setHitObstacle(false);
						game.map.generateObstacles();
						game.map.getPlayer().setCol(1);
						game.map.getPlayer().setRow(1);
						showGUIMap(mapGUI);
					} else if (ke.getCode() == KeyCode.UP) {
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

		AnimationTimer timer = new AnimationTimer() {

			int hectoseconds = 0;
			int seconds = 0;
			int minutes = 0;
			long before = 0;
			long timeSince;


			@Override
			public void handle(long now) {

				// everything in nanoseconds
				timeSince = now - before;
				if (gameOver == false) {
					if (timeSince >= 10000000) {
						hectoseconds++;
						if (hectoseconds >= 99) {
							hectoseconds = 0;
							seconds++;
							game.collectibles.setTime(game.collectibles.getTime() + 1);

							// to handle time left for order
							int timeLeft = timeForEachDelivery
									- (game.collectibles.getTime() - game.map.getCustomer().birthTime);
							if (timeLeft >= 0) {
								timeLeftForOrder.setText("Time left for order: " + timeLeft);
//								System.out.println("Time left for order: " + timeLeft);
//								System.out.println("Time in collectibles: " + game.collectibles.getTime());
//								System.out.println("Birth time: " + game.map.getCustomer().birthTime);
								currentTip = timeLeft * tipDeduction;
							} else {
								// strike if order is missed
								game.map.removeCustomer();
								game.map.generateCustomer();
								game.collectibles.addStrike();
								showGUIMap(mapGUI);
								displayStrike();
								state.setText("You missed an order!");
								game.map.getCustomer().birthTime = game.collectibles.getTime();
								currentTip = 5;
							}
						}
						
						// if strikes has reached 3, then game is over!
						if (game.collectibles.getStrikeCount() >= 3) {
							gameIsOver("You had 3 strikes!");
							hectoseconds = 0;
							seconds = 0;
							minutes = 0;
						}
						
						
						if (seconds >= 59) {
							seconds = 0;
							minutes++;
							gameIsOver("Your time is up!");
						}
						((Labeled) heading.getChildren().get(1))
								.setText("Timer: " + minutes + ":" + seconds + ":" + hectoseconds);
					}
				} else {
					
				}

			}
		};

		timer.start();

	}

	/**
	 * Makes the gameOver variable true, shows results to user, and resets the map
	 * 
	 * @param statement - reason why the game is over (time is up or 3 strikes
	 *                  reached)
	 */
	public void gameIsOver(String statement) {
		gameOver = true;
		state.setText("Game Over! " + statement + "\n" + "Your total tip is: $" + game.collectibles.getTipMoney()
				+ "\nPress reset to play again!");
		timeLeftForOrder.setText("");
		game = new PizzaKid();
		game.map = new Map(new Avatar(), 12, PizzaKid.createTilesFor12());
		game.collectibles = new Collectibles(0, 0);
		showGUIMap(mapGUI);
	}

	/**
	 * Check why the player can't move - either player has delivered or has hit an
	 * obstacle If delivered - tips is updated If hit an obstacle - strikes is
	 * updated
	 * 
	 * @param direction
	 */
	public void checkIfStrikeOrTip(int direction) {

		// updates tips
		((Labeled) heading.getChildren().get(2)).setText("Tips: " + game.collectibles.getTipMoney());

		if (game.checkSurroundings(direction) instanceof Obstacle) {
			// updates strikes
			displayStrike();
		}
	}

	public void displayStrike() {
		Label strike = new Label("Strike!!");
		state.setText("You hit an obstacle! 1 strike!");
		right.getChildren().add(strike);

		strike.setFont(Font.font("Arial", 15));
		strike.setTextFill(Color.BLACK);
		strike.setAlignment(Pos.CENTER);
		strike.setMinWidth(width / 4);
	}

	public void resetMap() {

		gameOver = false;

		game = new PizzaKid();
		game.map = new Map(new Avatar(), 12, PizzaKid.createTilesFor12());
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

}

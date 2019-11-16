
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

	private static PizzaKid game = new PizzaKid();
	private static Scanner input = new Scanner(System.in);

	// GUI related variables
	int height = 700;
	int width = 1000;

	int buttonHeight = 80;
	int buttonWidth = 100;

	int mapHeight = 400;
	int mapWidth = 500;

	int gridSize = 10;

	StackPane root = new StackPane();
	VBox startScreen = new VBox();
	BorderPane playScreen = new BorderPane();

	boolean isPlaying = false;

	int timeDisplayInSeconds = 0;

	public static void main(String[] args) {
		game.map = new Map(new Avatar(), 12, createTilesFor12());
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

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

		// start button
		HBox bottom = new HBox();
		setStartButton(bottom);

		startScreen.getChildren().add(top);
		startScreen.getChildren().add(bottom);

	}

	/**
	 * Sets the style of the start screen
	 */
	public void setStartScreenStyle() {
		String style = "-fx-background-color: rgba(0, 0, 0, 1);";
		startScreen.setStyle(style);
	}

	/**
	 * Sets the style of the title and adds it to top part of start screen
	 * 
	 * @param top
	 *            - the top part of the start screen where the title is placed
	 */
	public void setStartingTitle(HBox top) {
		top.setAlignment(Pos.CENTER);
		top.setMinHeight(height / 2);

		Label opening = new Label("Welcome to PizzaKid");
		opening.setFont(Font.font("Comfortaa", 40));
		opening.setTextFill(Color.FLORALWHITE);

		top.getChildren().add(opening);
	}

	/**
	 * Sets the style and the functionality of the button and adds it to bottom part
	 * of start screen
	 * 
	 * @param bottom
	 *            - the bottom part of the start screen where the button is placed
	 */
	public void setStartButton(HBox bottom) {
		bottom.setAlignment(Pos.CENTER);
		bottom.setMinHeight(height / 2);

		Button start = new Button("Start");
		start.setFont(Font.font("Arial Black", 20));
		start.setMinSize(buttonWidth, buttonHeight);

		// event in start button
		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				startScreen.setVisible(false);
				playScreen.setVisible(true);
				isPlaying = true;
				playScreen.toFront();
			}
		});

		bottom.getChildren().add(start);
	}

	// variables are outside since they need constant updating
	HBox heading = new HBox();
	GridPane mapGUI = new GridPane();
	HBox footer = new HBox();
	VBox right = new VBox();
	VBox left = new VBox();

	// state is a text that communicates the state of the game to the user
	Label state;

	public void initializePlayScreen() {

		// playScreen
		setPlayScreenStyle();

		// setting heading containing title, time, and tips
		setHeadings(heading);

		// setting map
		game.map = new Map(new Avatar(), 12, createTilesFor12());
		game.map.generateCustomer();
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
		String style2 = "-fx-background-color: rgba(245, 250, 250, 1);";
		playScreen.setStyle(style2);
	}

	/**
	 * Creates heading elements and sets their style
	 * 
	 * @param heading
	 *            - HBox containing the elements of the heading
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
		title.setFont(Font.font("Comfortaa", 30));
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
	 * @param map
	 *            - GridMap which contains the graphical map
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
	 * @param footer
	 *            - HBox containing button
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
	 * @param right
	 *            - VBox containing right elements
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
	 * @param right
	 *            - VBox containing left elements
	 */
	public void setLeft(VBox left) {
		Label instructions = new Label("Instructions:");
		Label first = new Label("- Use the arrow keys to move");
		Label second = new Label("- Deliver pizza on time to get tips");
		Label third = new Label("- Earn the most tips in 1 minute!");
		Label fourth = new Label("- Hit an obstacle earn a strike!");
		Label fifth = new Label("- Run out of time earn a strike!");
		Label sixth = new Label("- 3 strikes = Game Over!");
		Label seventh = new Label("- Good luck!");

		state = new Label("Start Playing!");

		left.getChildren().add(instructions);
		left.getChildren().add(first);
		left.getChildren().add(second);
		left.getChildren().add(third);
		left.getChildren().add(fourth);
		left.getChildren().add(fifth);
		left.getChildren().add(sixth);
		left.getChildren().add(seventh);
		left.getChildren().add(state);

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

	}

	/**
	 * Sets elements of the map on the graphical map
	 * 
	 * @param map
	 *            - GridPane that represents the map graphically
	 */
	public void showGUIMap(GridPane map) {
		map.getChildren().clear();

		for (int i = 0; i < game.map.getSize(); i++) {
			for (int j = 0; j < game.map.getSize(); j++) {
				Label x;
				if (i == game.map.getPlayer().getRow() && j == game.map.getPlayer().getCol()) {
					x = new Label("^");
				} else if (game.map.getTiles()[i][j] instanceof Customer) {
					x = new Label("*");
				} else if (game.map.getTiles()[i][j] instanceof House) {
					x = new Label("H");
				} else if (game.map.getTiles()[i][j] instanceof Trees) {
					x = new Label("T");
				} else if (game.map.getTiles()[i][j] instanceof Pothole) {
					x = new Label("X");
				} else {
					x = new Label("");
				}
				x.setAlignment(Pos.CENTER);
				x.setMinHeight(mapHeight / game.map.getSize());
				x.setMinWidth(mapWidth / game.map.getSize());
				map.add(x, j, i);
			}
		}
	}

	/**
	 * hard coding : creates the tiles needed for the map layout of a 12x12 map
	 * 
	 * @return - a 2d array of tiles
	 */
	public static Tile[][] createTilesFor12() {
		Tile[][] tiles = new Tile[12][12];

		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				// trees
				if (i == 0 || j == 0 || i == 11 || j == 11) {
					tiles[i][j] = new Trees();
				} else if (j == 1 && i > 2 && i < 11) {
					tiles[i][j] = new House();
				} else if ((j == 4 || j == 5) && i > 6 && i < 11) {
					tiles[i][j] = new House();
				} else if ((j == 8 || j == 9) && i > 7 && i < 11) {
					tiles[i][j] = new House();
				} else if ((i == 4 || i == 5) && j > 3 && j < 8) {
					tiles[i][j] = new House();
				} else if (i == 1 && j > 3 && j < 11) {
					tiles[i][j] = new House();
				} else if (j == 10 && i > 3 && i < 6) {
					tiles[i][j] = new House();
				} else {
					tiles[i][j] = new Road();
				}
			}
		}

		return tiles;
	}

	public void playGame(BorderPane playScreen) {

		playScreen.setOnKeyPressed(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent ke) {

				if (game.map.getPlayer().getPizzaDelivered() == true && game.map.getPlayer().getHitObstacle() == false) // alice
																														// edited
																														// this
																														// to
																														// add
																														// "
																														// ==
																														// true"
				{
					game.map.generateCustomer();
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
					if (game.checkIfValidMove(1)) {
						game.move(1);
						state.setText("Keep going!");
					} else {
						checkIfStrikeOrTip(1);
					}
				} else if (ke.getCode() == KeyCode.LEFT) {
					if (game.checkIfValidMove(2)) {
						state.setText("Keep going!");
						game.move(2);
					} else {
						checkIfStrikeOrTip(2);
					}
				} else if (ke.getCode() == KeyCode.DOWN) {
					if (game.checkIfValidMove(3)) {
						state.setText("Keep going!");
						game.move(3);
					} else {
						checkIfStrikeOrTip(3);
					}
				} else if (ke.getCode() == KeyCode.RIGHT) {
					if (game.checkIfValidMove(4)) {
						state.setText("Keep going!");
						game.move(4);
					} else {
						checkIfStrikeOrTip(4);
					}
				}

				showGUIMap(mapGUI);

				// if strikes has reached 3, then game is over!
				if (game.collectibles.getStrikeCount() >= 3) {
					state.setText("Game Over!");
					game.map = new Map(new Avatar(), 12, createTilesFor12());
				}

			}

		});

		
		AnimationTimer timer = new AnimationTimer() {

			int hectoseconds = 0;
			int seconds = 0;
			int minutes = 0;
			long before = 0;

			@Override
			public void handle(long now) {
				// everything in nanoseconds
				long timeSince = now - before;
				if (timeSince >= 10000000) {
					hectoseconds++;
					if(hectoseconds >= 99) {
						hectoseconds = 0;
						seconds++;
					}
					if(seconds >= 59) {
						seconds = 0;
						minutes++;
					}
					((Labeled) heading.getChildren().get(1)).setText("Timer: " + minutes + ":" + seconds + ":" + hectoseconds);
					before = now;
				}
			}
		};

		timer.start();

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
			Label strike = new Label("Strike!!!!");
			state.setText("You hit an obstacle! 1 strike!");
			right.getChildren().add(strike);

			strike.setFont(Font.font("Arial", 15));
			strike.setTextFill(Color.BLACK);
			strike.setAlignment(Pos.CENTER);
			strike.setMinWidth(width / 4);
		}
	}

	public void resetMap() {

		game = new PizzaKid();
		game.map = new Map(new Avatar(), 12, createTilesFor12());

		game.map.generateCustomer();
		game.map.generateObstacles();

		heading.getChildren().clear();
		footer.getChildren().clear();
		right.getChildren().clear();
		left.getChildren().clear();

		initializePlayScreen();

		showGUIMap(mapGUI);
	}

}


import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	GridPane mapGUI = new GridPane();
	
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
		
		//start screen style
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
	 * @param top - the top part of the start screen where the title is placed
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
	 * Sets the style and the functionality of the button and adds it to bottom part of start screen
	 * @param bottom - the bottom part of the start screen where the button is placed
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

	
	
	public void initializePlayScreen() {

		// playScreen
		setPlayScreenStyle();

		// setting heading containing title, time, and tips
		HBox heading = new HBox();
		setHeadings(heading);
		

		// setting map
		setMap(mapGUI);

		// setting footer containing quit button
		HBox footer = new HBox();
		setFooter(footer);


		playScreen.setTop(heading);
		playScreen.setCenter(mapGUI);
		playScreen.setBottom(footer);

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
				playScreen.setVisible(false);
				startScreen.setVisible(true);
				startScreen.toFront();
			}
		});

		footer.setAlignment(Pos.CENTER_RIGHT);
		footer.setPadding(new Insets(10, 50, 20, 10));
		footer.getChildren().add(quit);
	}
	
	/**
	 * Sets elements of the map on the graphical map
	 * @param map - GridPane that represents the map graphically
	 */
	public void showGUIMap(GridPane map) {
		for (int i = 0; i < game.map.getSize(); i++) {
			for (int j = 0; j < game.map.getSize(); j++) {
				Label x;
				if (i == game.map.getPlayer().getRow() && j == game.map.getPlayer().getCol()) {
					x = new Label("Player");
				} else if (game.map.getTiles()[i][j] instanceof Customer) {
					x = new Label("HouseOrder");
				} else if (game.map.getTiles()[i][j] instanceof House) {
					x = new Label("House");
				} else if (game.map.getTiles()[i][j] instanceof Trees) {
					x = new Label("Tree");
				} else if (game.map.getTiles()[i][j] instanceof Pothole) {
					x = new Label("Pothole");
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

}

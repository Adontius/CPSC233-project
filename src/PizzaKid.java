import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

public class PizzaKid extends Application{

	// private static Avatar player = new Avatar();
	private Map map = new Map();
	// collectibles
	private static Scanner input = new Scanner(System.in);
	// private static double score = 0;
	// private static int strike = 0;
	private static int tipAmount = 5;
	

	public static void main(String[] args) {
//		showStartScreen();
//		showPlayScreen();
		launch(args);
	}

	public static void showStartScreen() {
		System.out.println("Welcome to PizzaKid!");
		String num = "";
		do {
			System.out.println("Enter 1 to Start Playing");
			num = input.nextLine();
		} while (!num.equals("1"));
	}

	public void showPlayScreen() {
		String num = "";
		do {
			map = new Map();
			System.out.println();
			System.out.println("You are ^. Houses are H. House with order is O. Obstacles are X");
			System.out.println("Deliver a Pizza, earn tips!");
			System.out.println("Earn the most tips in 100 moves!");
			System.out.println("Hit an obstacle, earn a strike. 3 stirkes = Game Over!");
			System.out.println();
			System.out.println("Enter:");
			System.out.println("W to move up");
			System.out.println("S to move down");
			System.out.println("D to move right");
			System.out.println("A to move left");
			System.out.println("Enter 0 to quit");
			System.out.println();
			System.out.println("Good luck!");
			System.out.println();
			int counter = 0;
			map.getPlayer().setTipMoney(0);
			map.getPlayer().setStrikeCount(0);
			map.generateOrder();
			do {
				System.out.println("Tip money: $" + map.getPlayer().getTipMoney());
				System.out.println("Move: " + counter);
				System.out.println("Strikes: " + map.getPlayer().getStrikeCount());
				if (counter % 7 == 0 && counter != 0) {
					map.generateObstacle();
				}
				if (counter % 5 == 0) {
					map.generateOrder();
				}
				map.showMap();
				num = input.nextLine().toLowerCase();
				checkIfValid(num);
				checkIfDelivered();
				checkIfHit(num);
				counter++;
			} while (!num.equals("0") && counter <= 100 && map.getPlayer().getStrikeCount() < 3);
			System.out.println("Game Over");
			if (counter > 100) {
				System.out.println("Your moves have ended!");
			} else if (map.getPlayer().getStrikeCount() >= 3) {
				System.out.println("You got 3 strikes!");
			}
			System.out.println("Total tip: $" + map.getPlayer().getTipMoney());
			System.out.println();
			System.out.println("Enter 0 to quit");
			System.out.println("Enter anything else to continue playing");
			num = input.nextLine().toLowerCase();
		} while (!num.equals("0"));
	}

	public void checkIfValid(String str) {
		if (map.getPlayer().getHorzPos() == 0 && str.equals("w") || map.getPlayer().getVertPos() == 0 && str.equals("a")
				|| map.getPlayer().getHorzPos() == 9 && str.equals("s")
				|| map.getPlayer().getVertPos() == 9 && str.equals("d")) {
			System.out.println("Invalid move, try again");
		} else {
			if (str.equals("w")
					&& !map.getHasHouseAtIndex(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos() - 1))
					&& !map.getHasObstacleAtIndex(
							map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos() - 1))) {
				map.getPlayer().setHorzPos(map.getPlayer().getHorzPos() - 1);
			} else if (str.equals("s")
					&& !map.getHasHouseAtIndex(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos() + 1))
					&& !map.getHasObstacleAtIndex(
							map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos() + 1))) {
				map.getPlayer().setHorzPos(map.getPlayer().getHorzPos() + 1);
			} else if (str.equals("d")
					&& !map.getHasHouseAtIndex(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos()) + 1)
					&& !map.getHasObstacleAtIndex(
							map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos()) + 1)) {
				map.getPlayer().setVertPos(map.getPlayer().getVertPos() + 1);
			} else if (str.equals("a")
					&& !map.getHasHouseAtIndex(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos()) - 1)
					&& !map.getHasObstacleAtIndex(
							map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos()) - 1)) {
				map.getPlayer().setVertPos(map.getPlayer().getVertPos() - 1);
			} else {
				System.out.println("Invalid move, try again");
			}
		}
		System.out.println();
	}

	public boolean checkIfDelivered() {
		boolean delivered = false;
		// checks for each direction if house has order and removes order if true
		if (map.getPlayer().getHorzPos() >= 1 && map
				.getHasHouseAndOrderAtIndex(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos() - 1))) {
			map.setHasHouseAndOrderAtIndexFalse(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos() - 1));
			map.getPlayer().setTipMoney(map.getPlayer().getTipMoney() + tipAmount);
			delivered = true;
		}
		if (map.getPlayer().getHorzPos() <= 8 && map
				.getHasHouseAndOrderAtIndex(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos() + 1))) {
			map.setHasHouseAndOrderAtIndexFalse(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos() + 1));
			map.getPlayer().setTipMoney(map.getPlayer().getTipMoney() + tipAmount);
			delivered = true;
		}
		if (map.getPlayer().getVertPos() <= 8 && map
				.getHasHouseAndOrderAtIndex(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos()) + 1)) {
			map.setHasHouseAndOrderAtIndexFalse(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos()) + 1);
			map.getPlayer().setTipMoney(map.getPlayer().getTipMoney() + tipAmount);
			delivered = true;
		}
		if (map.getPlayer().getVertPos() >= 1 && map
				.getHasHouseAndOrderAtIndex(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos()) - 1)) {
			map.setHasHouseAndOrderAtIndexFalse(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos()) - 1);
			map.getPlayer().setTipMoney(map.getPlayer().getTipMoney() + tipAmount);
			delivered = true;
		}
		return delivered;
	}

	public void checkIfObstacle() {
		// checks for each direction if house has order and removes order if true
		if (map.getPlayer().getHorzPos() >= 1 && map
				.getHasHouseAndOrderAtIndex(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos() - 1))) {
			map.setHasHouseAndOrderAtIndexFalse(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos() - 1));
			map.getPlayer().addStrike();
		}
		if (map.getPlayer().getHorzPos() <= 8 && map
				.getHasHouseAndOrderAtIndex(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos() + 1))) {
			map.setHasHouseAndOrderAtIndexFalse(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos() + 1));
			map.getPlayer().addStrike();
		}
		if (map.getPlayer().getVertPos() <= 8 && map
				.getHasHouseAndOrderAtIndex(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos()) + 1)) {
			map.setHasHouseAndOrderAtIndexFalse(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos()) + 1);
			map.getPlayer().addStrike();
		}
		if (map.getPlayer().getVertPos() >= 1 && map
				.getHasHouseAndOrderAtIndex(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos()) - 1)) {
			map.setHasHouseAndOrderAtIndexFalse(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos()) - 1);
			map.getPlayer().addStrike();
		}
	}

	public void checkIfHit(String str) {
		// checks for each direction if theres obstacle and adds strike if true
		if (str.equals("w") && map.getPlayer().getHorzPos() >= 1
				&& map.getHasObstacleAtIndex(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos() - 1))) {
			map.setHasHouseAndOrderAtIndexFalse(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos() - 1));
			map.getPlayer().addStrike();
		}
		if (str.equals("s") && map.getPlayer().getHorzPos() <= 8
				&& map.getHasObstacleAtIndex(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos() + 1))) {
			map.setHasHouseAndOrderAtIndexFalse(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos() + 1));
			map.getPlayer().addStrike();
		}
		if (str.equals("d") && map.getPlayer().getVertPos() <= 8
				&& map.getHasObstacleAtIndex(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos()) + 1)) {
			map.setHasHouseAndOrderAtIndexFalse(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos()) + 1);
			map.getPlayer().addStrike();
		}
		if (str.equals("a") && map.getPlayer().getVertPos() >= 1
				&& map.getHasObstacleAtIndex(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos()) - 1)) {
			map.setHasHouseAndOrderAtIndexFalse(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos()) - 1);
			map.getPlayer().addStrike();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//GUI stuff
	
	int height = 700;
	int width = 1000;
	int buttonHeight = 80;
	int buttonWidth = 100;
	int gridSize = 10;
	StackPane root = new StackPane();
	VBox startScreen = new VBox();
	BorderPane playScreen = new BorderPane();
	
	public void start(Stage primaryStage) {

		initializeStartScreen();
		initializePlayScreen();

		root.getChildren().add(playScreen);
		root.getChildren().add(startScreen);

		Scene scene = new Scene(root, width, height);
		primaryStage.setTitle("PizzaKid");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void initializeStartScreen() {
		
		//setting start screen style
		String style = "-fx-background-color: rgba(0, 0, 0, 1);";
		startScreen.setStyle(style);
		
		// title
		HBox top = new HBox();
		top.setAlignment(Pos.CENTER);
		top.setMinHeight(height / 2);

		Label opening = new Label("Welcome to PizzaKid");
		opening.setFont(Font.font("Comfortaa", 40));
		opening.setTextFill(Color.FLORALWHITE);

		//start button
		HBox bottom = new HBox();
		bottom.setAlignment(Pos.CENTER);
		bottom.setMinHeight(height / 2);

		Button start = new Button("Start");
		start.setFont(Font.font("Arial Black", 20));
		start.setMinSize(buttonWidth, buttonHeight);

		//event in start button
		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				startScreen.setVisible(false);
				playScreen.setVisible(true);
				playScreen.toFront();
			}
		});

		top.getChildren().add(opening);
		bottom.getChildren().add(start);
		startScreen.getChildren().add(top);
		startScreen.getChildren().add(bottom);
	}

	public void initializePlayScreen() {
		
		//setting playScreen
		playScreen.setMinHeight(height);
		playScreen.setMinWidth(width);
		String style2 = "-fx-background-color: rgba(245, 250, 250, 1);";
		playScreen.setStyle(style2);
		
		//setting heading containing title, time, and tips
		HBox heading = new HBox();
		Label title = new Label("PizzaKid");
		Label time = new Label("Time left: ");
		Label tips = new Label("Tips: ");
		
		setHeadings(title, time, tips);

		heading.getChildren().add(title);
		heading.getChildren().add(time);
		heading.getChildren().add(tips);

		heading.setAlignment(Pos.CENTER);
		playScreen.setTop(heading);
		
		//setting map
		GridPane map = new GridPane();
		setMap(map);
		
		playScreen.setCenter(map);
		
		//setting footer containing quit button
		HBox footer = new HBox();
		footer.setMinWidth(width);
		
		Button quit = new Button("Quit");
		quit.setFont(Font.font("Arial Black", 15));
		quit.setMinSize(buttonWidth/2, buttonHeight/2);
		quit.setAlignment(Pos.CENTER);
		
		footer.setAlignment(Pos.CENTER_RIGHT);
		footer.setPadding(new Insets(10,50,20,10));
		footer.getChildren().add(quit);
		
		playScreen.setBottom(footer);

		//event in quit button
		quit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				playScreen.setVisible(false);
				startScreen.setVisible(true);
				startScreen.toFront();
			}
		});
	}

	private void setHeadings(Label title, Label time, Label tips) {
		//title
		title.setFont(Font.font("Comfortaa", 30));
		title.setTextFill(Color.BLACK);
		title.setAlignment(Pos.CENTER_LEFT);
		title.setMinWidth(width / 2);
		title.setPadding(new Insets(10, 0, 0, 50));

		//time
		time.setFont(Font.font("Arial", 15));
		time.setTextFill(Color.BLACK);
		time.setAlignment(Pos.CENTER);
		time.setMinWidth(width / 4);

		//tips
		tips.setFont(Font.font("Arial", 15));
		tips.setTextFill(Color.BLACK);
		tips.setAlignment(Pos.CENTER);
		tips.setMinWidth(width / 4);
	}
	
	private void setMap(GridPane map) {
		map.setVgap(5);
		map.setHgap(5);
		map.setPadding(new Insets(10,10,10,10));
		
		this.map.showGUIMap(map);
		
		map.setAlignment(Pos.CENTER);
	}
}


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

	int height = 700;
	int width = 1000;
	int buttonHeight = 80;
	int buttonWidth = 100;
	int gridSize = 10;
	StackPane root = new StackPane();
	VBox startScreen = new VBox();
	BorderPane playScreen = new BorderPane();
	int seconds = 0;
	boolean isPlaying = false;
	int timeDisplay = 0;
	GridPane mapGUI = new GridPane();
	
	public static void main(String[] args) {
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
	
	public void initializeStartScreen() {

		// setting start screen style
		String style = "-fx-background-color: rgba(0, 0, 0, 1);";
		startScreen.setStyle(style);

		// title
		HBox top = new HBox();
		top.setAlignment(Pos.CENTER);
		top.setMinHeight(height / 2);

		Label opening = new Label("Welcome to PizzaKid");
		opening.setFont(Font.font("Comfortaa", 40));
		opening.setTextFill(Color.FLORALWHITE);

		// start button
		HBox bottom = new HBox();
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

		top.getChildren().add(opening);
		bottom.getChildren().add(start);
		startScreen.getChildren().add(top);
		startScreen.getChildren().add(bottom);

	}

	public void initializePlayScreen() {
		
	}

}

//This class contains the timer for the game play "day".
//Will eventually contain the timers set for each new customer.
//author @alice
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameTimer extends AnimationTimer
{
	//constant to indicated how many seconds the timer counts down from
	private final int START_TIME = 60;
	
	private Label timerLabel = new Label();
	private int timeSeconds = START_TIME;
	
	public static void main(String[] args) 
	{
		Application.launch(args);
	}
	
	public void start(Stage primaryStage)
	{
		//Setup stage and scene
		primaryStage.setTitle("PizzaKid Timer");
		Group root = new Group();
		Scene scene = new Scene(root, 300, 300);
		
		//Details of the timer label
		//timerLabel.setText();
		timerLabel.setTextFill(Color.INDIANRED);
		
	}

	@Override
	public void handle(long now) {
		
	}
	
//	AnimationTimer timer = new AnimationTimer() {
//
//		@Override
//		public void handle(long arg0) {
//			map.showGUIMap(mapGUI);
//		}
//
//	};
//	timer.start();
}

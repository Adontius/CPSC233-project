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
import javafx.scene.control.Labeled;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameTimer extends AnimationTimer
{

	public static void main(String[] args) 
	{
		Application.launch(args);
	}


	int hectoseconds = 0;
	int seconds = 0;
	int minutes = 0;
	long before = 0;
	long timeSince;

	@Override
	public void handle(long now) {

		// everything in nanoseconds
		timeSince = now - before;
		if (PizzaKidGUI.gameOver == false) {
			if (timeSince >= 10000000) {
				hectoseconds++;
				if (hectoseconds >= 99) {
					hectoseconds = 0;
					seconds++;
					PizzaKidGUI.game.collectibles.setTime(PizzaKidGUI.game.collectibles.getTime() + 1);

					// to handle time left for order
					int timeLeft = PizzaKidGUI.timeForEachDelivery
							- (PizzaKidGUI.game.collectibles.getTime() - PizzaKidGUI.game.map.getCustomer().birthTime);
					if (timeLeft >= 0) {
						PizzaKidGUI.timeLeftForOrder.setText("Time left for order: " + timeLeft);
						PizzaKidGUI.currentTip = timeLeft * PizzaKidGUI.tipDeduction;
					} else {
						// strike if order is missed
						PizzaKidGUI.game.map.removeCustomer();
						PizzaKidGUI.game.map.generateCustomer();
						PizzaKidGUI.game.collectibles.addStrike();
						PizzaKidGUI.showGUIMap(PizzaKidGUI.mapGUI);
						PizzaKidGUI.displayStrike();
						PizzaKidGUI.state.setText("You missed an order!");
						PizzaKidGUI.game.map.getCustomer().birthTime = PizzaKidGUI.game.collectibles.getTime();
						PizzaKidGUI.currentTip = 5;
					}
				}
			
				if (PizzaKidGUI.game.map.getPlayer().getPizzaDelivered() == true
						&& PizzaKidGUI.game.map.getPlayer().getHitObstacle() == false) {
					PizzaKidGUI.game.map.generateCustomer();
					PizzaKidGUI.game.map.getCustomer().birthTime = PizzaKidGUI.game.collectibles.getTime();
					PizzaKidGUI.game.map.getPlayer().setPizzaDelivered(false); // resets pizzaDelivered after player delivers
																	// pizza
																	// to a customer.
					PizzaKidGUI.state.setText("You delivered a pizza!");
					PizzaKidGUI.showGUIMap(PizzaKidGUI.mapGUI);
				}

				// when the player hits an obstacle, the obstacles are removed and regenerated
				// and the player is put back to starting position
				if (PizzaKidGUI.game.map.getPlayer().getHitObstacle() == true) {
					PizzaKidGUI.game.map.removeObstacle();
					PizzaKidGUI.game.map.getPlayer().setHitObstacle(false);
					PizzaKidGUI.game.map.generateObstacles();
					PizzaKidGUI.game.map.getPlayer().setCol(1);
					PizzaKidGUI.game.map.getPlayer().setRow(1);
					PizzaKidGUI.showGUIMap(PizzaKidGUI.mapGUI);
				} else
				
				// if strikes has reached 3, then game is over!
				if (PizzaKidGUI.game.collectibles.getStrikeCount() >= 3) {
					PizzaKidGUI.gameIsOver("You had 3 strikes!");
					hectoseconds = 0;
					seconds = 0;
					minutes = 0;
				}
				
				
				if (seconds >= 59) {
					seconds = 0;
					minutes++;
					PizzaKidGUI.gameIsOver("Your time is up!");
				}
				((Labeled) PizzaKidGUI.heading.getChildren().get(1))
						.setText("Timer: " + minutes + ":" + seconds + ":" + hectoseconds);
			}
		} else {
			
		}
		before = now;
	}
	

}

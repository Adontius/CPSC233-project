/* This class displays/plays the text-based version of the PizzaKid game. It uses logic from the parent PizzaKid class.
 * 
 */
import java.util.Scanner;

public class PizzaKidText 
{

	private static PizzaKid game = new PizzaKid();
	private static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) 
	{
		game.map = new Map(new Avatar(), 17, PizzaKid.createTilesFor17());
		play();
	}

	/**
	 * The method where the player plays the game. Invokes other methods to display the appropriate 'screens':
	 * - Start Screen
	 * - Play Screen
	 */
	public static void play() 
	{
		showStartScreen();
		showPlayScreen();
	}

	/**
	 * Shows the start screen
	 * User inputs 1 to end method (and move on to the play screen)
	 */
	public static void showStartScreen() 
	{
		System.out.println("Welcome to PizzaKid!");
		String num = "";
		do 
		{
			System.out.println("Enter 1 to Start Playing");
			num = input.nextLine();
		} 
		while (!num.equals("1"));
	}
	
	/**
	 * Shows play screen
	 * Inner loop = loops every time the user inputs a move - checks if move is valid, if pizza is delivered,
	 * 		and if there are obstacles for each move
	 * Outer loop = loops the whole game, starts the game all over again when done, until the player quits
	 */
	public static void showPlayScreen() {
		String num = "";
		do 
		{
			System.out.println();
			System.out.println("You are ^. Houses are H. House with order is **. Obstacles are X. Trees are T");
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
			
			// generate initial customer and obstacles
			game.map.generateCustomer();
			game.map.generateObstacles();
			
			do 
			{
				System.out.println("Tip money: $" + game.collectibles.getTipMoney());//alice edited this to replace map reference with collectibles reference
				System.out.println("Move: " + counter);
				System.out.println("Strikes: " + game.collectibles.getStrikeCount());//'''
				
				if(game.map.getPlayer().getPizzaDelivered() == true) //alice edited this to add " == true"
				{
					game.map.generateCustomer();
					game.map.getPlayer().setPizzaDelivered(false); //resets pizzaDelivered after player delivers pizza to a customer.
				}
				
				// when the player hits an obstacle, the obstacles are removed and regenerated and the player is put back to starting position
				if(game.map.getPlayer().getHitObstacle() == true) {
					game.map.removeObstacle();
					game.map.getPlayer().setHitObstacle(false);
					game.map.generateObstacles();
					game.map.getPlayer().setCol(1);
					game.map.getPlayer().setRow(1);
				}
				
				displayMapToConsole();
				
				
				num = input.nextLine().toLowerCase(); //direction that the player enters (W, A, S, or D)
				
				if(num.equals("w")) 
				{
					if(game.checkIfValidMove(1, 5)) 
					{
						game.move(1);
					}
				} 
				else if(num.equals("a")) 
				{
					if(game.checkIfValidMove(2, 5)) 
					{
						game.move(2);
					}
				} 
				else if(num.equals("s")) 
				{
					if(game.checkIfValidMove(3, 5)) 
					{
						game.move(3);
					}
				} 
				else if(num.equals("d")) 
				{
					if(game.checkIfValidMove(4, 5)) 
					{
						game.move(4);
					}
				}
				counter++;
				
			} 
			while (!num.equals("0") && counter <= 100 && game.collectibles.getStrikeCount() < 3); //alice edited this to change map. to collectibles.
			System.out.println("Game Over");
			
			if (counter > 100) //if player has made more than 100 moves (i.e. on the 101th move), ends the game
			{
				System.out.println("Your moves have ended!");
			} 
			else if (game.collectibles.getStrikeCount() >= 3) //alice edited this to change map. to collectibles.
			{
				System.out.println("You got 3 strikes!");
			}
			System.out.println("Total tip: $" + game.collectibles.getTipMoney()); //alice edited this to change map. to collectibles.
			System.out.println();
			System.out.println("Enter 0 to quit");
			System.out.println("Enter anything else to continue playing");
			num = input.nextLine().toLowerCase();
		} 
		while (!num.equals("0"));
	}

	/**
	 * displays the map to the console
	 */
	public static void displayMapToConsole() {
		for (int i = 0; i < 17; i++) {
			for (int j = 0; j < 17; j++) {
				// trees

				if (i == game.map.getPlayer().getRow() && j == game.map.getPlayer().getCol()) {
					System.out.print("|^ |");
				} else if (game.map.getTiles()[i][j] instanceof Trees) {
					System.out.print("|T |");
				} else if (game.map.getTiles()[i][j] instanceof Pothole) {
					System.out.print("|X |");
				} else if (game.map.getTiles()[i][j] instanceof Customer) {
					System.out.print("|**|");
				} else if (game.map.getTiles()[i][j] instanceof House) {
					System.out.print("|H |");
				} else if (game.map.getTiles()[i][j] instanceof Road) {
					System.out.print("|  |");
				}
			}
			System.out.println();
		}
	}

}

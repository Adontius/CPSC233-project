/* This class displays/plays the text-based version of the PizzaKid game. It uses logic from the parent PizzaKid class.
 * 
 */
import java.util.Scanner;

public class PizzaKidText extends PizzaKid 
{

	public static Collectibles collectibles = new Collectibles(0,0);

	private static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) 
	{
		Map map = new Map(new Avatar(), 12, createTilesFor12());
		Collectibles collectibles = new Collectibles(0,0);
		play();
//		map.generateCustomer();
//		map.displayMapToConsole();
//		map.removeCustomer();
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
	
	public static void showPlayScreen() {
		String num = "";
		do 
		{
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
			
			map.generateCustomer();
			
			do 
			{
				System.out.println("Tip money: $" + collectibles.getTipMoney());
				System.out.println("Move: " + counter);
				System.out.println("Strikes: " + collectibles.getStrikeCount());
				if(map.getPlayer().getPizzaDelivered()) 
				{
					map.generateCustomer();
				}
				displayMapToConsople();
				num = input.nextLine().toLowerCase();
				

				System.out.println("inout: " + num);
				
				if(num.equals("w")) {
					if(checkIfValidMove(1)) {
						move(1);
					}
				} else if(num.equals("a")) {
					if(checkIfValidMove(2)) {
						move(2);
					}
				} else if(num.equals("s")) {
					if(checkIfValidMove(3)) {
						move(3);
					}
				} else if(num.equals("d")) {
					if(checkIfValidMove(4)) {
						move(4);
					}
				}
				
//				checkIfDelivered();
//				checkIfHit(num);
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
	


	/**
	 * displays the map to the console
	 */
	public static void displayMapToConsople() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				// trees

				if (i == map.getPlayer().getRow() && j == map.getPlayer().getCol()) {
					System.out.print("|^ |");
				} else if (map.getTiles()[i][j] instanceof Trees) {
					System.out.print("|T |");
				} else if (map.getTiles()[i][j] instanceof Pothole) {
					System.out.print("|X |");
				} else if (map.getTiles()[i][j] instanceof Customer) {
					System.out.print("|**|");
				} else if (map.getTiles()[i][j] instanceof House) {
					System.out.print("|H |");
				} else if (map.getTiles()[i][j] instanceof Road) {
					System.out.print("|  |");
				}
			}
			System.out.println();
		}
	}

}

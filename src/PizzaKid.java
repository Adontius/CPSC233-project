import java.util.ArrayList;
import java.util.Scanner;

public class PizzaKid {

	private static Avatar player = new Avatar();
	private static Map map = new Map();
	// collectibles
	private static Scanner input = new Scanner(System.in);
	private static double score = 0;

	public static void main(String[] args) {
		showStartScreen();
		showPlayScreen();
		// map.showMap();
	}

	public static void showStartScreen() {
		System.out.println("Welcome to PizzaKid!");
		String num = "";
		do {
			System.out.println("Enter 1 to Start Playing");
			num = input.nextLine();
		} while (!num.equals("1"));
	}

	public static void showPlayScreen() {
		String num = "";
		System.out.println("You are ^. Houses are h. House with order is h");
		System.out.println("Deliver the Most Pizzas in 100 moves!");
		System.out.println();
		System.out.println("Enter:");
		System.out.println("W to move up");
		System.out.println("S to move down");
		System.out.println("D to move right");
		System.out.println("A to move left");
		System.out.println("Enter 0 to quit");
		System.out.println();
		int counter = 0;
		map.generateOrder();
		do {
			System.out.println("Tip money: $" + score);
			System.out.println("Move: " + counter);
			if(counter % 3 == 0) {
				map.generateOrder();
			}
			map.showMap();
			num = input.nextLine().toLowerCase();
			checkIfValid(num);
			chechIfDelivered();
			counter++;
		} while (!num.equals("0") && counter <= 100);
		System.out.println("Total tip: $" + score);
	}

	private static void checkIfValid(String str) {
		if (map.getPlayerPositionX() == 0 && str.equals("w") || map.getPlayerPositionY() == 0 && str.equals("a")
				|| map.getPlayerPositionX() == 9 && str.equals("s")
				|| map.getPlayerPositionY() == 9 && str.equals("d")) {
			System.out.println("Invalid move, try again");
		} else {
			if (str.equals("w")
					&& !map.getHasHouseAtIndex(map.getPlayerPositionY() + 10 * (map.getPlayerPositionX() - 1))) {
				map.setPlayerPositionX(map.getPlayerPositionX() - 1);
			} else if (str.equals("s")
					&& !map.getHasHouseAtIndex(map.getPlayerPositionY() + 10 * (map.getPlayerPositionX() + 1))) {
				map.setPlayerPositionX(map.getPlayerPositionX() + 1);
			} else if (str.equals("d")
					&& !map.getHasHouseAtIndex(map.getPlayerPositionY() + 10 * (map.getPlayerPositionX()) + 1)) {
				map.setPlayerPositionY(map.getPlayerPositionY() + 1);
			} else if (str.equals("a")
					&& !map.getHasHouseAtIndex(map.getPlayerPositionY() + 10 * (map.getPlayerPositionX()) - 1)) {
				map.setPlayerPositionY(map.getPlayerPositionY() - 1);
			} else {
				System.out.println("Invalid move, try again");
			}
		}
		System.out.println();
	}
	
	private static void chechIfDelivered() {
		//checks for each direction fir house has order and removes order if true
		if (map.getPlayerPositionX() >= 1 && map.getHasHouseAndOrderAtIndex(map.getPlayerPositionY() + 10 * (map.getPlayerPositionX() - 1))) {
			map.setHasHouseAndOrderAtIndexFalse(map.getPlayerPositionY() + 10 * (map.getPlayerPositionX() - 1));
			score += 2.50;
		} else if (map.getPlayerPositionX() <= 8 && map.getHasHouseAndOrderAtIndex(map.getPlayerPositionY() + 10 * (map.getPlayerPositionX() + 1))) {
			map.setHasHouseAndOrderAtIndexFalse(map.getPlayerPositionY() + 10 * (map.getPlayerPositionX() + 1));
			score += 2.50;
		} else if (map.getPlayerPositionY() <= 8 && map.getHasHouseAndOrderAtIndex(map.getPlayerPositionY() + 10 * (map.getPlayerPositionX()) + 1)) {
			map.setHasHouseAndOrderAtIndexFalse(map.getPlayerPositionY() + 10 * (map.getPlayerPositionX()) + 1);
			score += 2.50;
		} else if (map.getPlayerPositionY() >= 1 && map.getHasHouseAndOrderAtIndex(map.getPlayerPositionY() + 10 * (map.getPlayerPositionX()) - 1)) {
			map.setHasHouseAndOrderAtIndexFalse(map.getPlayerPositionY() + 10 * (map.getPlayerPositionX()) - 1);
			score += 2.50;
		}
	}

}

import java.util.ArrayList;
import java.util.Scanner;

public class PizzaKid {

	// private static Avatar player = new Avatar();
	private static Map map = new Map();
	// collectibles
	private static Scanner input = new Scanner(System.in);
	// private static double score = 0;
	// private static int strike = 0;
	private static int tipAmount = 5;

	public static void main(String[] args) {
		showStartScreen();
		showPlayScreen();
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
				if (counter % 4 == 0) {
					map.generateOrder();
				}
				map.showMap();
				num = input.nextLine().toLowerCase();
				checkIfValid(num);
				checkIfDelivered();
				checkIfHit(num);
				counter++;
			} while (!num.equals("0") && counter <= 100 && map.getPlayer().getStrikeCount() <= 3);
			System.out.println("Game Over");
			if (counter > 100) {
				System.out.println("Your moves have ended!");
			} else if (map.getPlayer().getStrikeCount() >= 3) {
				System.out.println("You had more than 3 strikes!");
			}
			System.out.println("Total tip: $" + map.getPlayer().getTipMoney());
			System.out.println();
			System.out.println("Enter 0 to quit");
			System.out.println("Enter anything else to continue playing");
			num = input.nextLine().toLowerCase();
		} while (!num.equals("0"));
	}

	private static void checkIfValid(String str) {
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

	private static void checkIfDelivered() {
		// checks for each direction if house has order and removes order if true
		if (map.getPlayer().getHorzPos() >= 1 && map
				.getHasHouseAndOrderAtIndex(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos() - 1))) {
			map.setHasHouseAndOrderAtIndexFalse(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos() - 1));
			map.getPlayer().setTipMoney(map.getPlayer().getTipMoney() + tipAmount);
		}
		if (map.getPlayer().getHorzPos() <= 8 && map
				.getHasHouseAndOrderAtIndex(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos() + 1))) {
			map.setHasHouseAndOrderAtIndexFalse(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos() + 1));
			map.getPlayer().setTipMoney(map.getPlayer().getTipMoney() + tipAmount);
		}
		if (map.getPlayer().getVertPos() <= 8 && map
				.getHasHouseAndOrderAtIndex(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos()) + 1)) {
			map.setHasHouseAndOrderAtIndexFalse(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos()) + 1);
			map.getPlayer().setTipMoney(map.getPlayer().getTipMoney() + tipAmount);
		}
		if (map.getPlayer().getVertPos() >= 1 && map
				.getHasHouseAndOrderAtIndex(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos()) - 1)) {
			map.setHasHouseAndOrderAtIndexFalse(map.getPlayer().getVertPos() + 10 * (map.getPlayer().getHorzPos()) - 1);
			map.getPlayer().setTipMoney(map.getPlayer().getTipMoney() + tipAmount);
		}
	}

	private static void checkIfObstacle() {
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

	private static void checkIfHit(String str) {
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

}

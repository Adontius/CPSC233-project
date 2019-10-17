import java.util.ArrayList;
import java.util.Random;

public class Map {
	
	private Grid[][] grids; 
	private static Avatar player = new Avatar();

	private boolean[] hasHouse;
	private static boolean[] hasHouseAndOrder;
	private static boolean[] hasObstacle;
	private static int[] houses = {2, 3, 4, 5, 6, 7, 8, 9, 20, 22, 23, 24, 25, 26, 28, 29, 30, 34, 38, 39, 40, 42, 46, 50, 52, 54, 56, 58, 59, 60, 62, 66, 69, 70, 74, 79, 80, 82, 83, 84, 85, 86, 88, 89, 90};
	static ArrayList<Integer> roads = new ArrayList<Integer>();
	
	public Map() {
		grids = new Grid[10][10];
		hasHouse = new boolean[100]; //boolean to store whether or not a cell on the grid has a House in it
		hasHouseAndOrder = new boolean[100]; //boolean to store whether or not a cell on the grid has a House with an order (customer) in it
		hasObstacle = new boolean[100]; //boolean to store whether or not a cell on the grid has an obstacle in it
//		housePositionX = new ArrayList<Integer>(12);
//		housePositionY = new ArrayList<Integer>(12);
		generateHouses();
	}
	
	public Avatar getPlayer()
	{
		return player;
	}
	
	public boolean getHasHouseAtIndex(int index) {
		return hasHouse[index];
	}
	
	public boolean getHasHouseAndOrderAtIndex(int index) {
		return hasHouseAndOrder[index];
	}
	
	public boolean getHasObstacleAtIndex(int index) {
		return hasObstacle[index];
	}
	
	public void setHasHouseAndOrderAtIndexFalse(int i) {
		hasHouseAndOrder[i] = false;
	}
	
	public void showMap() {
		System.out.println();
		for(int i = 0; i < 10; i++) {
			System.out.print(" ___ ");
		}
		System.out.println();
		int counter = 0;
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(i == player.horzPos && j == player.vertPos) {
					System.out.print("|_^_|");
				} else if(hasHouseAndOrder[counter]){
					System.out.print("|_O_|");
				} else if(hasHouse[counter]) {
					System.out.print("|_H_|");
				} else if(hasObstacle[counter]) {
					System.out.print("|_X_|");
				} else {
					System.out.print("|___|");
				}
				counter++;
			}
			System.out.println();
		}
	}
	
	public void generateHouses() {
		for(int i = 0; i < hasHouse.length; i++) {
			if(i >= 2 && i <= 9) {
				hasHouse[i] = true;
			} else if(i >= 22 && i <= 26) {
				hasHouse[i] = true;
				hasHouse[i + 60] = true;
			} else if(i == 42 || i == 52 || i == 62) {
				hasHouse[i] = true;
				hasHouse[i+4] = true;
			} else if(i % 10 == 0 && i != 10 && i != 0) {
				hasHouse[i] = true;
			} else if(i == 28) {
				hasHouse[i] = true;
				hasHouse[i+10] = true;
				hasHouse[i+30] = true;
				hasHouse[i+60] = true;
			} else if(i == 29) {
				hasHouse[i] = true;
				hasHouse[i+10] = true;
				hasHouse[i+30] = true;
				hasHouse[i+40] = true;
				hasHouse[i+50] = true;
				hasHouse[i+60] = true;
			} else if(i == 34) {
				hasHouse[i] = true;
				hasHouse[i+20] = true;
				hasHouse[i+40] = true;
			}
		}
		

	}
	
	public static void generateOrder() {
		// 45 houses
		Random r = new Random();
		int index = r.nextInt(45);
		hasHouseAndOrder[houses[index]] = true;
	}
	
// 	public static int generateObstacle() {
// 		roads = new ArrayList<Integer>();
// 		int counter = 0;
// 		for(int i = 1; i <= 100; i++) {
// 			if(i == houses[counter]) {
// 				if (counter < 44) {
// 					counter++;
// 				}
// 			} else {
// 				roads.add(i);
// 			}
// 		}
// 		// add obstacle on roadway
// 		Random r = new Random();
// 		int randNum = r.nextInt(45);
// 		hasObstacle[roads.get(2)] = true;
// //		hasObstacle[roads.get(randNum)] = true;
// 		return randNum;
// 	}
	
// 	public static void removeObstacle(int num) {
// 		hasObstacle[roads.get(num)] = false;
// 		System.out.println("obstacle " + num + " removed");
// 	}
	
}

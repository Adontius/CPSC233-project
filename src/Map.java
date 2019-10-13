import java.util.ArrayList;
import java.util.Random;

public class Map {
	
	private Grid[][] grids;
	private int playerPositionX, playerPositionY; // change to Grid later
//	private ArrayList<Integer> housePositionX;
//	private ArrayList<Integer> housePositionY;
	private boolean[] hasHouse;
	private static boolean[] hasHouseAndOrder;
	
	public Map() {
		grids = new Grid[10][10];
		playerPositionX = 0;
		playerPositionY = 0;
		hasHouse = new boolean[100];
		hasHouseAndOrder = new boolean[100];
//		housePositionX = new ArrayList<Integer>(12);
//		housePositionY = new ArrayList<Integer>(12);
		generateHouses();
	}
	
	public int getPlayerPositionX() {
		return playerPositionX;
	}
	
	public int getPlayerPositionY() {
		return playerPositionY;
	}
	
	public boolean getHasHouseAtIndex(int index) {
		return hasHouse[index];
	}
	
	public void setPlayerPositionX(int i) {
		if(i >= 0 && i < 10) {
			playerPositionX = i;
		}
	}
	
	public void setPlayerPositionY(int i) {
		if(i >= 0 && i < 10) {
			playerPositionY = i;
		}
	}
	
	public void showMap() {
		for(int i = 0; i < 10; i++) {
			System.out.print(" ___ ");
		}
		System.out.println();
		int counter = 0;
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(i == playerPositionX && j == playerPositionY) {
					System.out.print("|_^_|");
				} else if(hasHouseAndOrder[counter]){
					System.out.print("|_H_|");
				} else if(hasHouse[counter]) {
					System.out.print("|_h_|");
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
		
//		for(int i = 2; i < 10; i++) {
//			housePositionX.add(1);
//			housePositionY.add(i);
//			housePositionX.add(i);
//			housePositionY.add(1);
//		}
//		for(int i = 2; i < 7; i++) {
//			housePositionX.add(2);
//			housePositionY.add(i);
//			housePositionX.add(8);
//			housePositionY.add(i);
//		}
//		for(int i = 4; i < 7; i++) {
//			housePositionX.add(i);
//			housePositionY.add(2);
//			housePositionX.add(i);
//			housePositionY.add(2);
//		}
//		
//		for(int i = 5; i < 9; i++) {
//			housePositionX.add(9);
//			housePositionY.add(i);
//		}
//		
//		housePositionX.add(3);
//		housePositionY.add(4);
//		housePositionX.add(7);
//		housePositionY.add(4);
//
//		housePositionX.add(5);
//		housePositionY.add(4);
//
//		housePositionX.add(9);
//		housePositionY.add(2);
//		housePositionX.add(9);
//		housePositionY.add(6);
	}
	
	public static void generateOrder() {
		// 45 houses
		int[] houses = {2, 3, 4, 5, 6, 7, 8, 9, 20, 22, 23, 24, 25, 26, 28, 29, 30, 34, 38, 39, 40, 42, 46, 50, 52, 54, 56, 58, 59, 60, 62, 66, 69, 70, 74, 79, 80, 82, 83, 84, 85, 86, 88, 89, 90};
		Random r = new Random();
		int index = r.nextInt(45);
		hasHouseAndOrder[houses[index]] = true;
	}
	
}

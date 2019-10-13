import java.util.ArrayList;
import java.util.Random;

public class Map {
	
	private Grid[][] grids;
	private int playerPositionX, playerPositionY; // change to Grid later
//	private ArrayList<Integer> housePositionX;
//	private ArrayList<Integer> housePositionY;
	private boolean[] hasHouse;
	
	public Map() {
		grids = new Grid[10][10];
		playerPositionX = 0;
		playerPositionY = 0;
		hasHouse = new boolean[100];
//		housePositionX = new ArrayList<Integer>(12);
//		housePositionY = new ArrayList<Integer>(12);
		generateHouses();
	}
	
	public void showMap() {
		for(int i = 0; i < 10; i++) {
			System.out.print(" ___ ");
		}
		System.out.println();
		int counter = 0;
		for(int i = playerPositionX; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(i == playerPositionX && j == playerPositionY) {
					System.out.print("|_P_|");
				} else if(hasHouse[counter]) {
					System.out.print("|_H_|");
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
			} else if(i == 92) {
				hasHouse[i] = true;
				hasHouse[i+4] = true;
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
	
}

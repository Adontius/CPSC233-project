import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import com.sun.prism.paint.Color;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Map {

	private static final javafx.geometry.Insets Insets = null;
	private Grid[][] grids;
	private static Avatar player;

	private boolean[] hasHouse;
	private boolean[] hasHouseAndOrder;
	private boolean[] hasObstacle;
	private int[] houses = { 3, 4, 5, 6, 7, 8, 9, 20, 30, 33, 34, 35, 36, 39, 40, 43, 44, 45, 46, 50, 60, 70, 73, 74,
			77, 78, 80, 83, 84, 87, 88, 90, 93, 94, 97, 98 };
	static ArrayList<Integer> roads = new ArrayList<Integer>();
	private int size;
	private int mapHeight;
	private int mapWidth;

	public Map() {
		grids = new Grid[10][10];
		hasHouse = new boolean[100]; // boolean to store whether or not a cell on the grid has a House in it
		hasHouseAndOrder = new boolean[100]; // boolean to store whether or not a cell on the grid has a House with an
												// order (customer) in it
		hasObstacle = new boolean[100]; // boolean to store whether or not a cell on the grid has an obstacle in it
		player = new Avatar();
		// housePositionX = new ArrayList<Integer>(12);
		// housePositionY = new ArrayList<Integer>(12);
		generateHouses();
		generateRoads();
		size = 10;
		mapHeight = 400;
		mapWidth = 800;
	}

	public Avatar getPlayer() {
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
		for (int i = 0; i < 10; i++) {
			System.out.print(" ___ ");
		}
		System.out.println();
		int counter = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (i == player.horzPos && j == player.vertPos) {
					System.out.print("|_^_|");
				} else if (hasHouseAndOrder[counter]) {
					System.out.print("|_O_|");
				} else if (hasHouse[counter]) {
					System.out.print("|_H_|");
				} else if (hasObstacle[counter]) {
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
		for (int i = 0; i < hasHouse.length; i++) {
			if (i >= 3 && i <= 9) {
				hasHouse[i] = true;
			} /*
				 * else if(i >= 23 && i <= 26) { hasHouse[i] = true; hasHouse[i + 60] = true; }
				 * /*else if(i == 42 || i == 52 || i == 62) { hasHouse[i] = true; hasHouse[i+4]
				 * = true; }
				 */
			else if (i >= 33 && i <= 36) {
				hasHouse[i] = true;
				// hasHouse[i+60] = true;
			} else if (i >= 43 && i <= 46) {
				hasHouse[i] = true;
			} else if (i % 10 == 0 && i != 10 && i != 0) {
				hasHouse[i] = true;
			} /*
				 * else if(i == 28) { hasHouse[i] = true; hasHouse[i+10] = true; hasHouse[i+30]
				 * = true; hasHouse[i+60] = true; }
				 */else if (i == 39) {
				hasHouse[i] = true;
				hasHouse[i + 10] = true;
				/*
				 * hasHouse[i+30] = true; hasHouse[i+40] = true; hasHouse[i+50] = true;
				 * hasHouse[i+60] = true;
				 */
			} else if (i == 73) {
				hasHouse[i] = true;
				hasHouse[i + 10] = true;
				hasHouse[i + 20] = true;
				// hasHouse[i+30] = true;
			} else if (i == 74) {
				hasHouse[i] = true;
				hasHouse[i + 10] = true;
				hasHouse[i + 20] = true;
			} else if (i == 77) {
				hasHouse[i] = true;
				hasHouse[i + 10] = true;
				hasHouse[i + 20] = true;
			} else if (i == 78) {
				hasHouse[i] = true;
				hasHouse[i + 10] = true;
				hasHouse[i + 20] = true;
			}
		}

	}

	public void generateOrder() {
		// 45 houses
		Random r = new Random();
		int index = r.nextInt(houses.length);
		hasHouseAndOrder[houses[index]] = true;
	}

	public void generateRoads() {
		roads = new ArrayList<Integer>();
		int counter = 0;
		for (int i = 1; i <= 100; i++) {
			if (i == houses[counter]) {
				if (counter < houses.length - 1) {
					counter++;
				}
			} else {
				roads.add(i);
			}
		}
	}

	public int generateObstacle() {
		// add obstacle on roadway
		Random r = new Random();
		int randNum = r.nextInt(roads.size());
		hasObstacle[roads.get(randNum) - 1] = true;
		return roads.get(randNum);
	}

	// GUI stuff

	public void showGUIMap(GridPane map) {
		int counter = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Label x;
				if (i == player.vertPos && j == player.horzPos) {
					x = new Label();
//					Image image = new Image("http://pixelartmaker.com/art/1f35e220dd65e03", true);
					File imageFile = new File("C:\\Users\\agrav\\eclipse-workspace\\CPSC233-project\\src\\car.png");
					Image car = new Image(imageFile.toURI().toString());
					ImageView carView = new ImageView(car);
					carView.setFitHeight(mapHeight / size);
					carView.setFitWidth(mapWidth / size);
					x.setGraphic(carView);
				} else if (hasHouseAndOrder[counter]) {
					x = new Label("HouseOrder");
				} else if (hasHouse[counter]) {
					x = new Label();
					File imageFile = new File("C:\\Users\\agrav\\eclipse-workspace\\CPSC233-project\\src\\house1.png");
					Image house = new Image(imageFile.toURI().toString());
					ImageView houseView = new ImageView(house);
					houseView.setFitHeight(mapWidth / size);
					houseView.setFitWidth(mapWidth / size);
					x.setGraphic(houseView);
				} else if (hasObstacle[counter]) {
					x = new Label("Obstacle");
				} else {
					x = new Label("");
				}
				counter++;
				x.setAlignment(Pos.CENTER);
				x.setMinHeight(mapHeight / size);
				x.setMinWidth(mapWidth / size);
				map.add(x, j, i);
			}
		}
	}

	public void clearGUIMap(GridPane map) {
		
		int counter = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Label x = new Label();
				counter++;
				x.setAlignment(Pos.CENTER);
				x.setMinHeight(mapHeight / size);
				x.setMinWidth(mapWidth / size);
				x.setStyle("-fx-background-color: rgba(245, 250, 250, 1);");
				map.add(x, j, i);
			}
		}
	}
	
	public boolean hasObstacleOnTop() {
		return (getHasHouseAtIndex(player.getHorzPos() + 10 * (player.getVertPos() - 1)) && getHasObstacleAtIndex(player.getHorzPos() + 10 * (player.getVertPos() - 1)));
	}
	
	public boolean hasObstacleOnBottom() {
		return (getHasHouseAtIndex(player.getHorzPos() + 10 * (player.getVertPos() + 1)) && getHasObstacleAtIndex(player.getHorzPos() + 10 * (player.getVertPos() + 1)));
	}

	public boolean hasObstacleOnRight() {
		return (getHasHouseAtIndex(player.getHorzPos() + 10 * (player.getVertPos()) + 1) && getHasObstacleAtIndex(player.getHorzPos() + 10 * (player.getVertPos()) + 1));
	}

	public boolean hasObstacleOnLeft() {
		return (getHasHouseAtIndex(player.getHorzPos() + 10 * (player.getVertPos()) + 1) && getHasObstacleAtIndex(player.getHorzPos() + 10 * (player.getVertPos()) - 1));
	}
	
	public void updateDirection() {
		if (player.direction == 1 && !hasObstacleOnTop() && player.getVertPos() > 0) {
			player.moveUp();
		} else if (player.direction == 2 && !hasObstacleOnLeft() && player.getHorzPos() > 0) {
			player.moveLeft();
		} else if (player.direction == 3 && !hasObstacleOnBottom() && player.getVertPos() < 9) {
			player.moveDown();
		} else if (player.direction == 4 && !hasObstacleOnRight() && player.getHorzPos() < 9) {
			player.moveRight();
		}
	}
	

}

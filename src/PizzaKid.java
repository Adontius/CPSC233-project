import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PizzaKid 
{

	public Map map;
	public static String scoresFile = "scores.txt";
	
	public Collectibles collectibles = new Collectibles(0, 0); 
	//intiates new player collectibles, sets tipmoney to 0 and strike count to 0

	/* checks the direction that the player is going and returns the object that is
	 * in that direction.
	 * 
	 * @param direction
	 *   - int value that represents the direction that the player is going
	 * @return Tile 
	 *   - The object that is in the surroundings
	 */
	public Tile checkSurroundings(int direction) {
		// direction definitions: 0: Stop, 1: Up, 2: Left, 3: Down, 4: Right
		
		if (direction == 1)//checks tile above player
		{
			return map.getTiles()[map.getPlayer().getRow() - 1][map.getPlayer().getCol()];
		} 
		else if (direction == 2) //checks tile to the left of player
		{
			return map.getTiles()[map.getPlayer().getRow()][map.getPlayer().getCol() - 1];
		} 
		else if (direction == 3) //checks tile below player
		{
			return map.getTiles()[map.getPlayer().getRow() + 1][map.getPlayer().getCol()];
		} 
		else if (direction == 4) //checks tile to the right of player
		{
			return map.getTiles()[map.getPlayer().getRow()][map.getPlayer().getCol() + 1];
		} else 
		{
			return null;
		}
	}

	/**
	 * delivers the pizza by removing customer order, receiving tip money, and
	 * cancelling timer
	 * Note: should be overriden since text and gui are different
	 */
	public void deliverPizza(double tipToAdd) {
		map.removeCustomer();
		collectibles.setTipMoney(collectibles.getTipMoney() + tipToAdd);
	}

	/**
	 * checks if move is valid
	 * 
	 * @param direction
	 *            - int value that represents the direction that the player is going
	 *       tipToAdd
	 *       	- tip to add if the tile in the direction is a customer and pizza is to be delivered
	 * @return boolean - true if the move is valid, false if otherwise
	 */
	public boolean checkIfValidMove(int direction, double tipToAdd) {
		// direction: 0 - stop, 1 - up, 2 - left, 3 - down, 4 - right
		if (direction == 1) {
			if (map.getPlayer().getRow() > 0) {
				if (checkSurroundings(direction) instanceof Road) {
					return true;
				} else if (checkSurroundings(direction) instanceof Customer) {
					deliverPizza(tipToAdd);
					return false;
				} else if (checkSurroundings(direction) instanceof Obstacle) {
					collectibles.addStrike();
					map.getPlayer().setHitObstacle(true);
					return false;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else if (direction == 2) {
			if (map.getPlayer().getCol() > 0) {
				if (checkSurroundings(direction) instanceof Road) {
					return true;
				} else if (checkSurroundings(direction) instanceof Customer) {
					deliverPizza(tipToAdd);
					return false;
				} else if (checkSurroundings(direction) instanceof Obstacle) {
					collectibles.addStrike();
					map.getPlayer().setHitObstacle(true);
					return false;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else if (direction == 3) {
			if (map.getPlayer().getRow() < map.getSize() - 1) {
				if (checkSurroundings(direction) instanceof Road) {
					return true;
				} else if (checkSurroundings(direction) instanceof Customer) {
					deliverPizza(tipToAdd);
					return false;
				} else if (checkSurroundings(direction) instanceof Obstacle) {
					collectibles.addStrike();
					map.getPlayer().setHitObstacle(true);
					return false;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else if (direction == 4) {
			if (map.getPlayer().getCol() < map.getSize() - 1) {
				if (checkSurroundings(direction) instanceof Road) {
					return true;
				} else if (checkSurroundings(direction) instanceof Customer) {
					deliverPizza(tipToAdd);
					return false;
				} else if (checkSurroundings(direction) instanceof Obstacle) {
					collectibles.addStrike();
					map.getPlayer().setHitObstacle(true);
					return false;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		return false;
	}
	

	/**
	 * hard coding : creates the tiles needed for the map layout of a 17x17 map
	 * 
	 * @return - a 2d array of tiles
	 */
	public static Tile[][] createTilesFor17() {
		Tile[][] tiles = new Tile[17][17];

		for (int i = 0; i < 17; i++) {
			for (int j = 0; j < 17; j++) {
				// trees
				if (i == 0 || j == 0 || i == 16|| j == 16) {
					tiles[i][j] = new Trees();
				} else if (j == 3 && i > 2 && i < 14) {
					tiles[i][j] = new House(false);
				} else if (i == 3 && j > 5 && j < 14) {
					tiles[i][j] = new House(false);
				} else if (i == 6 && j > 5 && j < 10) {
					tiles[i][j] = new House(false);
				} else if (i == 9 && j > 5 && j < 10) {
					tiles[i][j] = new House(false);
				} else if (i == 12 && j > 5 && j < 10) {
					tiles[i][j] = new House(false);
				} else if (j == 12 && i > 5 && i < 14) {
					tiles[i][j] = new House(false);
				} else if (i == 15 && j == 6) {
					tiles[i][j] = new House(false);
				} else if (i == 15 && j == 9) {
					tiles[i][j] = new House(false);
				}  else if (j == 15 && i == 6) {
					tiles[i][j] = new House(false);
				} else if (j == 15 && i == 8) {
					tiles[i][j] = new House(false);
				} else if (j == 15 && i == 10) {
					tiles[i][j] = new House(false);
				} else if (j == 15 && i == 12) {
					tiles[i][j] = new House(false);
				} else if (j == 15 && i == 14) {
					tiles[i][j] = new House(false);
				}
				else {
					tiles[i][j] = new Road();
				}
			}
		}

		return tiles;
	}
	
	/**
	 * Moves the player based on the direction passed
	 * @param direction - direction of the movement: 1 - up, 2 - left, 3 - down, 4 - right
	 */
	public void move(int direction) {
		if(direction == 1) {
			map.getPlayer().moveUp();
		} else if(direction == 2) {
			map.getPlayer().moveLeft();
		} else if(direction == 3) {
			map.getPlayer().moveDown();
		} else if(direction == 4) {
			map.getPlayer().moveRight();
		} 
	}
	
	public static void addScore(String initials, int score) {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(scoresFile, true));
			out.println(initials + ": " + score);
			out.close();
		} catch (IOException ioe) {
			System.out.println("IO Exception!");
		}
	}
	
//	public static int readNextScore() {
//		try {
//			Scanner in = new Scanner(new FileInputStream(scoresFile));
//			int nextInt = in.readInt();
//			in.close();
//			return nextInt;
//		} catch (IOException ioe) {
//			System.out.println("IO Exception!");
//			return 0;
//		}
//	}
	
	public static void main(String[] args) {
		addScore("AD", 5);
		addScore("FD", 6);
		addScore("DD", 9);
		addScore("VD", 7);
//		System.out.println(readNextScore());
	}
	
}

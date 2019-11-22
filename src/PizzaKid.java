
public class PizzaKid 
{

	public Map map;
	
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
					tiles[i][j] = new House(false);
				} else if ((j == 4 || j == 5) && i > 7 && i < 11) {
					tiles[i][j] = new House(false);
				} else if ((j == 8 || j == 9) && i > 7 && i < 11) {
					tiles[i][j] = new House(false);
				} else if ((i == 4 || i == 5) && j > 3 && j < 8) {
					tiles[i][j] = new House(false);
				} else if (i == 1 && j > 3 && j < 11) {
					tiles[i][j] = new House(false);
				} else if (j == 10 && i > 3 && i < 6) {
					tiles[i][j] = new House(false);
				} else {
					tiles[i][j] = new Road();
				}
			}
		}

		return tiles;
	}
	
	
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
	
}

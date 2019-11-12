
public class PizzaKid {
	
	public static void main(String[] args) {
		Tile[][] tiles = createTilesFor12();
		for(int i = 0; i < 12; i++) {
			for(int j = 0; j < 12; j++) {
				// trees
				if(tiles[i][j] instanceof Trees) {
					System.out.print("|T|");
				} else if(tiles[i][j] instanceof House) {
					System.out.print("|H|");
				} else if(tiles[i][j] instanceof Road) {
					System.out.print("| |");
				}
			}
			System.out.println();
		}
	}
	
	/**
	 * checks the direction that the player is going and returns the object that is in it
	 * @param direction - int value that represents the direction that the player is going
	 * @return Tile - The object that is in the surroundings
	 */
	public Tile checkSurroundings(int direction) {
		return null;
	}
	
	/**
	 * delivers the pizza by removing customer order, receiving tip money, and cancelling timer
	 */
	public void deliverPizza() {
		
	}
	
	/**
	 * checks if move is valid
	 * @param direction - int value that represents the direction that the player is going
	 * @return boolean - true if the move is valid, false if otherwise
	 */
	public boolean checkIfValidMove(int direction) {
		return true;
	}
	
	/**
	 * hard coding : creates the tiles needed for the map layout of a 12x12 map
	 * @return - a 2d array of tiles
	 */
	public static Tile[][] createTilesFor12() {
		Tile[][] tiles = new Tile[12][12];
		
		for(int i = 0; i < 12; i++) {
			for(int j = 0; j < 12; j++) {
				// trees
				if(i == 0 || j == 0 || i == 11 || j == 11) {
					tiles[i][j] = new Trees();
				} else if(j == 1 && i > 2 && i < 11) {
					tiles[i][j] = new House();
				} else if((j == 4 || j == 5) && i > 6 && i < 11) {
					tiles[i][j] = new House();
				} else if((j == 8 || j == 9) && i > 7 && i < 11) {
					tiles[i][j] = new House();
				} else if((i == 4 || i == 5) && j > 3 && j < 8) {
					tiles[i][j] = new House();
				} else if(i == 1 && j > 3 && j < 11) {
					tiles[i][j] = new House();
				} else if(j == 10 && i > 3 && i < 6) {
					tiles[i][j] = new House();
				} else {
					tiles[i][j] = new Road();
				}
			}
		}
		
		return tiles;
	}

}

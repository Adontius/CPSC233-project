import java.util.ArrayList;
import java.util.Random;

public class Map 
{

	public Avatar player; //initiates new Avatar object called player
	public Tile tiles[][]; //creates new grid of tiles to play on
	public int mapSize; //int of the length/width of the map

	public Map(Avatar player, int mapSize, Tile tiles[][]) 
	{
		this.player = player;
		setSize(mapSize);
		setTiles(tiles);
	}

	// getters
	public int getSize() 
	{
		return mapSize;
	}

	public Tile[][] getTiles() 
	{
		Tile[][] tiles2 = new Tile[tiles.length][tiles.length];
		for (int i = 0; i < tiles.length; i++) 
		{
			for (int j = 0; j < tiles.length; j++) 
			{
				tiles2[i][j] = tiles[i][j];
			}
		}
		return tiles2;
	}

	public Avatar getPlayer() 
	{
		return player;
	}
	

	// setters
	public void setSize(int size) {
		this.mapSize = size;
	}

	public void setTiles(Tile[][] tiles2) {
		tiles = new Tile[tiles2.length][tiles2.length];
		for (int i = 0; i < tiles2.length; i++) {
			for (int j = 0; j < tiles2.length; j++) {
				tiles[i][j] = tiles2[i][j];
			}
		}
	}

	public void setPlayer(Avatar player) {
		Avatar player2 = new Avatar(player);
		player = player2;
	}

	/**
	 * generates a customer by doing the following:
	 * - creating arraylists of row numbers and column numbers, with each index representing a house
	 * - adding existing houses to the arraylists
	 * - generating a random number representing a random index for the arraylist, and geting its row and column number
	 * - turning the house on the original array as a customer
	 */
	public void generateCustomer() {
		ArrayList<Integer> rowNumbers = new ArrayList<Integer>(0);
		ArrayList<Integer> colNumbers = new ArrayList<Integer>(0);
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				if (tiles[i][j] instanceof House) {
					if (!(tiles[i][j] instanceof Customer)) {
						rowNumbers.add(i);
						colNumbers.add(j);
					}
				}
			}
		}
		Random rand = new Random();
		int randInt = rand.nextInt(rowNumbers.size());
		
		int randomHouseRowPosition = rowNumbers.get(randInt);
		int randomHouseColPosition = colNumbers.get(randInt);
		
		tiles[randomHouseRowPosition][randomHouseColPosition] = new Customer();
		player.setPizzaDelivered(false);
	}
	
	/**
	 * turns the customer back into a house - used after delivering pizza
	 */
	public void removeCustomer() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				if (tiles[i][j] instanceof House) {
					if ((tiles[i][j] instanceof Customer)) {
						tiles[i][j] = new House();
					}
				}
			}
		}
		player.deliverPizza();
	}

}

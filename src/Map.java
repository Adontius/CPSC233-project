
public class Map {

	Avatar player;
	Tile tiles[][];
	int size;

	public Map(Avatar player, int size, Tile tiles[][]) {
		setPlayer(player);
		setSize(size);
		setTiles(tiles);
	}

	//getters
	public int getSize() {
		return size;
	}
	
	public Tile[][] getTiles() {
		Tile[][] tiles2 = new Tile[tiles.length][tiles.length];
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles.length; j++) {
				tiles2[i][j] = tiles[i][j];
			}
		}
		return tiles2;
	}
	
	public Avatar getPlayer() {
		Avatar player2 = new Avatar(player);
		return player2;
	}
	
	//setters
	public void setSize(int size) {
		this.size = size;
	}

	public void setTiles(Tile[][] tiles2) {
		tiles = new Tile[tiles2.length][tiles2.length];
		for(int i = 0; i < tiles2.length; i++) {
			for(int j = 0; j < tiles2.length; j++) {
				tiles[i][j] = tiles2[i][j];
			}
		}
	}
	
	public void setPlayer(Avatar player) {
		Avatar player2 = new Avatar(player);
		player = player2;
	}
	
	/**
	 * displays the map to the console
	 */
	public void displayMapToConsople() {
		for(int i = 0; i < 12; i++) {
			for(int j = 0; j < 12; j++) {
				// trees
				if(tiles[i][j] instanceof Trees) {
					System.out.print("|T |");
				} else if(tiles[i][j] instanceof House) {
					System.out.print("|H |");
				} else if(tiles[i][j] instanceof Pothole) {
					System.out.print("|X |");
				} else if(tiles[i][j] instanceof Customer) {
					System.out.print("|H'|");
				} else if(tiles[i][j] instanceof Road) {
					System.out.print("|  |");
				}
			}
			System.out.println();
		}
	}
}

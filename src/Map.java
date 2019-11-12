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

	Avatar player;
	Tile tiles[][];
	int size;

	public Map(int size, Tile tiles[][]) {
		player = new Avatar();
		this.size = size;
		this.tiles = tiles;
	}

	//getters and setters
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
	
	public void setSize(int size) {

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
	
}

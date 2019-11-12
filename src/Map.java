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

}

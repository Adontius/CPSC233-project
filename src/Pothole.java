import java.util.ArrayList;
import java.util.Random;

/**Potholes are a type of Obstacle.
 * They are randomly generated on the Map, and respawn in a new random location once the player has ran into them.
 * Running into a Pothole gives the player a strike and the player is relocated to the starting location.
 */
public class Pothole extends Obstacle
{
	private int npothole = 3;
	
	public Pothole(){
		super();
	}
	
	public int GetNpothole() {
		return npothole;
	}
	
	public void SetNpothole() {
		return;
	}
//	/**
//	 * 
//	 */
//	public void generateObstacles() {
//		ArrayList<Integer> rowNumbers = new ArrayList<Integer>(0);
//		ArrayList<Integer> colNumbers = new ArrayList<Integer>(0);
//		for (int i = 0; i < 12; i++) {
//			for (int j = 0; j < 12; j++) {
//				if (tiles[i][j] instanceof Road) {
//					rowNumbers.add(i);
//					colNumbers.add(j);
//				}
//			}
//		}
//		Random rand = new Random();
//
//		int numOfObstacles = 3;
//
//		for (int i = 0; i < numOfObstacles; i++) {
//			int randInt = rand.nextInt(rowNumbers.size());
//
//			int randomHouseRowPosition = rowNumbers.get(randInt);
//			int randomHouseColPosition = colNumbers.get(randInt);
//
//			tiles[randomHouseRowPosition][randomHouseColPosition] = new Pothole();
//		}
//	}
}

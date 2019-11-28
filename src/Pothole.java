
/**Potholes are a type of Obstacle.
 * They are randomly generated on the Map, and respawn in a new random location once the player has ran into them.
 * Running into a Pothole gives the player a strike and the player is relocated to the starting location.
 */
public class Pothole extends Obstacle
{
	private int npothole = 3;
	
	//potholes are impassable 
	public Pothole()
	{
		super(); 
	}
	
	//returns number of potholes set to spawn
	public int GetNpothole()
	{
		return npothole;
	}
	
	//sets the numbers of potholes that spawn
	public void SetNpothole(int n)
	{
		this.npothole = n;
	}

}

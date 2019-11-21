/* A house is an immovable object which the player cannot pass through. 
 * Houses can be an empty house (no pizza order) OR they can be a Customer (has pizza order), which is extended from this class. 
 * 
 */
public class House extends Tile
{
	boolean hasOrder = false;
	
	public House(boolean hasOrder)
	{
		super(false); //houses are not passable
		this.hasOrder = hasOrder;
		
	}
	
}

/* Collectibles acts as a tracker for TipMoney and Strikes accumulated by the player.
 * It contains setternmethods to add strikes and add tip money as well as their respective getter methods.
 */

public class Collectibles
{
	private int tipMoney; // counter for amount of tip money the avatar has accumulated. Has setter and getter.
	public int strikeCount; // counter for number of strikes the avatar has accummulated. Has setter and getter.
	
	public Collectibles(int newTip, int newStrikes)
	{
		this.tipMoney = 0;
		this.strikeCount = 0;
	}
	
	public Collectibles(Collectibles toCopy)
	{
		this.tipMoney = toCopy.tipMoney;
		this.strikeCount = toCopy.strikeCount;
	}
	
	public void giveTipMoney(int tipAmount)// adds the customer's tip to the avatar's tip money balance
	{
		this.tipMoney += tipAmount;
	}

	public void setTipMoney(int tipMoney)// setter method for tipMoney
	{
		this.tipMoney = tipMoney;
	}

	public int getTipMoney() // getter method to return the player's current tip money total
	{
		return this.tipMoney;
	}

	public void addStrike()// adds a strike to the avatar' strike count.
	{
		strikeCount += 1;
	}

	public void setStrikeCount(int strikeCount)// setter method to set the number of strikes the avatar has.
	{
		this.strikeCount = strikeCount;
	}

	public int getStrikeCount() //getter method to return the number of strikes the player has
	{
		return this.strikeCount;
	}
	
}

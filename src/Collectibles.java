import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/* Collectibles acts as a tracker for TipMoney and Strikes accumulated by the player.
 * It contains setternmethods to add strikes and add tip money as well as their respective getter methods.
 */

public class Collectibles
{
	private double tipMoney; // counter for amount of tip money the avatar has accumulated. Has setter and getter.
	public int strikeCount; // counter for number of strikes the avatar has accummulated. Has setter and getter.
	public int time; //counter for the amount of time (in seconds) - different from animation timer
	
	public Collectibles(int newTip, int newStrikes)
	{
		this.tipMoney = 0;
		this.strikeCount = 0;
		this.time = 0;
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

	public void setTipMoney(double tipMoney)// setter method for tipMoney
	{
		this.tipMoney = tipMoney;
	}

	public double getTipMoney() // getter method to return the player's current tip money total
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

	public void setTime(int time)// setter method to set the number of strikes the avatar has.
	{
		this.time = time;
	}

	public int getTime() //getter method to return the number of strikes the player has
	{
		return this.time;
	}
	
	public static String getHighScores(int index) throws IOException
	{
		DataInputStream readScores = new DataInputStream(new FileInputStream(PizzaKid.scoresFile));
		int numberOfScores = PizzaKid.scoresFile.length();
		
		ArrayList<Double> allScores= new ArrayList<Double>(); //creates an arraylist to hold the scores from the scores.txt file
		
		Scanner reader = new Scanner(readScores);
		
		// loop through the file and separates initials from scores (into two separate arraylists)
		while(reader.hasNextLine())
		{
			String everything = reader.nextLine(); //creates array of split up initials and scores, from the scores.txt file .split(": ", 1)

			allScores.add(Double.parseDouble(everything));
			
		}
		
		Collections.sort(allScores);
		Collections.reverse(allScores);
		
		ArrayList<String> top5Scores= new ArrayList<String>();
		for(int i = 0; i < 5; i++) 
		{
			top5Scores.add(reader.nextLine());
		}
		
		return top5Scores.get(index);
		
		
	}
}

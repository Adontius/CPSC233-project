/*Customer is a type of House, which means it just has a pizza order that the player needs to fulfill.
 *Creating a new customer (generateCustomer()) will start a count of how long that player has taken to deliver to that customer
 *(uses checkDifference()).
 *
 *checkDifference() checks the difference between the "birth time" of the customer (when the customer was generated) with respect to
 *the GameTimer. If the player takes too long too deliver the pizza (greater than orderTimeLimit variable), the player recieves a strike
 *and that customer disappears (no more order).
 * 
 */

public class Customer extends House 
{
	
	int birthTime = 0;
	
	public Customer() {
		super(true); //house has an order to be a customer
	}
	
	public void setBirthTime(int birthTime)
	{
		this.birthTime = birthTime;
	}
	
	public int getBirthTime()
	{
		return this.birthTime;
	}
}

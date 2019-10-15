//This class holds the variables related to the avatar, including tip money count, strike count, vertical and horizontal position, and whether
//the avatar has delivered pizza or not. It also has movement methods (move up, down, left, or right). Each variable has it's respective setter 
//and getter methods.
//author @alice

public class Avatar
{
    private int tipMoney; //counter for amount of tip money the avatar has accumulated. Has setter and getter.
    public int strikeCount; //counter for number of strikes the avatar has accummulated. Has setter and getter.
    public int vertPos; //vertical position of avatar (y axis). Has setter and getter.
    public int horzPos; //horizontal position of avatar (x axis). Has setter and getter.
    public boolean pizzaDelivered; //booelan to store whether pizza has been delivered (true) or not (false). Has setter and getter.

    public Avatar()
    {
        //initializes avatar stats at zero
        tipMoney = 0; 
        strikeCount = 0;
        vertPos = 0;
        horzPos = 0; //sets initial position of avatar to (0, 0)
        pizzaDelivered = false;
    }

    public void setVertPos(int vertPos)//sets avatar's vertical position
    {
        this.vertPos = vertPos;
    }

    public int getVertPos()//returns the vertical position of the avatar
    {
        return this.vertPos;
    }

    public void setHorzPos(int horzPos)//sets the avatar's horizontal position
    {
        this.horzPos = horzPos;
    }

    public int getHorzPos()//returns the horizontal position of the avatar
    {
        return this.horzPos;
    }

    public void moveUp() //moves avatar one unit up (y + 1)
    {
        vertPos += 1;
    }

    public void moveDown() //moves avatar one unit down (y - 1)
    {
        vertPos -= 1;
    }

    public void moveRight()//moves avatar one unit right (x + 1)
    {
        horzPos += 1;
    }

    public void moveLeft()//moves avatar one unit left (x - 1)
    {
        horzPos -= 1;
    }

    public boolean deliverPizza() //when invoked, sets pizzaDelivered to true to signal that a pizza has been delivered. No count of pizzas required
    {
        pizzaDelivered = true;
        return this.pizzaDelivered;
    }

    public void setPizzaDelivered(boolean pizzaDelivered)//setter method to set whether pizza has been delivered (true) or not (false)
    {
        this.pizzaDelivered = pizzaDelivered;
    }

    public boolean getPizzaDelivered()//getter method that returns whether the pizza has been delivered (true) or not (false)
    {
        return this.pizzaDelivered;
    }

    public void receiveTip(int tipAmount)//adds the customer's tip to the avatar's tip money balance
    {
        tipMoney += tipAmount;
    }

    public void setTipMoney(int tipMoney)//setter method for tipMoney
    {
        this.tipMoney = tipMoney;
    }

    public int getTipMoney()
    {
        return this.tipMoney;
    }

    public void addStrike()//adds a strike to the avatar' strike count.
    {
        strikeCount += 1;
    }

    public void setStrikeCount(int strikeCount)//setter method to set the number of strikes the avatar has.
    {
        this.strikeCount = strikeCount;
    }

}
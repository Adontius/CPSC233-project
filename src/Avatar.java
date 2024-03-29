//This class holds the variables related to the avatar, including tip money count, strike count, vertical and horizontal position, and whether
//the avatar has delivered pizza or not. It also has movement methods (move up, down, left, or right). Each variable has it's respective setter 
//and getter methods.
//author @alice

//important note: y-axis is flipped, so decreasing vertPos value means avatar moves up and vice versa

public class Avatar {
	
	public int col; // vertical position of avatar (y axis). Has setter and getter.
	public int row; // horizontal position of avatar (x axis). Has setter and getter.
	public boolean pizzaDelivered; // booelan to store whether pizza has been delivered (true) or not (false). Has setter and getter.
	public boolean hitObstacle; // boolean to store whether the Avatar hit an obstacle or not
	
	// 0 - stop, 1 - up, 2 - left, 3 - down, 4 - right
	int direction = 0;
	
	public Avatar() {
		// initializes avatar stats at zero
		this.col = 1;
		this.direction = 0;
		this.row = 1; // sets initial position of avatar to (1, 1)
		this.pizzaDelivered = false;
		this.hitObstacle = false;
	}

	public Avatar(Avatar player) {
		
		this.col = player.col;
		this.direction = player.direction;
		this.row = player.row; // sets initial position of avatar to (0, 0)
		this.pizzaDelivered = player.pizzaDelivered;
		this.hitObstacle = player.hitObstacle;
	}

	public void setCol(int i) // sets the avatar's vertical position
	{
		if (i >= 0 && i < 20) {
			this.col = i;
		}
	}

	public void setRow(int i) // sets the avatar's horizontal position
	{
		if (i >= 0 && i < 20) {
			this.row = i;
		}
	}

	public int getCol()// returns the vertical position of the avatar
	{
		return this.col;
	}

	public int getRow()// returns the horizontal position of the avatar
	{
		return this.row;
	}

	public void moveUp() // moves avatar one unit up (y + 1)
	{
		row -= 1;
	}

	public void moveDown() // moves avatar one unit down (y - 1)
	{
		row += 1;
	}

	public void moveRight()// moves avatar one unit right (x + 1)
	{
		col += 1;
	}

	public void moveLeft()// moves avatar one unit left (x - 1)
	{
		col -= 1;
	}

	
	/**
	 * deliverPizza(): when invoked, sets pizzaDelivered to true to signal that a pizza has been
	 * delivered. Invoked by player using a button or click. No count of pizzas required.
	 * @return returns Boolean of whether pizza has been delivered (true) or not (false)
	 */
	public boolean deliverPizza() 
	{
		pizzaDelivered = true;
		return this.pizzaDelivered;
	}

	public void setPizzaDelivered(boolean pizzaDelivered)// setter method to set whether pizza has been delivered (true)
															// or not (false)
	{
		this.pizzaDelivered = pizzaDelivered;
	}

	
	/*
	 * getter method that returns Bool on whether the pizza has been delivered (true) 
	 * or not (false)
	 */
	public boolean getPizzaDelivered()
	{
		return this.pizzaDelivered;
	}
	
	/**
	 * setter method for setting the hitObstacle variable
	 */
	public void setHitObstacle(boolean hitObstacle)
	{
		this.hitObstacle = hitObstacle;
	}
	
	/**
	 * getter method for hitObstacle
	 * @return boolean value of hitObstacle
	 */
	public boolean getHitObstacle()
	{
		return this.hitObstacle;
	}
	
	public void setDirection(int i) //allows player's facing-direction to be set to either 0, 1, 2, 3, or 4 (stop, up, left, down, right)
	{
		if (i >= 0 && i <= 4) 
		{
			direction = i;
		}
	}
}
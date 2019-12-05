import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.*;

public class PizzaKidTest {

	@Test
	public final void testCheckSurroundings_customerAbove() 
	{
		Avatar p = new Avatar();
		Tile[][] tile = new Tile[5][5];
		p.setCol(2);
		p.setRow(2);
		tile[1][2] = new Customer();
		
		PizzaKid pk = new PizzaKid();
		pk.map = new Map(p, 5, tile);		
		
		assertEquals("Checked if tile above player is a customer.", true, pk.checkSurroundings(1) instanceof Customer);
	}

	@Test
	public final void testCheckSurroundings_customerBelow() 
	{
		Avatar p = new Avatar();
		Tile[][] tile = new Tile[5][5];
		p.setCol(2);
		p.setRow(2);
		tile[3][2] = new Customer();
		
		PizzaKid pk = new PizzaKid();
		pk.map = new Map(p, 5, tile);		
		
		assertEquals("Checked if tile below player is a customer.", true, pk.checkSurroundings(3) instanceof Customer);
	}

	@Test
	public final void testCheckSurroundings_customerLeft() 
	{
		Avatar p = new Avatar();
		Tile[][] tile = new Tile[5][5];
		p.setCol(2);
		p.setRow(2);
		tile[2][1] = new Customer();
		
		PizzaKid pk = new PizzaKid();
		pk.map = new Map(p, 5, tile);		
		
		assertEquals("Checked if tile to left is a customer.", true, pk.checkSurroundings(2) instanceof Customer);
	}

	@Test
	public final void testCheckSurroundings_customerRight() 
	{
		Avatar p = new Avatar();
		Tile[][] tile = new Tile[5][5];
		p.setCol(2);
		p.setRow(2);
		tile[2][3] = new Customer();
		
		PizzaKid pk = new PizzaKid();
		pk.map = new Map(p, 5, tile);		
		
		assertEquals("Checked if tile to right is a customer.", true, pk.checkSurroundings(4) instanceof Customer);
	}
	
	@Test
	public final void testPizzaDelivered_customerRemoved() 
	{
		Avatar p = new Avatar();
		Tile[][] tile = new Tile[5][5];
		tile[2][3] = new Customer();
		
		PizzaKid pk = new PizzaKid();
		pk.map = new Map(p, 5, tile);
		pk.collectibles = new Collectibles(0, 0);
		pk.deliverPizza(0);
		
		assertEquals("Checked if the customer is removed after pizza is delivered.", true, !(tile[2][3] instanceof Customer));
	}

	
	@Test
	public final void testPizzaDelivered_tipMoneyAdded() 
	{
		Avatar p = new Avatar();
		Tile[][] tile = new Tile[5][5];
		tile[2][3] = new Customer();
		
		PizzaKid pk = new PizzaKid();
		pk.map = new Map(p, 5, tile);
		pk.collectibles = new Collectibles(0, 0);
		pk.deliverPizza(5);
		
		assertEquals("Checked if the customer is removed after pizza is delivered.", true, (pk.collectibles.getTipMoney() == 5));
	}
	
	@Test
	public final void checkIfValidMove_roadAndUp() 
	{
		Avatar p = new Avatar();
		p.setCol(0);
		p.setRow(1);
		Tile[][] tile = new Tile[5][5];
		tile[0][0] = new Road();
		
		PizzaKid pk = new PizzaKid();
		pk.map = new Map(p, 5, tile);
		pk.collectibles = new Collectibles(0, 0);
		
		assertEquals("Checked if the player is allowed to move up on a road.", true, pk.checkIfValidMove(1, 0));
	}
	
	@Test
	public final void checkIfValidMove_houseAndLeft() 
	{
		Avatar p = new Avatar();
		p.setCol(1);
		p.setRow(0);
		Tile[][] tile = new Tile[5][5];
		tile[0][0] = new House(false);
		
		PizzaKid pk = new PizzaKid();
		pk.map = new Map(p, 5, tile);
		pk.collectibles = new Collectibles(0, 0);
		
		assertEquals("Checked if the player is not allowed to move left on a house.", true, !pk.checkIfValidMove(2, 0));
	}
	
	@Test
	public final void checkIfValidMove_customerAndRight() 
	{
		Avatar p = new Avatar();
		p.setCol(0);
		p.setRow(0);
		Tile[][] tile = new Tile[5][5];
		tile[0][1] = new Customer();
		
		PizzaKid pk = new PizzaKid();
		pk.map = new Map(p, 5, tile);
		pk.collectibles = new Collectibles(0, 0);
		
		assertEquals("Checked if the player is not allowed to move right on a customer.", true, !pk.checkIfValidMove(4, 0));
	}
	
	@Test
	public final void checkIfValidMove_obstacleAndDown() 
	{
		Avatar p = new Avatar();
		p.setCol(0);
		p.setRow(0);
		Tile[][] tile = new Tile[5][5];
		tile[1][0] = new Obstacle();
		
		PizzaKid pk = new PizzaKid();
		pk.map = new Map(p, 5, tile);
		pk.collectibles = new Collectibles(0, 0);
		
		assertEquals("Checked if the player is not allowed to move down on an obstacle.", true, !pk.checkIfValidMove(3, 0));
	}
	
	@Test
	public final void checkMove_up() 
	{
		Avatar p = new Avatar();
		p.setCol(0);
		p.setRow(1);
		Tile[][] tile = new Tile[5][5];
		
		PizzaKid pk = new PizzaKid();
		pk.map = new Map(p, 5, tile);
		pk.collectibles = new Collectibles(0, 0);
		
		pk.move(1);
		
		assertEquals("Check if the player has moved up", true, (p.getRow() == 0 && p.getCol() == 0));
	}
	
	@Test
	public final void checkMove_up() 
	{
		Avatar p = new Avatar();
		p.setCol(0);
		p.setRow(1);
		Tile[][] tile = new Tile[5][5];
		
		PizzaKid pk = new PizzaKid();
		pk.map = new Map(p, 5, tile);
		pk.collectibles = new Collectibles(0, 0);
		
		pk.move(1);
		
		assertEquals("Check if the player has moved up", true, (p.getRow() == 0 && p.getCol() == 0));
	}
	
	
	// help 

}

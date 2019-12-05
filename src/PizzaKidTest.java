import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.*;

public class PizzaKidTextTest {

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

	// help 

}

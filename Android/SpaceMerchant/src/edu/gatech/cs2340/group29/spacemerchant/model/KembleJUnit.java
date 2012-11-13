package edu.gatech.cs2340.group29.spacemerchant.model;

import android.content.Context;
import junit.framework.TestCase;
import edu.gatech.cs2340.group29.spacemerchant.interfaces.Marketable;

public class KembleJUnit extends TestCase {
	
	/**
	 * This JUnit test asseses if the getBasePrice for item is properly working
	 */
	public void KembleJunit()
	{
	}
	
	public void testItem_getBasePrice()
	{	
		Item itm1 = new Item( 1, "widget", 1, 3 );
		
		int calculated_base_price = 100 + itm1.getType() + ( itm1.getType() * Game.difficulty );
		
		System.out.println( "Calculated base price of item: "+calculated_base_price );
		System.out.println( "Game method base price: "+calculated_base_price );
		
		assertTrue("Base price method works properly!", ( itm1.getBasePrice() == calculated_base_price ));
	}	
}

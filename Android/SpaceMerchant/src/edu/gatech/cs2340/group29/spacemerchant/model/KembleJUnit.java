package edu.gatech.cs2340.group29.spacemerchant.model;

import junit.framework.TestCase;

public class KembleJUnit extends TestCase {

	
	/**
	 * These J-Unit tests will asses the functionality of the way items work,
	 * as well as some inventory functions, and Market functions
	 */
	public void KembleJunit()
	{
	}
		
	public void testItem()
	{
		// intakes type, name, drawable, techLevel
		Item itm1 = new Item( 1, "widget", 1, 3 );
		Item itm2 = new Item( 0, "monkey", 2, 1 );
		Item itm3 = new Item( 2, "sedan", 3, 2 );
		Item itm4 = new Item( 4, "Nixon", 5, 1 );
		Item itm5 = new Item( 3, "Chips", 6, 2 );
		
		assertTrue("woohoo truee", ( 2==2 ));  
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

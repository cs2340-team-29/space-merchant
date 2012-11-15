package edu.gatech.cs2340.group29.spacemerchant.model;

import java.util.ArrayList;
import java.util.LinkedList;

import junit.framework.TestCase;

public class RobertJUnit extends TestCase {

	public void testHasItem() {
	
		
		Inventory inventory = new Inventory();
		Item item = new Item(1, "Fake item", 1, 1);
	
		inventory.add(item);
		
		assertTrue("success", inventory.hasItem(item));
	
	}

}

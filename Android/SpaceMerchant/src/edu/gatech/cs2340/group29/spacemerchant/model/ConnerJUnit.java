package edu.gatech.cs2340.group29.spacemerchant.model;

import junit.framework.TestCase;

public class ConnerJUnit extends TestCase {

	
	public void testInventorySizes() {
	
		Inventory inventory = new Inventory();
		Item item = new Item(1, "FAKE1", 1, 1);
		Item item2 = new Item(2, "FAKE2", 1, 1);
		Item item3 = new Item(3, "FAKE3", 1, 1);
		Item item4 = new Item(4, "FAKE4", 1, 1);

		Item[] itemArray = new Item[] {item,item2,item3,item4};
		inventory.addAll(itemArray);
		
		assertTrue("All items added.", inventory.size() == 4);
	
	}
}

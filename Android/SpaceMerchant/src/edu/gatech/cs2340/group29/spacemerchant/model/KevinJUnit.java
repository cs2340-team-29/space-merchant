package edu.gatech.cs2340.group29.spacemerchant.model;

import junit.framework.TestCase;

public class KevinJUnit extends TestCase {

	public void testRemove() {
		int capacity = 10;
		Inventory inventory = new Inventory(capacity);
		Inventory inventory2 = inventory;
		Item item = new Item(0, "Item1", 0, 0);
		Item ret;
		try {
			ret = inventory.remove(item);
			assertTrue("Check removing from an empty inventory",ret == null);
		}
		catch(Exception e) {
			fail("Did not check for removing from an empty inventory");
		}
		inventory.add(item);
		try {
			ret = inventory.remove(item);
			assertTrue("Check removing item from one-item inventory", ret == item && inventory.equals(inventory2));
		}
		catch(Exception e) {
			fail("Error when removing item from one-item inventory");
		}
		
		for (int i = 0; i < capacity - 1; i++) {
			inventory.add(item);
		}
		inventory2 = inventory;
		Item item2 = new Item(1, "Item2", 1, 1);
		inventory.add(item2);
		try {
			ret = inventory.remove(item2);
			assertTrue("Check removing item from full inventory", ret == item2 && inventory.equals(inventory2));
		}
		catch(Exception e) {
			fail("Error when removing item from full inventory");
		}
		
	}

}

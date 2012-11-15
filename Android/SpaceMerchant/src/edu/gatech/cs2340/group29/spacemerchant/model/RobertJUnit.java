package edu.gatech.cs2340.group29.spacemerchant.model;

import java.util.ArrayList;
import java.util.LinkedList;

import junit.framework.TestCase;

public class RobertJUnit extends TestCase {

	public void testGiveToA() {
	
		Player playerA = new Player();
		Player playerB = new Player();
		
		Inventory playerAInventory = new Inventory();
		Item item = new Item(1, "Fake item", 1, 1);
		playerAInventory.add(item);
		playerA.setInventory(playerAInventory);
		
		Market market = new Market(playerA, playerB);
		
		market.giveToB(item);
		
		Inventory playerBInventory = playerB.getInventory();
		LinkedList<Item>[] inventoryContents = playerBInventory.getContents();
		
		assertEquals("item successfully transferred", inventoryContents[1].get(0), item );
	
	}

}

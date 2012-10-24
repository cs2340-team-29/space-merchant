package edu.gatech.cs2340.group29.spacemerchant.interfaces;

import edu.gatech.cs2340.group29.spacemerchant.model.Inventory;
import edu.gatech.cs2340.group29.spacemerchant.model.Item;

public interface Marketable {

	public int getBasePrice(Item item);
	public Inventory getInventory();
	public void setInventory(Inventory newInventory);
	
}

/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.model;

public class Market {

	// The two inventories to trade between
	private Inventory a, b;

	public Market(Inventory a, Inventory b) {
		setInventoryA(a);
		setInventoryB(b);
	}

	public Inventory getInventoryA() {
		return a;
	}

	public void setInventoryA(Inventory a) {
		this.a = a;
	}

	public Inventory getInventoryB() {
		return b;
	}

	public void setInventoryB(Inventory b) {
		this.b = b;
	}

	/**
	 * Attempts to give an item from inventory A to inventory B.
	 * 
	 * @param item
	 * @return whether or not the trade was successful
	 */
	public boolean giveToB(Item item) {
		if (!a.hasItem(item)) {
			return false;
		}
		Item aItem = a.getItem(item);
		a.remove(aItem);
		b.add(aItem);
		return true;
	}

	/**
	 * Attempts to give an item from inventory B to inventory A.
	 * 
	 * @param item
	 * @return whether or not the trade was successful
	 */
	public boolean giveToA(Item item) {
		if (!b.hasItem(item)) {
			return false;
		}
		Item bItem = b.getItem(item);
		b.remove(bItem);
		a.add(bItem);
		return true;
	}

}

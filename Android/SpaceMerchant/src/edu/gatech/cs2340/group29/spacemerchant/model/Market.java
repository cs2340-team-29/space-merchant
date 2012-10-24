/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.model;

import edu.gatech.cs2340.group29.spacemerchant.interfaces.Marketable;

public class Market {

	// The two marketables to trade between
	private Marketable a, b;

	public Market(Marketable a, Marketable b) {
		setMarketableA(a);
		setMarketableB(b);
	}
	
	

	public Marketable getMarketableA() {
		return a;
	}



	public void setMarketableA(Marketable a) {
		this.a = a;
	}



	public Marketable getMarketableB() {
		return b;
	}



	public void setMarketableB(Marketable b) {
		this.b = b;
	}



	/**
	 * Attempts to give an item from inventory A to inventory B.
	 * 
	 * @param item
	 * @return whether or not the trade was successful
	 */
	public boolean giveToB(Item item) {
		if (!a.getInventory().hasItem(item)) {
			return false;
		}
		Item aItem = a.getInventory().getItem(item);
		a.getInventory().remove(aItem);
		b.getInventory().add(aItem);
		return true;
	}

	/**
	 * Attempts to give an item from inventory B to inventory A.
	 * 
	 * @param item
	 * @return whether or not the trade was successful
	 */
	public boolean giveToA(Item item) {
		if (!b.getInventory().hasItem(item)) {
			return false;
		}
		Item bItem = b.getInventory().getItem(item);
		b.getInventory().remove(bItem);
		a.getInventory().add(bItem);
		return true;
	}

}

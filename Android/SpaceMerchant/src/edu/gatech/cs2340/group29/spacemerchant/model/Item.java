package edu.gatech.cs2340.group29.spacemerchant.model;

public class Item implements Comparable<Item>{
	private int type;
	private int basePrice;
	
	public Item(int type, int basePrice) {
		this.type = type;
		this.basePrice = basePrice;
	}

	public int getType() {
		return type;
	}

	public int getBasePrice() {
		return basePrice;
	}

	public int compareTo(Item itm) {
		if(itm == this)
			return 0;
		return -1;
	}
}

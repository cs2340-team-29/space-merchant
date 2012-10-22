
package edu.gatech.cs2340.group29.spacemerchant.model;

public class Item implements Comparable<Item>
{
    private int    type;
    private int    basePrice;
    private String name;
    private int drawable;
    
    /**
     * creates an item
     * 
     * @param type
     * @param basePrice
     * @param name
     * @param drawable
     */
    public Item( int type, int basePrice, String name , int drawable)
    {
        this.type = type;
        this.basePrice = basePrice;
        this.name = name;
        this.drawable = drawable;
    }
    
    /**
     * 
     * creates an item without a drawable
     * 
     * @param type
     * @param basePrice
     * @param name
     */
    public Item( int type, int basePrice, String name) {
    	this.type = type;
        this.basePrice = basePrice;
        this.name = name;
    }
    
    /**
     * gets the type of item it is
     * 
     * @return the type
     */
    public int getType()
    {
        return type;
    }
    
    /**
     * 
     * gets the base price of the item
     * 
     * @return the base price
     */
    public int getBasePrice()
    {
        return basePrice;
    }
    
    /**
     * 
     * get the name of the item
     * 
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * 
     * gets the resource id for drawable
     * 
     * @return the drawable id
     */
    public int getDrawable() {
		return drawable;
	}

    
    // compares the item to another item
	public int compareTo( Item itm )
    {
        if ( itm.equals( this ) )
            return 0;
        return -1;
    }
}

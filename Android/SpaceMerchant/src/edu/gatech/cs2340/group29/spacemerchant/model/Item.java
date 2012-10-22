
package edu.gatech.cs2340.group29.spacemerchant.model;

public class Item implements Comparable<Item>
{
    private int    type; //Drawable
    private int    basePrice;
    private String name;
    
    public Item( int type, int basePrice, String name )
    {
        this.type = type;
        this.basePrice = basePrice;
        this.name = name;
    }
    
    public int getType()
    {
        return type; //Drawable
    }
    
    public int getBasePrice()
    {
        return basePrice;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int compareTo( Item itm )
    {
        if ( itm.equals( this ) )
            return 0;
        return -1;
    }
}

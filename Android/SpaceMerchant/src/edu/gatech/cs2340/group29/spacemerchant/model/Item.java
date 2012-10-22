
package edu.gatech.cs2340.group29.spacemerchant.model;

public class Item implements Comparable<Item>
{
	private final int type_generic = 0;
	private final int type_material = 1;
	private final int type_liquid = 2;
	private final int type_plant = 3;
	private final int type_animal = 4;
	private final int type_art = 5;
	private final int type_weapon = 6;
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

    public int getType_generic() {
		return type_generic;
	}

	public int getType_material() {
		return type_material;
	}

	public int getType_liquid() {
		return type_liquid;
	}

	public int getType_plant() {
		return type_plant;
	}

	public int getType_animal() {
		return type_animal;
	}

	public int getType_art() {
		return type_art;
	}

	public int getType_weapon() {
		return type_weapon;
	}

	// compares the item to another item
	public int compareTo( Item itm )
    {
        if ( itm.equals( this ) )
            return 0;
        return -1;
    }
}

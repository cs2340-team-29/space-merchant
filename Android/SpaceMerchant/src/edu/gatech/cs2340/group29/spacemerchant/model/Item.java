
package edu.gatech.cs2340.group29.spacemerchant.model;

public class Item implements Comparable<Item>
{
    private final int type_generic  = 0;
    private final int type_material = 1;
    private final int type_liquid   = 2;
    private final int type_animal   = 3;
    private final int type_art      = 4;
    private final int type_weapon   = 5;
    private int       type;
    private String    name;
    private int       drawable;
    private int		  techLevel;
    
    /**
     * creates an item
     * 
     * @param type
     * @param basePrice
     * @param name
     * @param drawable
     */
    public Item( int type, String name, int drawable, int techLevel )
    {
        this.type = type;
        this.name = name;
        this.drawable = drawable;
        this.setTechLevel(techLevel);
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
     * gets the base price of the item
     * 
     * @return the base price
     */
    public int getBasePrice()
    {
        return 100 + type + type * Game.difficulty;
    }
    
    /**
     * get the name of the item
     * 
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * gets the resource id for drawable
     * 
     * @return the drawable id
     */
    public int getDrawable()
    {
        return drawable;
    }
    
    public int getType_generic()
    {
        return type_generic;
    }
    
    public int getType_material()
    {
        return type_material;
    }
    
    public int getType_liquid()
    {
        return type_liquid;
    }
    
    public int getType_animal()
    {
        return type_animal;
    }
    
    public int getType_art()
    {
        return type_art;
    }
    
    public int getType_weapon()
    {
        return type_weapon;
    }
    
    // compares the item to another item
    public int compareTo( Item itm )
    {
        if ( itm.equals( this ) )
            return 0;
        return -1;
    }

	public int getTechLevel() {
		return techLevel;
	}

	public void setTechLevel(int techLevel) {
		this.techLevel = techLevel;
	}
}

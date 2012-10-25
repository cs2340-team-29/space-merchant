/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.model;

/**
 * The Class Item.
 */
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
    private int       techLevel;
    
    /**
     * Instantiates a new item.
     * 
     * @param type
     *            the int
     * @param name
     *            the String
     * @param drawable
     *            the int
     * @param techLevel
     *            the int
     */
    public Item( int type, String name, int drawable, int techLevel )
    {
        this.type = type;
        this.name = name;
        this.drawable = drawable;
        this.setTechLevel( techLevel );
    }
    
    /**
     * Gets the type.
     * 
     * @return the type
     */
    public int getType()
    {
        return type;
    }
    
    /**
     * Gets the base price.
     * 
     * @return the base price
     */
    public int getBasePrice()
    {
        return 100 + type + ( type * Game.difficulty );
    }
    
    /**
     * Gets the name.
     * 
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Gets the drawable.
     * 
     * @return the drawable
     */
    public int getDrawable()
    {
        return drawable;
    }
    
    /**
     * Gets the type_generic.
     * 
     * @return the type_generic
     */
    public int getType_generic()
    {
        return type_generic;
    }
    
    /**
     * Gets the type_material.
     * 
     * @return the type_material
     */
    public int getType_material()
    {
        return type_material;
    }
    
    /**
     * Gets the type_liquid.
     * 
     * @return the type_liquid
     */
    public int getType_liquid()
    {
        return type_liquid;
    }
    
    /**
     * Gets the type_animal.
     * 
     * @return the type_animal
     */
    public int getType_animal()
    {
        return type_animal;
    }
    
    /**
     * Gets the type_art.
     * 
     * @return the type_art
     */
    public int getType_art()
    {
        return type_art;
    }
    
    /**
     * Gets the type_weapon.
     * 
     * @return the type_weapon
     */
    public int getType_weapon()
    {
        return type_weapon;
    }
    
    // compares the item to another item
    /**
     * Override:
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo( Item itm )
    {
        if ( itm.equals( this ) )
        {
            return 0;
        }
        return -1;
    }
    
    /**
     * Gets the tech level.
     * 
     * @return the tech level
     */
    public int getTechLevel()
    {
        return techLevel;
    }
    
    /**
     * Sets the tech level.
     * 
     * @param techLevel
     *            the new tech level
     */
    public void setTechLevel( int techLevel )
    {
        this.techLevel = techLevel;
    }
}

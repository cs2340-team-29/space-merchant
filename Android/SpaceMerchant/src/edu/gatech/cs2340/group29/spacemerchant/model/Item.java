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
    
    /** The Constant type_generic. */
    private static final int TYPE_GENERIC  = 0;
    
    /** The Constant type_material. */
    private static final int TYPE_MATERIAL = 1;
    
    /** The Constant type_liquid. */
    private static final int TYPE_LIQUID   = 2;
    
    /** The Constant type_animal. */
    private static final int TYPE_ANIMAL   = 3;
    
    /** The Constant type_art. */
    private static final int TYPE_ART      = 4;
    
    /** The Constant type_weapon. */
    private static final int TYPE_WEAPON   = 5;
    
    /** The type. */
    private final int        type;
    
    /** The name. */
    private final String     name;
    
    /** The drawable. */
    private final int        drawable;
    
    /** The tech level. */
    private int              techLevel;
    
    /**
     * Instantiates a new item.
     * 
     * @param type
     *        the int
     * @param name
     *        the String
     * @param drawable
     *        the int
     * @param techLevel
     *        the int
     */
    public Item( final int type, final String name, final int drawable, final int techLevel )
    {
        this.type = type;
        this.name = name;
        this.drawable = drawable;
        this.techLevel = techLevel;
    }
    
    /**
     * Gets the type.
     * 
     * @return the type
     */
    public int getType()
    {
        return this.type;
    }
    
    /**
     * Gets the base price.
     * 
     * @return the base price
     */
    public int getBasePrice()
    {
        return 100 + this.type + ( this.type * Game.DIFFICULTY );
    }
    
    /**
     * Gets the name.
     * 
     * @return the name
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Gets the drawable.
     * 
     * @return the drawable
     */
    public int getDrawable()
    {
        return this.drawable;
    }
    
    /**
     * Gets the type_generic.
     * 
     * @return the type_generic
     */
    public int getType_generic()
    {
        return Item.TYPE_GENERIC;
    }
    
    /**
     * Gets the type_material.
     * 
     * @return the type_material
     */
    public int getType_material()
    {
        return Item.TYPE_MATERIAL;
    }
    
    /**
     * Gets the type_liquid.
     * 
     * @return the type_liquid
     */
    public int getType_liquid()
    {
        return Item.TYPE_LIQUID;
    }
    
    /**
     * Gets the type_animal.
     * 
     * @return the type_animal
     */
    public int getType_animal()
    {
        return Item.TYPE_ANIMAL;
    }
    
    /**
     * Gets the type_art.
     * 
     * @return the type_art
     */
    public int getType_art()
    {
        return Item.TYPE_ART;
    }
    
    /**
     * Gets the type_weapon.
     * 
     * @return the type_weapon
     */
    public int getType_weapon()
    {
        return Item.TYPE_WEAPON;
    }
    
    // compares the item to another item
    /**
     * Override:
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo( final Item itm )
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
        return this.techLevel;
    }
    
    /**
     * Sets the tech level.
     * 
     * @param techLevel
     *        the new tech level
     */
    public void setTechLevel( final int techLevel )
    {
        this.techLevel = techLevel;
    }
}

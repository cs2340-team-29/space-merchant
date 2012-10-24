/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.model;

import java.util.Random;

import android.content.Context;
import android.content.res.Resources;
import edu.gatech.cs2340.group29.spacemerchant.R;
import edu.gatech.cs2340.group29.spacemerchant.interfaces.Marketable;

/**
 * The Class Planet.
 */
public class Planet implements Marketable
{
    // Tech Levels
    public static final int PRE_AGRICULTURAL = 0;
    public static final int AGRICULTURAL     = 1;
    public static final int MEDIEVAL         = 2;
    public static final int RENAISSANCE      = 3;
    public static final int EARLY_INDUSTRIAL = 4;
    public static final int INDUSTRIAL       = 5;
    public static final int POST_INDUSTRIAL  = 6;
    public static final int HI_TECH          = 7;
    
    // Resource Types
    public static final int NOTHING_SPECIAL  = 0;
    public static final int MINERAL_RICH     = -1;
    public static final int MINERAL_POOR     = 1;
    public static final int WATERY           = -2;
    public static final int DESERT           = 2;
    public static final int ARTISTIC         = -3;
    public static final int PHILISTINIC      = 3;
    public static final int ANIMALS          = -4;
    public static final int NO_ANIMALS       = 4;
    public static final int PEACEFUL         = -5;
    public static final int WARFARE          = 5;
    
    private int             techLevel;
    private int             resourceType;
    private String          name;
    private int             x;
    private int             y;
    private Inventory       inventory;
    private Context         context;
    
    /**
     * Instantiates a new planet.
     * 
     * @param name
     *            the String
     * @param x
     *            the int
     * @param y
     *            the int
     */
    public Planet( String name, int x, int y, Context context )
    {
        Random r = new Random();
        techLevel = r.nextInt( 8 );
        resourceType = r.nextInt( 10 );
        this.name = name;
        this.x = x;
        this.y = y;
        this.context = context;
    }
    
    /**
     * Gets the x.
     * 
     * @return the x
     */
    public int getX()
    {
        return x;
    }
    
    /**
     * Sets the x.
     * 
     * @param x
     *            the new x
     */
    public void setX( int x )
    {
        this.x = x;
    }
    
    /**
     * Gets the y.
     * 
     * @return the y
     */
    public int getY()
    {
        return y;
    }
    
    /**
     * Sets the y.
     * 
     * @param y
     *            the new y
     */
    public void setY( int y )
    {
        this.y = y;
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
    
    /**
     * Gets the resource type.
     * 
     * @return the resource type
     */
    public int getResourceType()
    {
        return resourceType;
    }
    
    /**
     * Sets the resource type.
     * 
     * @param resourceType
     *            the new resource type
     */
    public void setResourceType( int resourceType )
    {
        this.resourceType = resourceType;
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
     * Sets the name.
     * 
     * @param name
     *            the new name
     */
    public void setName( String name )
    {
        this.name = name;
    }
    
    /**
     * Override:
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return " Name: " + name + " TechLevel: " + techLevel + " ResourceType: " + resourceType;
    }
    
    public Inventory getInventory()
    {
        if ( inventory == null )
            generateInventory();
        return inventory;
    }
    
    private void generateInventory()
    {
        inventory = new Inventory();
        Resources res = context.getResources();
        int[] items = res.getIntArray( R.array.Items );
        String[] names = res.getStringArray( R.array.ItemNames );
        int[] drawables = res.getIntArray( R.array.ItemDrawables );
        
        for ( int i = 0; i < items.length; i++ )
        {
            int tempItemType = items[i] % 10;
            int tempTechLevel = items[i] / 10 % 10;
            if ( tempTechLevel <= this.techLevel )
            {
                inventory.add( new Item( tempItemType, names[i], drawables[tempItemType] ) );
            }
        }
    }
    
    // TODO Implement this!
    public int getBasePrice( Item item )
    {
        return ( int ) ( item.getBasePrice() * ( 1 - ( techLevel / 10.0 ) ) );
    }
    
    public void setInventory( Inventory newInventory )
    {
        this.inventory = newInventory;
    }
    
}

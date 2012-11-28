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
    /** The Constant PRE_AGRICULTURAL. */
    public static final int   PRE_AGRICULTURAL = 0;
    
    /** The Constant AGRICULTURAL. */
    public static final int   AGRICULTURAL     = 1;
    
    /** The Constant MEDIEVAL. */
    public static final int   MEDIEVAL         = 2;
    
    /** The Constant RENAISSANCE. */
    public static final int   RENAISSANCE      = 3;
    
    /** The Constant EARLY_INDUSTRIAL. */
    public static final int   EARLY_INDUSTRIAL = 4;
    
    /** The Constant INDUSTRIAL. */
    public static final int   INDUSTRIAL       = 5;
    
    /** The Constant POST_INDUSTRIAL. */
    public static final int   POST_INDUSTRIAL  = 6;
    
    /** The Constant HI_TECH. */
    public static final int   HI_TECH          = 7;
    
    // Resource Types
    /** The Constant PEACEFUL. */
    public static final int   PEACEFUL         = -5;
    
    /** The Constant ANIMALS. */
    public static final int   ANIMALS          = -4;
    
    /** The Constant ARTISTIC. */
    public static final int   ARTISTIC         = -3;
    
    /** The Constant WATERY. */
    public static final int   WATERY           = -2;
    
    /** The Constant MINERAL_RICH. */
    public static final int   MINERAL_RICH     = -1;
    
    /** The Constant NOTHING_SPECIAL. */
    public static final int   NOTHING_SPECIAL  = 0;
    
    /** The Constant MINERAL_POOR. */
    public static final int   MINERAL_POOR     = 1;
    
    /** The Constant DESERT. */
    public static final int   DESERT           = 2;
    
    /** The Constant PHILISTINIC. */
    public static final int   PHILISTINIC      = 3;
    
    /** The Constant LIFELESS. */
    public static final int   LIFELESS         = 4;
    
    /** The Constant WARFARE. */
    public static final int   WARFARE          = 5;
    
    /** The Constant BASES. */
    public static final int[] BASES            = new int[]{ R.drawable.ic_planet_base_a,
            R.drawable.ic_planet_base_b, R.drawable.ic_planet_base_c, R.drawable.ic_planet_base_d,
            R.drawable.ic_planet_base_e       };
    
    /** The Constant LANDS. */
    public static final int[] LANDS            = new int[]{ R.drawable.ic_land_a, R.drawable.ic_land_b,
            R.drawable.ic_land_c, R.drawable.ic_land_d, R.drawable.ic_land_e };
    
    /** The Constant CLOUDS. */
    public static final int[] CLOUDS           = new int[]{ R.drawable.ic_clouds_a, R.drawable.ic_clouds_b,
            R.drawable.ic_clouds_c, R.drawable.ic_clouds_d, R.drawable.ic_clouds_e };
    
    /** The Constant ITEM_DRAWABLES. */
    public static final int[] ITEM_DRAWABLES   = new int[]{ R.drawable.ic_generic_object,
            R.drawable.ic_mineral, R.drawable.ic_liquid, R.drawable.ic_animal, R.drawable.ic_art,
            R.drawable.ic_gun                 };
    
    /** The tech level. */
    private int               techLevel;
    
    /** The resource type. */
    private int               resourceType;
    
    /** The name. */
    private String            name;
    
    /** The x. */
    private int               x;
    
    /** The y. */
    private int               y;
    
    /** The inventory. */
    private Inventory         inventory;
    
    /** The context. */
    private final Context     context;
    
    /** The money. */
    private int               money;
    
    /** The base. */
    private int               base;                                                  // drawable
                                                                                      
    /** The land. */
    private int               land;                                                  // drawable
                                                                                      
    /** The cloud. */
    private int               cloud;                                                 // drawable
                                                                                      
    /**
     * Instantiates a new planet.
     * 
     * @param name
     *        the String
     * @param x
     *        the int
     * @param y
     *        the int
     * @param context
     *        the Context
     */
    public Planet( final String name, final int x, final int y, final Context context )
    {
        final Random r = new Random();
        this.techLevel = r.nextInt( 8 );
        this.resourceType = r.nextInt( 11 ) - 5;
        this.name = name;
        this.x = x;
        this.y = y;
        this.context = context;
        this.money = 1000 * ( 1 + this.techLevel );
        this.base = Planet.BASES[r.nextInt( 5 )];
        this.land = Planet.LANDS[r.nextInt( 5 )];
        this.cloud = Planet.CLOUDS[r.nextInt( 5 )];
    }
    
    /**
     * Gets the x.
     * 
     * @return the x
     */
    public int getX()
    {
        return this.x;
    }
    
    /**
     * Sets the x.
     * 
     * @param x
     *        the new x
     */
    public void setX( final int x )
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
        return this.y;
    }
    
    /**
     * Sets the y.
     * 
     * @param y
     *        the new y
     */
    public void setY( final int y )
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
    
    /**
     * Gets the resource type.
     * 
     * @return the resource type
     */
    public int getResourceType()
    {
        return this.resourceType;
    }
    
    /**
     * Sets the resource type.
     * 
     * @param resourceType
     *        the new resource type
     */
    public void setResourceType( final int resourceType )
    {
        this.resourceType = resourceType;
    }
    
    /**
     * Override:
     * 
     * @see edu.gatech.cs2340.group29.spacemerchant.interfaces.Marketable#getName()
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Sets the name.
     * 
     * @param name
     *        the new name
     */
    public void setName( final String name )
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
        return " Name: " + this.name + " TechLevel: " + this.techLevel + " ResourceType: "
                + this.resourceType;
    }
    
    /**
     * Override:
     * 
     * @see edu.gatech.cs2340.group29.spacemerchant.interfaces.Marketable#getInventory()
     */
    public Inventory getInventory()
    {
        if ( this.inventory == null )
        {
            this.generateInventory();
        }
        return this.inventory;
    }
    
    /**
     * Generate inventory.
     */
    private void generateInventory()
    {
        this.inventory = new Inventory();
        final Random r = new Random();
        final Resources res = this.context.getResources();
        final int[] items = res.getIntArray( R.array.Items );
        final String[] names = res.getStringArray( R.array.ItemNames );
        
        for ( int i = 0; i < items.length; i++ )
        {
            final int tempTechLevel = items[i] % 10;
            final int tempItemType = ( items[i] / 10 ) % 10;
            if ( tempTechLevel <= this.techLevel )
            {
                for ( int j = 0; j < r.nextInt( this.techLevel + 1 ); j++ )
                {
                    this.inventory.add( new Item( tempItemType, names[i],
                            Planet.ITEM_DRAWABLES[tempItemType], tempTechLevel ) );
                }
            }
        }
    }
    
    /**
     * Override:
     * 
     * @see edu.gatech.cs2340.group29.spacemerchant.interfaces.Marketable#getBasePrice(edu.gatech.cs2340.group29.spacemerchant.model.Item)
     */
    public int getBasePrice( final Item item )
    {
        final Random rand = new Random();
        double basePrice = item.getBasePrice();
        
        if ( item.getType() == this.resourceType )
        {
            basePrice *= ( 1.0 / ( ( 1 + rand.nextInt( 4 ) ) << 1 ) );
        }
        if ( item.getType() == -this.resourceType )
        {
            basePrice /= ( 1.0 / ( ( 1 + rand.nextInt( 4 ) ) << 1 ) );
        }
        
        basePrice -= ( this.techLevel * this.techLevel * 15 );
        
        if ( basePrice <= 0 )
        {
            basePrice = item.getBasePrice();
        }
        
        return ( int ) basePrice;
    }
    
    /**
     * Override:
     * 
     * @see edu.gatech.cs2340.group29.spacemerchant.interfaces.Marketable#setInventory(edu.gatech.cs2340.group29.spacemerchant.model.Inventory)
     */
    public void setInventory( final Inventory newInventory )
    {
        this.inventory = newInventory;
    }
    
    /**
     * Override:
     * 
     * @see edu.gatech.cs2340.group29.spacemerchant.interfaces.Marketable#getMoney()
     */
    public int getMoney()
    {
        return this.money;
    }
    
    /**
     * Override:
     * 
     * @see edu.gatech.cs2340.group29.spacemerchant.interfaces.Marketable#setMoney(int)
     */
    public void setMoney( final int money )
    {
        this.money = money;
    }
    
    /**
     * Gets the base.
     * 
     * @return the base
     */
    public int getBase()
    {
        return this.base;
    }
    
    /**
     * Sets the base.
     * 
     * @param base
     *        the new base
     */
    public void setBase( final int base )
    {
        this.base = base;
    }
    
    /**
     * Gets the land.
     * 
     * @return the land
     */
    public int getLand()
    {
        return this.land;
    }
    
    /**
     * Sets the land.
     * 
     * @param land
     *        the new land
     */
    public void setLand( final int land )
    {
        this.land = land;
    }
    
    /**
     * Gets the cloud.
     * 
     * @return the cloud
     */
    public int getCloud()
    {
        return this.cloud;
    }
    
    /**
     * Sets the cloud.
     * 
     * @param cloud
     *        the new cloud
     */
    public void setCloud( final int cloud )
    {
        this.cloud = cloud;
    }
}

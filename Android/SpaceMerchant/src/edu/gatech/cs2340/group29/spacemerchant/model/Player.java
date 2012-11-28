/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.model;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import edu.gatech.cs2340.group29.spacemerchant.interfaces.Marketable;
import edu.gatech.cs2340.group29.spacemerchant.model.StatGroup.Stat;

/**
 * The Class Player.
 */
public class Player implements Parcelable, Marketable
{
    
    /** The name. */
    private String    name;
    
    /** The ship. */
    private Ship      ship;
    
    /** The stats. */
    private StatGroup stats;
    
    /** The money. */
    private int       money;
    
    /** The inventory. */
    private Inventory inventory;
    
    /** The head. */
    private int       head;
    
    /** The body. */
    private int       body;
    
    /** The legs. */
    private int       legs;
    
    /** The feet. */
    private int       feet;
    
    /**
     * Instantiates a new player.
     */
    public Player()
    {
        this.name = "Default";
        this.ship = new Ship();
        this.stats = new StatGroup();
        this.money = 0;
        
        this.head = android.R.drawable.ic_menu_camera;
        this.body = android.R.drawable.ic_menu_camera;
        this.legs = android.R.drawable.ic_menu_camera;
        this.feet = android.R.drawable.ic_menu_camera;
    }
    
    /**
     * Instantiates a new player.
     * 
     * @param in
     *        the Parcel
     */
    private Player( final Parcel in )
    {
        this.name = in.readString();
        final int[] statArray = new int[StatGroup.Stat.values().length];
        in.readIntArray( statArray );
        this.stats = new StatGroup( statArray );
        this.money = in.readInt();
        
        this.head = in.readInt();
        this.body = in.readInt();
        this.legs = in.readInt();
        this.feet = in.readInt();
        
        this.ship = new Ship();
    }
    
    /**
     * Override:
     * 
     * @see android.os.Parcelable#describeContents()
     */
    public int describeContents()
    {
        return 0;
    }
    
    /**
     * Override:
     * 
     * @see android.os.Parcelable#writeToParcel(android.os.Parcel, int)
     */
    public void writeToParcel( final Parcel dest, final int flags )
    {
        dest.writeString( this.name );
        dest.writeIntArray( this.stats.toIntArray() );
        dest.writeInt( this.money );
        
        dest.writeInt( this.head );
        dest.writeInt( this.body );
        dest.writeInt( this.legs );
        dest.writeInt( this.feet );
    }
    
    /** The Constant CREATOR. */
    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
                                                               public Player createFromParcel( final Parcel in )
                                                               {
                                                                   return new Player( in );
                                                               }
                                                               
                                                               public Player[] newArray( final int size )
                                                               {
                                                                   return new Player[size];
                                                               }
                                                           };
    
    /**
     * Gets the ship.
     * 
     * @return the ship
     */
    public Ship getShip()
    {
        return this.ship;
    }
    
    /**
     * Sets the ship.
     * 
     * @param ship
     *        the new ship
     */
    public void setShip( final Ship ship )
    {
        this.ship = ship;
    }
    
    /**
     * Gets the stats.
     * 
     * @return the stats
     */
    public StatGroup getStats()
    {
        return this.stats;
    }
    
    /**
     * Sets the stats.
     * 
     * @param stats
     *        the new stats
     */
    public void setStats( final int[] stats )
    {
        this.stats = new StatGroup( stats );
    }
    
    /**
     * Gets the head.
     * 
     * @return the head
     */
    public int getHead()
    {
        return this.head;
    }
    
    /**
     * Sets the head.
     * 
     * @param head
     *        the new head
     */
    public void setHead( final int head )
    {
        this.head = head;
    }
    
    /**
     * Gets the body.
     * 
     * @return the body
     */
    public int getBody()
    {
        return this.body;
    }
    
    /**
     * Sets the body.
     * 
     * @param body
     *        the new body
     */
    public void setBody( final int body )
    {
        this.body = body;
    }
    
    /**
     * Gets the legs.
     * 
     * @return the legs
     */
    public int getLegs()
    {
        return this.legs;
    }
    
    /**
     * Sets the legs.
     * 
     * @param legs
     *        the new legs
     */
    public void setLegs( final int legs )
    {
        this.legs = legs;
    }
    
    /**
     * Gets the feet.
     * 
     * @return the feet
     */
    public int getFeet()
    {
        return this.feet;
    }
    
    /**
     * Sets the feet.
     * 
     * @param feet
     *        the new feet
     */
    public void setFeet( final int feet )
    {
        this.feet = feet;
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
     * @see edu.gatech.cs2340.group29.spacemerchant.interfaces.Marketable#getInventory()
     */
    public Inventory getInventory()
    {
        return this.inventory;
    }
    
    /**
     * Override:
     * 
     * @see edu.gatech.cs2340.group29.spacemerchant.interfaces.Marketable#setInventory(edu.gatech.cs2340.group29.spacemerchant.model.Inventory)
     */
    public void setInventory( final Inventory inventory )
    {
        this.inventory = inventory;
    }
    
    /**
     * Fill inventory.
     * 
     * @param itm
     *        the List<Item>
     */
    public void fillInventory( final List<Item> itm )
    {
        for ( final Item element : itm )
        {
            this.inventory.add( element );
        }
    }
    
    /**
     * Override:
     * 
     * @see edu.gatech.cs2340.group29.spacemerchant.interfaces.Marketable#getBasePrice(edu.gatech.cs2340.group29.spacemerchant.model.Item)
     */
    public int getBasePrice( final Item item )
    {
        return ( int ) ( item.getBasePrice() + ( item.getBasePrice() * ( this.stats.get( Stat.TRADER ) / 100.0 ) ) );
    }
}

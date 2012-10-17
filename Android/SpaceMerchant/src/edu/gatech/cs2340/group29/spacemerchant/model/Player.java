/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The Class Player.
 */
public class Player implements Parcelable
{
    private String name;
    private Ship   ship;
    private int[]  stats;
    private int    money;
    
    private int    head;
    private int    body;
    private int    legs;
    private int    feet;
    
    /**
     * Instantiates a new player.
     */
    public Player()
    {
        setName( "Default" );
        ship = new Ship();
        stats = new int[4];
        setMoney( 0 );
        
        head = android.R.drawable.ic_menu_camera;
        body = android.R.drawable.ic_menu_camera;
        legs = android.R.drawable.ic_menu_camera;
        feet = android.R.drawable.ic_menu_camera;
    }
    
    private Player( Parcel in )
    {
        name = in.readString();
        stats = new int[4];
        in.readIntArray( stats );
        money = in.readInt();
        
        head = in.readInt();
        body = in.readInt();
        legs = in.readInt();
        feet = in.readInt();
        
        ship = new Ship();
    }
    
    /** 
     *
     * Override:
     * @see android.os.Parcelable#describeContents()
     */
    public int describeContents()
    {
        return 0;
    }
    
    /** 
     *
     * Override:
     * @see android.os.Parcelable#writeToParcel(android.os.Parcel, int)
     */
    public void writeToParcel( Parcel dest, int flags )
    {
        dest.writeString( name );
        dest.writeIntArray( stats );
        dest.writeInt( money );
        
        dest.writeInt( head );
        dest.writeInt( body );
        dest.writeInt( legs );
        dest.writeInt( feet );
    }
    
    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
                                                               public Player createFromParcel( Parcel in )
                                                               {
                                                                   return new Player( in );
                                                               }
                                                               
                                                               public Player[] newArray( int size )
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
        return ship;
    }
    
    /**
     * Sets the ship.
     *
     * @param ship the new ship
     */
    public void setShip( Ship ship )
    {
        this.ship = ship;
    }
    
    /**
     * Gets the stats.
     *
     * @return the stats
     */
    public int[] getStats()
    {
        return stats;
    }
    
    /**
     * Sets the stats.
     *
     * @param stats the new stats
     */
    public void setStats( int[] stats )
    {
        this.stats = stats;
    }
    
    /**
     * Gets the head.
     *
     * @return the head
     */
    public int getHead()
    {
        return head;
    }
    
    /**
     * Sets the head.
     *
     * @param head the new head
     */
    public void setHead( int head )
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
        return body;
    }
    
    /**
     * Sets the body.
     *
     * @param body the new body
     */
    public void setBody( int body )
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
        return legs;
    }
    
    /**
     * Sets the legs.
     *
     * @param legs the new legs
     */
    public void setLegs( int legs )
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
        return feet;
    }
    
    /**
     * Sets the feet.
     *
     * @param feet the new feet
     */
    public void setFeet( int feet )
    {
        this.feet = feet;
    }
    
    /**
     * Gets the money.
     *
     * @return the money
     */
    public int getMoney()
    {
        return money;
    }
    
    /**
     * Sets the money.
     *
     * @param money the new money
     */
    public void setMoney( int money )
    {
        this.money = money;
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
     * @param name the new name
     */
    public void setName( String name )
    {
        this.name = name;
    }
    
}

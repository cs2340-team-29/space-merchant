
package edu.gatech.cs2340.group29.spacemerchant.model;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable
{
    private String name;
    private Ship  ship;
    private int[] stats;
    private int   money;
    
    private Drawable   hat;
    private Drawable   head;
    private Drawable   body;
    private Drawable   legs;
    private Drawable   feet;
                         
    public Player()
    {
        setName( "Default" );
        ship = new Ship();
        stats = new int[4];
        setMoney( 0 );
        
        hat  = null;
        head = null;
        body = null;
        legs = null;
        feet = null;
    }
    
    public Ship getShip()
    {
        return ship;
    }
    
    public void setShip( Ship ship )
    {
        this.ship = ship;
    }
    
    public int[] getStats()
    {
        return stats;
    }
    
    public void setStats( int[] stats )
    {
        this.stats = stats;
    }
    
    public Drawable getHat()
    {
        return hat;
    }
    
    public void setHat( Drawable hat )
    {
        this.hat = hat;
    }
    
    public Drawable getHead()
    {
        return head;
    }
    
    public void setHead( Drawable head )
    {
        this.head = head;
    }
    
    public Drawable getBody()
    {
        return body;
    }
    
    public void setBody( Drawable body )
    {
        this.body = body;
    }
    
    public Drawable getLegs()
    {
        return legs;
    }
    
    public void setLegs( Drawable legs )
    {
        this.legs = legs;
    }
    
    public Drawable getFeet()
    {
        return feet;
    }
    
    public void setFeet( Drawable feet )
    {
        this.feet = feet;
    }
    
    public int getMoney()
    {
        return money;
    }
    
    public void setMoney( int money )
    {
        this.money = money;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public int describeContents()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public void writeToParcel( Parcel dest, int flags )
    {
        
    }
}

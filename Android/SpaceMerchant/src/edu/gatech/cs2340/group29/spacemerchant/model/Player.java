
package edu.gatech.cs2340.group29.spacemerchant.model;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable
{
    private String name;
    private Ship   ship;
    private int[]  stats;
    private int    money;
    private long   playerID;
    
    private int   hat;
    private int   head;
    private int   body;
    private int   legs;
    private int   feet;

    
    public Player()
    {
        setName( "Default" );
        ship = new Ship();
        stats = new int[4];
        setMoney( 0 );
        
        hat = android.R.drawable.ic_menu_camera;
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
        
        hat = in.readInt();
        head = in.readInt();
        body = in.readInt();
        legs = in.readInt();
        feet = in.readInt();
        
        ship = new Ship();
    }
    
    public int describeContents()
    {
        return 0;
    }
    
    public void writeToParcel( Parcel dest, int flags )
    {
        dest.writeString( name );
        dest.writeIntArray( stats );
        dest.writeInt( money );
        
        dest.writeInt( hat );
        dest.writeInt( head );
        dest.writeInt( body );
        dest.writeInt( legs );
        dest.writeInt( feet );
    }
    
    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>()
            {
                public Player createFromParcel( Parcel in )
                {
                    return new Player( in );
                }

                public Player[] newArray( int size )
                {
                    return new Player[size];
                }
            };
    
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
    
    public int getHat()
    {
        return hat;
    }
    
    public void setHat( int hat )
    {
        this.hat = hat;
    }
    
    public int getHead()
    {
        return head;
    }
    
    public void setHead( int head )
    {
        this.head = head;
    }
    
    public int getBody()
    {
        return body;
    }
    
    public void setBody( int body )
    {
        this.body = body;
    }
    
    public int getLegs()
    {
        return legs;
    }
    
    public void setLegs( int legs )
    {
        this.legs = legs;
    }
    
    public int getFeet()
    {
        return feet;
    }
    
    public void setFeet( int feet )
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
    
    public long getID()
    {
        return playerID;
    }

    public void setID( long playerID )
    {
        this.playerID = playerID;
    }


}

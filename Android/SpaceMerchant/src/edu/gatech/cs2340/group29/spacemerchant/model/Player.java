
package edu.gatech.cs2340.group29.spacemerchant.model;


public class Player
{
    private String name;
    private Ship  ship;
    private int[] stats;
    private int   money;
    
    private int   hat;  // Drawable
    private int   head; // Drawable
    private int   body; // Drawable
    private int   legs; // Drawable
    private int   feet; // Drawable
                         
    public Player()
    {
        setName( "Default" );
        ship = new Ship();
        stats = new int[4];
        setMoney( 0 );
        
        hat = android.R.drawable.ic_dialog_alert;
        head = android.R.drawable.ic_dialog_alert;
        body = android.R.drawable.ic_dialog_alert;
        legs = android.R.drawable.ic_dialog_alert;
        feet = android.R.drawable.ic_dialog_alert;
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
}

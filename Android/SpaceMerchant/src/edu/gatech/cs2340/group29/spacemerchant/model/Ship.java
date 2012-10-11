
package edu.gatech.cs2340.group29.spacemerchant.model;

public class Ship
{
    private long shipID;
    private int[] stats;
    
    private int   fuselage;
    private int   cabin;
    private int   boosters;
    
    public Ship()
    {
        stats = new int[4];
        
        fuselage = android.R.drawable.ic_menu_camera;
        cabin = android.R.drawable.ic_menu_camera;
        boosters = android.R.drawable.ic_menu_camera;
    }
    
    public int[] getStats()
    {
        return stats;
    }
    
    public void setStats( int[] stats )
    {
        this.stats = stats;
    }
    
    public long getID()
    {
        return shipID;
    }

    public void setID( long shipID )
    {
        this.shipID = shipID;
    }

    public long getShipID()
    {
        return shipID;
    }

    public void setShipID( long shipID )
    {
        this.shipID = shipID;
    }

    public int getFuselage()
    {
        return fuselage;
    }

    public void setFuselage( int fuselage )
    {
        this.fuselage = fuselage;
    }

    public int getCabin()
    {
        return cabin;
    }

    public void setCabin( int cabin )
    {
        this.cabin = cabin;
    }

    public int getBoosters()
    {
        return boosters;
    }

    public void setBoosters( int boosters )
    {
        this.boosters = boosters;
    } 
}

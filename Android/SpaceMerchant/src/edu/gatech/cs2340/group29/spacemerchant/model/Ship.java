/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.model;

/**
 * The Class Ship.
 */
public class Ship
{
    private long  shipID;
    private int[] stats;
    
    private int   fuselage;
    private int   cabin;
    private int   boosters;
    
    /**
     * Instantiates a new ship.
     */
    public Ship()
    {
        stats = new int[4];
        
        fuselage = android.R.drawable.ic_menu_camera;
        cabin = android.R.drawable.ic_menu_camera;
        boosters = android.R.drawable.ic_menu_camera;
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
     * @param stats
     *            the new stats
     */
    public void setStats( int[] stats )
    {
        this.stats = stats;
    }
    
    /**
     * Gets the id.
     * 
     * @return the id
     */
    public long getID()
    {
        return shipID;
    }
    
    /**
     * Sets the id.
     * 
     * @param shipID
     *            the new id
     */
    public void setID( long shipID )
    {
        this.shipID = shipID;
    }
    
    /**
     * Gets the ship id.
     * 
     * @return the ship id
     */
    public long getShipID()
    {
        return shipID;
    }
    
    /**
     * Sets the ship id.
     * 
     * @param shipID
     *            the new ship id
     */
    public void setShipID( long shipID )
    {
        this.shipID = shipID;
    }
    
    /**
     * Gets the fuselage.
     * 
     * @return the fuselage
     */
    public int getFuselage()
    {
        return fuselage;
    }
    
    /**
     * Sets the fuselage.
     * 
     * @param fuselage
     *            the new fuselage
     */
    public void setFuselage( int fuselage )
    {
        this.fuselage = fuselage;
    }
    
    /**
     * Gets the cabin.
     * 
     * @return the cabin
     */
    public int getCabin()
    {
        return cabin;
    }
    
    /**
     * Sets the cabin.
     * 
     * @param cabin
     *            the new cabin
     */
    public void setCabin( int cabin )
    {
        this.cabin = cabin;
    }
    
    /**
     * Gets the boosters.
     * 
     * @return the boosters
     */
    public int getBoosters()
    {
        return boosters;
    }
    
    /**
     * Sets the boosters.
     * 
     * @param boosters
     *            the new boosters
     */
    public void setBoosters( int boosters )
    {
        this.boosters = boosters;
    }
}

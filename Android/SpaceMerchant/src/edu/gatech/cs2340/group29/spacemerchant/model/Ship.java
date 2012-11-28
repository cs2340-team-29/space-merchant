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
    
    /** The ship id. */
    private long  shipID;
    
    /** The stats. */
    private int[] stats;
    
    /** The fuselage. */
    private int   fuselage;
    
    /** The cabin. */
    private int   cabin;
    
    /** The boosters. */
    private int   boosters;
    
    /**
     * Instantiates a new ship.
     */
    public Ship()
    {
        this.stats = new int[4];
        
        this.fuselage = android.R.drawable.ic_menu_camera;
        this.cabin = android.R.drawable.ic_menu_camera;
        this.boosters = android.R.drawable.ic_menu_camera;
    }
    
    /**
     * Gets the stats.
     * 
     * @return the stats
     */
    public int[] getStats()
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
        this.stats = stats;
    }
    
    /**
     * Gets the id.
     * 
     * @return the id
     */
    public long getID()
    {
        return this.shipID;
    }
    
    /**
     * Sets the id.
     * 
     * @param shipID
     *        the new id
     */
    public void setID( final long shipID )
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
        return this.shipID;
    }
    
    /**
     * Sets the ship id.
     * 
     * @param shipID
     *        the new ship id
     */
    public void setShipID( final long shipID )
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
        return this.fuselage;
    }
    
    /**
     * Sets the fuselage.
     * 
     * @param fuselage
     *        the new fuselage
     */
    public void setFuselage( final int fuselage )
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
        return this.cabin;
    }
    
    /**
     * Sets the cabin.
     * 
     * @param cabin
     *        the new cabin
     */
    public void setCabin( final int cabin )
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
        return this.boosters;
    }
    
    /**
     * Sets the boosters.
     * 
     * @param boosters
     *        the new boosters
     */
    public void setBoosters( final int boosters )
    {
        this.boosters = boosters;
    }
}

/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.model;

import java.util.Arrays;

/**
 * The Class StatGroup.
 */
public class StatGroup
{
    
    /**
     * The Enum Stat.
     */
    public enum Stat
    {
        
        /** The pilot. */
        PILOT,
        /** The fighter. */
        FIGHTER,
        /** The trader. */
        TRADER,
        /** The engineer. */
        ENGINEER
    }
    
    /** The values. */
    private final int[] values = new int[Stat.values().length];
    
    /**
     * Instantiates a new stat group.
     */
    public StatGroup()
    {
        Arrays.fill( this.values, 0 );
    }
    
    /**
     * Instantiates a new stat group.
     * 
     * @param statArray
     *        the int[]
     */
    public StatGroup( final int[] statArray )
    {
        System.arraycopy( this.values, 0, statArray, 0, this.values.length );
    }
    
    /**
     * Gets the.
     * 
     * @param stat
     *        the Stat
     * @return the int
     */
    public int get( final Stat stat )
    {
        return this.values[stat.ordinal()];
    }
    
    /**
     * Sets the.
     * 
     * @param stat
     *        the Stat
     * @param value
     *        the int
     */
    public void set( final Stat stat, final int value )
    {
        this.values[stat.ordinal()] = value;
    }
    
    /**
     * To int array.
     * 
     * @return the int[]
     */
    public int[] toIntArray()
    {
        return this.values;
    }
    
}

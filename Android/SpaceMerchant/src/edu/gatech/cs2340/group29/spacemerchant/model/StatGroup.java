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
        PILOT, FIGHTER, TRADER, ENGINEER
    }
    
    private int[] values = new int[Stat.values().length];
    
    /**
     * Instantiates a new stat group.
     */
    public StatGroup()
    {
        Arrays.fill( values, 0 );
    }
    
    /**
     * Instantiates a new stat group.
     *
     * @param statArray the int[]
     */
    public StatGroup( int[] statArray )
    {
        for ( int i = 0; i < statArray.length; i++ )
        {
            values[i] = statArray[i];
        }
    }
    
    /**
     * Gets the.
     *
     * @param stat the Stat
     * @return the int
     */
    public int get( Stat stat )
    {
        return values[stat.ordinal()];
    }
    
    /**
     * Sets the.
     *
     * @param stat the Stat
     * @param value the int
     */
    public void set( Stat stat, int value )
    {
        values[stat.ordinal()] = value;
    }
    
    /**
     * To int array.
     *
     * @return the int[]
     */
    public int[] toIntArray()
    {
        return values;
    }
    
}

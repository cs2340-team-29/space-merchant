/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

import android.content.Context;
import android.content.res.Resources;
import edu.gatech.cs2340.group29.spacemerchant.R;

/**
 * The Class Universe.
 */
public class Universe
{
    private ArrayList<Integer> planet_x;
    private ArrayList<Integer> planet_y;
    private ArrayList<String>  planet_name;
    private Planet[][]         universe;
    
    private int                width;
    private int                height;
    
    /**
     * Instantiates a new universe.
     * 
     * @param difficulty
     *            the int
     * @param context
     *            the Context
     */
    @SuppressWarnings ( { "rawtypes", "unchecked" })
    public Universe( int difficulty, Context context )
    {
        // 12^2 < 120 (# of planets) with some room to breathe,
        // a.k.a. the smallest universe size
        width = 12 * difficulty;
        height = 12 * difficulty;
        Resources res = context.getResources();
        planet_name = new ArrayList<String>( Arrays.asList( res.getStringArray( R.array.planets ) ) );
        
        Random r = new Random();
        while ( planet_x.size() < 120 )
        {
            planet_x.add( r.nextInt( width ) );
            // remove duplicates
            HashSet hs = new HashSet();
            hs.addAll( planet_x );
            planet_x.clear();
            planet_x.addAll( hs );
        }
        
        while ( planet_y.size() < 120 )
        {
            planet_y.add( r.nextInt( height ) );
            // remove duplicates
            HashSet hs = new HashSet();
            hs.addAll( planet_y );
            planet_y.clear();
            planet_y.addAll( hs );
        }
        
        for ( int i = 0; i < planet_name.size(); i++ )
        {
            universe[planet_x.get( i )][planet_y.get( i )] = new Planet( planet_name.get( i ) );
        }
        
    }
    
    /**
     * Gets the first planet.
     * 
     * @return the first planet
     */
    public Planet getFirstPlanet()
    {
        return universe[planet_x.get( 0 )][planet_y.get( 0 )];
    }
    
    /**
     * Planet exists at.
     * 
     * @param x
     *            the int
     * @param y
     *            the int
     * @return true, if successful
     */
    public boolean planetExistsAt( int x, int y )
    {
        if ( universe[planet_x.get( x )][planet_y.get( y )] != null )
            return true;
        
        return false;
    }
    
    /**
     * Gets the planet.
     * 
     * @param x
     *            the int
     * @param y
     *            the int
     * @return the planet
     */
    public Planet getPlanet( int x, int y )
    {
        return universe[planet_x.get( x )][planet_y.get( y )];
    }
    
    /**
     * Override:
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        String stringverse = "";
        for ( int i = 0; i < planet_name.size(); i++ )
        {
            for ( int j = 0; j < planet_name.size(); j++ )
            {
                stringverse += universe[i][j];
            }
        }
        
        return stringverse;
    }
    
    /**
     * Gets the planet_x.
     * 
     * @return the planet_x
     */
    public ArrayList<Integer> getPlanet_x()
    {
        return planet_x;
    }
    
    /**
     * Sets the planet_x.
     * 
     * @param planet_x
     *            the new planet_x
     */
    public void setPlanet_x( ArrayList<Integer> planet_x )
    {
        this.planet_x = planet_x;
    }
    
    /**
     * Gets the planet_y.
     * 
     * @return the planet_y
     */
    public ArrayList<Integer> getPlanet_y()
    {
        return planet_y;
    }
    
    /**
     * Sets the planet_y.
     * 
     * @param planet_y
     *            the new planet_y
     */
    public void setPlanet_y( ArrayList<Integer> planet_y )
    {
        this.planet_y = planet_y;
    }
    
    /**
     * Gets the planet_name.
     * 
     * @return the planet_name
     */
    public ArrayList<String> getPlanet_name()
    {
        return planet_name;
    }
    
    /**
     * Sets the planet_name.
     * 
     * @param planet_name
     *            the new planet_name
     */
    public void setPlanet_name( ArrayList<String> planet_name )
    {
        this.planet_name = planet_name;
    }
    
    /**
     * Gets the universe.
     * 
     * @return the universe
     */
    public Planet[][] getUniverse()
    {
        return universe;
    }
    
    /**
     * Sets the universe.
     * 
     * @param universe
     *            the new universe
     */
    public void setUniverse( Planet[][] universe )
    {
        this.universe = universe;
    }
    
    /**
     * Gets the width.
     * 
     * @return the width
     */
    public int getWidth()
    {
        return width;
    }
    
    /**
     * Sets the width.
     * 
     * @param width
     *            the new width
     */
    public void setWidth( int width )
    {
        this.width = width;
    }
    
    /**
     * Gets the height.
     * 
     * @return the height
     */
    public int getHeight()
    {
        return height;
    }
    
    /**
     * Sets the height.
     * 
     * @param height
     *            the new height
     */
    public void setHeight( int height )
    {
        this.height = height;
    }
}
/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import edu.gatech.cs2340.group29.spacemerchant.R;

/**
 * The Class Universe.
 */
public class Universe
{
    
    /** The planet_xy. */
    private List<Point>   planet_xy;
    
    /** The planet_name. */
    private List<String>  planet_name;
    
    /** The universe. */
    private List<Planet>  universe;
    
    /** The width. */
    private int           width;
    
    /** The height. */
    private int           height;
    
    /** The difficulty. */
    private final int     difficulty;
    
    /** The context. */
    private final Context context;
    
    /**
     * Instantiates a new universe.
     * 
     * @param difficulty
     *        the int
     * @param context
     *        the Context
     */
    public Universe( final int difficulty, final Context context )
    {
        this.difficulty = difficulty;
        this.context = context;
    }
    
    /**
     * Generate planets.
     * 
     * @return the list
     */
    public List<Planet> generatePlanets()
    {
        // 12^2 > 120 (# of planets) with some room to breathe,
        // a.k.a. the smallest universe size
        this.width = 10 + ( 2 * this.difficulty );
        this.height = 10 + ( 2 * this.difficulty );
        final Resources res = this.context.getResources();
        this.planet_name = new ArrayList<String>( Arrays.asList( res.getStringArray( R.array.planets ) ) );
        this.planet_xy = new ArrayList<Point>();
        this.universe = new ArrayList<Planet>();
        
        final Random r = new Random();
        while ( this.planet_xy.size() < 120 )
        {
            final int x = r.nextInt( this.width + 1 );
            final int y = r.nextInt( this.height + 1 );
            
            final Point p = new Point( x, y );
            this.planet_xy.add( p );
            // remove duplicates
            final Set<Point> hs = new HashSet<Point>();
            hs.addAll( this.planet_xy );
            this.planet_xy.clear();
            this.planet_xy.addAll( hs );
        }
        
        for ( int i = 0; i < this.planet_name.size(); i++ )
        {
            this.universe.add( new Planet( this.planet_name.get( i ), this.planet_xy.get( i ).x,
                    this.planet_xy.get( i ).y, this.context ) );
        }
        
        return this.universe;
    }
    
    /**
     * Override:
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return " Width: " + this.width + " Height: " + this.height + " Difficulty: " + this.difficulty
                + "Universe: " + this.universe.toString();
    }
    
    /**
     * Gets the planet_xy.
     * 
     * @return the planet_xy
     */
    public List<Point> getPlanet_xy()
    {
        return this.planet_xy;
    }
    
    /**
     * Sets the planet_xy.
     * 
     * @param planet_xy
     *        the new planet_xy
     */
    public void setPlanet_xy( final List<Point> planet_xy )
    {
        this.planet_xy = planet_xy;
    }
    
    /**
     * Gets the planet_name.
     * 
     * @return the planet_name
     */
    public List<String> getPlanet_name()
    {
        return this.planet_name;
    }
    
    /**
     * Sets the planet_name.
     * 
     * @param planet_name
     *        the new planet_name
     */
    public void setPlanet_name( final List<String> planet_name )
    {
        this.planet_name = planet_name;
    }
    
    /**
     * Gets the universe.
     * 
     * @return the universe
     */
    public List<Planet> getUniverse()
    {
        return this.universe;
    }
    
    /**
     * Sets the universe.
     * 
     * @param universe
     *        the new universe
     */
    public void setUniverse( final List<Planet> universe )
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
        return this.width;
    }
    
    /**
     * Sets the width.
     * 
     * @param width
     *        the new width
     */
    public void setWidth( final int width )
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
        return this.height;
    }
    
    /**
     * Sets the height.
     * 
     * @param height
     *        the new height
     */
    public void setHeight( final int height )
    {
        this.height = height;
    }
}
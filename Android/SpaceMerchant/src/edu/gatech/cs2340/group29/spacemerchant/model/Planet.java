/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.model;

import java.util.Random;

/**
 * The Class Planet.
 */
public class Planet
{
    // Tech Levels
    public static final int PRE_AGRICULTURAL = 0;
    public static final int AGRICULTURAL     = 1;
    public static final int MEDIEVAL         = 2;
    public static final int RENAISSANCE      = 3;
    public static final int EARLY_INDUSTRIAL = 4;
    public static final int INDUSTRIAL       = 5;
    public static final int POST_INDUSTRIAL  = 6;
    public static final int HI_TECH          = 7;
    
    // Resource Types
    public static final int NOTHING_SPECIAL  = 0;
    public static final int MINERAL_RICH     = 1;
    public static final int MINERAL_POOR     = 2;
    public static final int DESERT           = 3;
    public static final int WATERY           = 4;
    public static final int SOIL_RICH        = 5;
    public static final int SOIL_POOR        = 6;
    public static final int ANIMALS          = 7;
    public static final int ARTISTIC         = 8;
    public static final int WARFARE          = 9;
    
    private int             techLevel;
    private int             resourceType;
    private String          name;
    
    /**
     * Instantiates a new planet.
     *
     * @param name the String
     */
    public Planet( String name )
    {
        Random r = new Random();
        techLevel = r.nextInt( 8 );
        resourceType = r.nextInt( 10 );
        this.name = name;
    }
    
    /**
     * Gets the tech level.
     *
     * @return the tech level
     */
    public int getTechLevel()
    {
        return techLevel;
    }
    
    /**
     * Sets the tech level.
     *
     * @param techLevel the new tech level
     */
    public void setTechLevel( int techLevel )
    {
        this.techLevel = techLevel;
    }
    
    /**
     * Gets the resource type.
     *
     * @return the resource type
     */
    public int getResourceType()
    {
        return resourceType;
    }
    
    /**
     * Sets the resource type.
     *
     * @param resourceType the new resource type
     */
    public void setResourceType( int resourceType )
    {
        this.resourceType = resourceType;
    }
    
    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName( String name )
    {
        this.name = name;
    }

    /** 
     *
     * Override:
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return name + techLevel + resourceType;
    }
}

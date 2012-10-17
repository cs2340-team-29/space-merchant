
package edu.gatech.cs2340.group29.spacemerchant.model;

import java.util.Random;

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
    
    public Planet( String name )
    {
        Random r = new Random();
        techLevel = r.nextInt( 8 );
        resourceType = r.nextInt( 10 );
        this.name = name;
    }
    
    public int getTechLevel()
    {
        return techLevel;
    }
    
    public void setTechLevel( int techLevel )
    {
        this.techLevel = techLevel;
    }
    
    public int getResourceType()
    {
        return resourceType;
    }
    
    public void setResourceType( int resourceType )
    {
        this.resourceType = resourceType;
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


package edu.gatech.cs2340.group29.spacemerchant.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class Universe
{
    private ArrayList<Integer> planet_x;
    private ArrayList<Integer> planet_y;
    private ArrayList<String>  planet_name;
    private Planet[][]         universe;
    
    private int                width;
    private int                height;
    
    @SuppressWarnings ( { "rawtypes", "unchecked" })
    public Planet Universe( int difficulty )
    {
        // 12^2 < 120 (# of planets) with some room to breathe,
        // a.k.a. the smallest universe size
        width = 12 * difficulty;
        height = 12 * difficulty;
        
        planet_name = new ArrayList<String>( Arrays.asList( array ) );
        
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
    
}

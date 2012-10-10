
package edu.gatech.cs2340.group29.spacemerchant.model;

import android.graphics.drawable.Drawable;

public class Ship
{
    private int[] stats;
    
    private Drawable   nose;
    private Drawable   right_wing;
    private Drawable   left_wing;
    private Drawable   body;
    private Drawable   thruster;
                              
    public Ship()
    {
        stats = new int[4];
        
        nose = null;
        right_wing = null;
        left_wing = null;
        body = null;
        thruster = null;
    }
    
    public int[] getStats()
    {
        return stats;
    }
    
    public void setStats( int[] stats )
    {
        this.stats = stats;
    }
    
    public Drawable getNose()
    {
        return nose;
    }
    
    public void setNose( Drawable nose )
    {
        this.nose = nose;
    }
    
    public Drawable getRight_wing()
    {
        return right_wing;
    }
    
    public void setRight_wing( Drawable right_wing )
    {
        this.right_wing = right_wing;
    }
    
    public Drawable getLeft_wing()
    {
        return left_wing;
    }
    
    public void setLeft_wing( Drawable left_wing )
    {
        this.left_wing = left_wing;
    }
    
    public Drawable getBody()
    {
        return body;
    }
    
    public void setBody( Drawable body )
    {
        this.body = body;
    }
    
    public Drawable getThruster()
    {
        return thruster;
    }
    
    public void setThruster( Drawable thruster )
    {
        this.thruster = thruster;
    }
}

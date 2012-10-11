
package edu.gatech.cs2340.group29.spacemerchant.model;

public class Ship
{
    private long shipID;
    private int[] stats;
    
    private int   nose;
    private int   right_wing;
    private int   left_wing;
    private int   body;
    private int   thruster;
    
    public Ship()
    {
        stats = new int[4];
        
        nose = android.R.drawable.ic_menu_camera;
        right_wing = android.R.drawable.ic_menu_camera;
        left_wing = android.R.drawable.ic_menu_camera;
        body = android.R.drawable.ic_menu_camera;
        thruster = android.R.drawable.ic_menu_camera;
    }
    
    public int[] getStats()
    {
        return stats;
    }
    
    public void setStats( int[] stats )
    {
        this.stats = stats;
    }
    
    public int getNose()
    {
        return nose;
    }
    
    public void setNose( int nose )
    {
        this.nose = nose;
    }
    
    public int getRight_wing()
    {
        return right_wing;
    }
    
    public void setRight_wing( int right_wing )
    {
        this.right_wing = right_wing;
    }
    
    public int getLeft_wing()
    {
        return left_wing;
    }
    
    public void setLeft_wing( int left_wing )
    {
        this.left_wing = left_wing;
    }
    
    public int getBody()
    {
        return body;
    }
    
    public void setBody( int body )
    {
        this.body = body;
    }
    
    public int getThruster()
    {
        return thruster;
    }
    
    public void setThruster( int thruster )
    {
        this.thruster = thruster;
    }
    
    public long getID()
    {
        return shipID;
    }

    public void setID( long shipID )
    {
        this.shipID = shipID;
    } 
}

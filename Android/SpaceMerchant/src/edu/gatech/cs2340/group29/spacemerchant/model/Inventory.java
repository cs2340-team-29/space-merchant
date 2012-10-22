
package edu.gatech.cs2340.group29.spacemerchant.model;

import java.util.LinkedList;

public class Inventory
{
    private final int          numOfItemTypes = 5;
    
    private LinkedList<Item>[] Inventory;
    private int                size;
    
    @SuppressWarnings ( "unchecked")
    public Inventory()
    {
        Inventory = new LinkedList[numOfItemTypes];
        size = 0;
    }
    
    public void add( Item itm )
    {
        Inventory[itm.getType()].add( itm );
        size++;
    }
    
    public void addAll( Item[] itm )
    {
        for ( int i = 0; i < itm.length; i++ )
            add( itm[i] );
    }
    
    public boolean hasItem( Item itm )
    {
        if ( Inventory[itm.getType()].size() > 0 )
            for ( int i = 0; i < Inventory[itm.getType()].size(); i++ )
                if ( Inventory[itm.getType()].get( i ).compareTo( itm ) == 0 )
                    return true;
        
        return false;
    }
    
    public Item getItem( Item itm )
    {
        if ( Inventory[itm.getType()].size() > 0 )
            for ( int i = 0; i < Inventory[itm.getType()].size(); i++ )
                if ( Inventory[itm.getType()].get( i ).compareTo( itm ) == 0 )
                    return Inventory[itm.getType()].get( i );
        
        return null;
    }
    
    public Item remove( Item itm )
    {
        if ( Inventory[itm.getType()].size() > 0 )
            for ( int i = 0; i < Inventory[itm.getType()].size(); i++ )
                if ( Inventory[itm.getType()].get( i ).compareTo( itm ) == 0 )
                {
                    Item temp = Inventory[itm.getType()].get( i );
                    Inventory[itm.getType()].remove( i );
                    size--;
                    return temp;
                }
        
        return null;
    }
    
    @SuppressWarnings ( "unchecked")
    public void clear()
    {
        Inventory = new LinkedList[numOfItemTypes];
        size = 0;
    }
    
    public int size()
    {
        return size;
    }
    
}

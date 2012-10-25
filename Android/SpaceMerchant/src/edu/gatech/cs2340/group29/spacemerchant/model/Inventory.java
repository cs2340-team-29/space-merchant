
package edu.gatech.cs2340.group29.spacemerchant.model;

import java.util.LinkedList;

public class Inventory
{
    private final int          numOfItemTypes = 7;
    
    private LinkedList<Item>[] Inventory;
    private int                size;
    private int capacity;
    
    /**
     * 
     * constructor creates the inventory
     * 
     */
    @SuppressWarnings ( "unchecked")
    public Inventory()
    {
        Inventory = new LinkedList[numOfItemTypes];
        size = 0;
        capacity = 100;
    }
    
    /**
     * 
     * constructor creates the inventory
     * 
     */
    @SuppressWarnings ( "unchecked")
    public Inventory(int capacity)
    {
        Inventory = new LinkedList[numOfItemTypes];
        size = 0;
        this.capacity = capacity;
    }
    
    /**
     * 
     * adds an item to an inventory
     * 
     * @param itm
     */
    public void add( Item itm )
    {
        Inventory[itm.getType()].add(itm);
        size++;
    }
    
    /**
     * 
     * adds all the items from an array to an inventory
     * 
     * @param itm
     */
    public void addAll( Item[] itm )
    {
        for ( int i = 0; i < itm.length; i++ )
            add( itm[i] );
    }
    
    /**
     * 
     * checks to see if an item is in the inventory
     * 
     * @param itm
     * @return true if the inventory contains item; false otherwise
     */
    public boolean hasItem( Item itm )
    {
        if ( Inventory[itm.getType()].size() > 0 )
            for ( int i = 0; i < Inventory[itm.getType()].size(); i++ )
                if ( Inventory[itm.getType()].get( i ).compareTo( itm ) == 0 )
                    return true;
        
        return false;
    }
    
    /**
     * 
     * gets an item from the inventory
     * 
     * @param itm
     * @return the item if it exists, null otherwise
     */
    public Item getItem( Item itm )
    {
        if ( Inventory[itm.getType()].size() > 0 )
            for ( int i = 0; i < Inventory[itm.getType()].size(); i++ )
                if ( Inventory[itm.getType()].get( i ).compareTo( itm ) == 0 )
                    return Inventory[itm.getType()].get( i );
        
        return null;
    }
    
    /**
     * 
     * removes an item from the inventory and return it
     * 
     * @param itm
     * @return returns the item removed, null otherwise
     */
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
    
    /**
     * 
     * clear the inventory
     * 
     */
    @SuppressWarnings ( "unchecked")
    public void clear()
    {
        Inventory = new LinkedList[numOfItemTypes];
        size = 0;
    }
    
    /**
     * 
     * gets the size of the inventory
     * 
     * @return the size of the inventory
     */
    public int size()
    {
        return size;
    }
    
    public int capacity() {
    	return capacity;
    }
    
    public LinkedList<Item>[] getContents() {
    	return Inventory;
    }
}

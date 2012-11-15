/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.model;

import java.util.LinkedList;

/**
 * The Class Inventory.
 */
public class Inventory
{
    public static final int    numOfItemTypes = 7;
    
    private LinkedList<Item>[] Inventory;
    private int                size;
    private int                capacity;
    
    /**
     * Instantiates a new inventory.
     */
    @SuppressWarnings ( "unchecked")
    public Inventory()
    {
        Inventory = new LinkedList[numOfItemTypes];
        for ( int i = 0; i < Inventory.length; i++ )
        {
            Inventory[i] = new LinkedList<Item>();
        }
        size = 0;
        capacity = 100;
    }
    
    /**
     * Instantiates a new inventory.
     * 
     * @param capacityMultiplyer
     *            the int
     */
    @SuppressWarnings ( "unchecked")
    public Inventory( int capacityMultiplyer )
    {
        Inventory = new LinkedList[numOfItemTypes];
        for ( int i = 0; i < Inventory.length; i++ )
        {
            Inventory[i] = new LinkedList<Item>();
        }
        size = 0;
        this.capacity = 10 + ( capacityMultiplyer * 2 );
    }
    
    /**
     * Adds the.
     * 
     * @param itm
     *            the Item
     */
    public void add( Item itm )
    {
        if ( size + 1 < capacity )
        {
            Inventory[itm.getType()].add( itm );
            size++;
        }
    }
    
    /**
     * Adds the all.
     * 
     * @param itm
     *            the Item[]
     */
    public void addAll( Item[] itm )
    {
        for ( Item element : itm )
        {
            add( element );
        }
    }
    
    /**
     * Checks for item.
     * 
     * @param itm
     *            the Item
     * @return true, if successful
     */
    public boolean hasItem( Item itm )
    {
        if ( Inventory[itm.getType()].size() > 0 )
        {
            for ( int i = 0; i < Inventory[itm.getType()].size(); i++ )
            {
                if ( Inventory[itm.getType()].get( i ).compareTo( itm ) == 0 )
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Gets the item.
     * 
     * @param itm
     *            the Item
     * @return the item
     */
    public Item getItem( Item itm )
    {
        if ( Inventory[itm.getType()].size() > 0 )
        {
            for ( int i = 0; i < Inventory[itm.getType()].size(); i++ )
            {
                if ( Inventory[itm.getType()].get( i ).compareTo( itm ) == 0 )
                {
                    return Inventory[itm.getType()].get( i );
                }
            }
        }
        
        return null;
    }
    
    /**
     * Removes the.
     * 
     * @param itm
     *            the Item
     * @return the item
     */
    public Item remove( Item itm )
    {
        if ( Inventory[itm.getType()].size() > 0 )
        {
            for ( int i = 0; i < Inventory[itm.getType()].size(); i++ )
            {
                if ( Inventory[itm.getType()].get( i ).compareTo( itm ) == 0 )
                {
                    Item temp = Inventory[itm.getType()].get( i );
                    Inventory[itm.getType()].remove( i );
                    size--;
                    return temp;
                }
            }
        }
        
        return null;
    }
    
    /**
     * Clear.
     */
    @SuppressWarnings ( "unchecked")
    public void clear()
    {
        Inventory = new LinkedList[numOfItemTypes];
        size = 0;
    }
    
    /**
     * Size.
     * 
     * @return the int
     */
    public int size()
    {
        return size;
    }
    
    /**
     * Capacity.
     * 
     * @return the int
     */
    public int capacity()
    {
        return capacity;
    }
    
    /**
     * Gets the contents.
     * 
     * @return the contents
     */
    public LinkedList<Item>[] getContents()
    {
        return Inventory;
    }
}

/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.model;

import java.util.LinkedList;
import java.util.List;

/**
 * The Class Inventory.
 */
public class Inventory
{
    
    /** The Constant NUM_ITEM_TYPES. */
    public static final int NUM_ITEM_TYPES = 7;
    
    /** The inventory. */
    private List<Item>[]    inventory;
    
    /** The size. */
    private int             size;
    
    /** The capacity. */
    private final int       capacity;
    
    /**
     * Instantiates a new inventory.
     */
    @SuppressWarnings ( "unchecked")
    public Inventory()
    {
        this.inventory = new LinkedList[Inventory.NUM_ITEM_TYPES];
        for ( int i = 0; i < this.inventory.length; i++ )
        {
            this.inventory[i] = new LinkedList<Item>();
        }
        this.size = 0;
        this.capacity = 100;
    }
    
    /**
     * Instantiates a new inventory.
     * 
     * @param capacityMultiplyer
     *        the int
     */
    @SuppressWarnings ( "unchecked")
    public Inventory( final int capacityMultiplyer )
    {
        this.inventory = new LinkedList[Inventory.NUM_ITEM_TYPES];
        for ( int i = 0; i < this.inventory.length; i++ )
        {
            this.inventory[i] = new LinkedList<Item>();
        }
        this.size = 0;
        this.capacity = 10 + ( capacityMultiplyer << 1 );
    }
    
    /**
     * Adds the.
     * 
     * @param itm
     *        the Item
     */
    public void add( final Item itm )
    {
        if ( ( this.size + 1 ) < this.capacity )
        {
            this.inventory[itm.getType()].add( itm );
            this.size++;
        }
    }
    
    /**
     * Adds the all.
     * 
     * @param itm
     *        the Item[]
     */
    public void addAll( final Item[] itm )
    {
        for ( final Item element : itm )
        {
            this.add( element );
        }
    }
    
    /**
     * Checks for item.
     * 
     * @param itm
     *        the Item
     * @return true, if successful
     */
    public boolean hasItem( final Item itm )
    {
        if ( this.inventory[itm.getType()].size() > 0 )
        {
            for ( int i = 0; i < this.inventory[itm.getType()].size(); i++ )
            {
                if ( this.inventory[itm.getType()].get( i ).compareTo( itm ) == 0 )
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
     *        the Item
     * @return the item
     */
    public Item getItem( final Item itm )
    {
        if ( this.inventory[itm.getType()].size() > 0 )
        {
            for ( int i = 0; i < this.inventory[itm.getType()].size(); i++ )
            {
                if ( this.inventory[itm.getType()].get( i ).compareTo( itm ) == 0 )
                {
                    return this.inventory[itm.getType()].get( i );
                }
            }
        }
        
        return null;
    }
    
    /**
     * Removes the.
     * 
     * @param itm
     *        the Item
     * @return the item
     */
    public Item remove( final Item itm )
    {
        if ( this.inventory[itm.getType()].size() > 0 )
        {
            for ( int i = 0; i < this.inventory[itm.getType()].size(); i++ )
            {
                if ( this.inventory[itm.getType()].get( i ).compareTo( itm ) == 0 )
                {
                    final Item temp = this.inventory[itm.getType()].get( i );
                    this.inventory[itm.getType()].remove( i );
                    this.size--;
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
        this.inventory = new LinkedList[Inventory.NUM_ITEM_TYPES];
        this.size = 0;
    }
    
    /**
     * Size.
     * 
     * @return the int
     */
    public int size()
    {
        return this.size;
    }
    
    /**
     * Capacity.
     * 
     * @return the int
     */
    public int capacity()
    {
        return this.capacity;
    }
    
    /**
     * Gets the contents.
     * 
     * @return the contents
     */
    public List<Item>[] getContents()
    {
        return this.inventory;
    }
}

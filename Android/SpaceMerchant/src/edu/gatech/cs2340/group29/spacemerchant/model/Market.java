/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.model;

import edu.gatech.cs2340.group29.spacemerchant.interfaces.Marketable;

public class Market
{
    
    // The two marketables to trade between
    private Marketable a, b;
    
    public Market( Marketable a, Marketable b )
    {
        setMarketableA( a );
        setMarketableB( b );
    }
    
    public Marketable getMarketableA()
    {
        return a;
    }
    
    public void setMarketableA( Marketable a )
    {
        this.a = a;
    }
    
    public Marketable getMarketableB()
    {
        return b;
    }
    
    public void setMarketableB( Marketable b )
    {
        this.b = b;
    }
    
    public int getCost(Item item)
    {
        return ( int ) ( ( item.getBasePrice() + a.getBasePrice( item ) + b.getBasePrice( item ) ) / 3.0 );
    }
    
    /**
     * Attempts to give an item from inventory A to inventory B.
     * 
     * @param item
     * @return whether or not the trade was successful
     */
    public boolean giveToB( Item item )
    {
        if ( b.getInventory().size() < b.getInventory().capacity() )
        {
            int cost = ( int ) ( ( item.getBasePrice() + a.getBasePrice( item ) + b.getBasePrice( item ) ) / 3.0 );
            if ( !a.getInventory().hasItem( item ) )
            {
                return false;
            }
            if ( b.getMoney() < cost )
            {
                return false;
            }
            Item aItem = a.getInventory().getItem( item );
            a.getInventory().remove( aItem );
            b.getInventory().add( aItem );
            a.setMoney( a.getMoney() + cost );
            b.setMoney( b.getMoney() - cost );
            
            return true;
        }
        return false;
    }
    
    /**
     * Attempts to give an item from inventory B to inventory A.
     * 
     * @param item
     * @return whether or not the trade was successful
     */
    public boolean giveToA( Item item )
    {
        if ( a.getInventory().size() < a.getInventory().capacity() )
        {
            int cost = ( int ) ( ( item.getBasePrice() + a.getBasePrice( item ) + b.getBasePrice( item ) ) / 3.0 );
            if ( !b.getInventory().hasItem( item ) )
            {
                return false;
            }
            if ( a.getMoney() < cost )
            {
                return false;
            }
            Item bItem = b.getInventory().getItem( item );
            b.getInventory().remove( bItem );
            a.getInventory().add( bItem );
            b.setMoney( b.getMoney() + cost );
            a.setMoney( a.getMoney() - cost );
            return true;
        }
        return false;
    }
    
}

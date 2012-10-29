/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.model;

import edu.gatech.cs2340.group29.spacemerchant.interfaces.Marketable;

/**
 * The Class Market.
 */
public class Market
{
    
    // The two marketables to trade between
    private Marketable a, b;
    
    /**
     * Instantiates a new market.
     *
     * @param a the Marketable
     * @param b the Marketable
     */
    public Market( Marketable a, Marketable b )
    {
        setMarketableA( a );
        setMarketableB( b );
    }
    
    /**
     * Gets the marketable a.
     *
     * @return the marketable a
     */
    public Marketable getMarketableA()
    {
        return a;
    }
    
    /**
     * Sets the marketable a.
     *
     * @param a the new marketable a
     */
    public void setMarketableA( Marketable a )
    {
        this.a = a;
    }
    
    /**
     * Gets the marketable b.
     *
     * @return the marketable b
     */
    public Marketable getMarketableB()
    {
        return b;
    }
    
    /**
     * Sets the marketable b.
     *
     * @param b the new marketable b
     */
    public void setMarketableB( Marketable b )
    {
        this.b = b;
    }
    
    /**
     * Gets the cost.
     *
     * @param item the Item
     * @return the cost
     */
    public int getCost( Item item )
    {
        return ( int ) ( ( item.getBasePrice() + a.getBasePrice( item ) + b.getBasePrice( item ) ) / 3.0 );
    }
    
    /**
     * Give to b.
     *
     * @param item the Item
     * @return true, if successful
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
     * Give to a.
     *
     * @param item the Item
     * @return true, if successful
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

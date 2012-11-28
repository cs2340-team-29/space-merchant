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
    /** The b. */
    private Marketable a, b;
    
    /**
     * Instantiates a new market.
     * 
     * @param a
     *        the Marketable
     * @param b
     *        the Marketable
     */
    public Market( final Marketable a, final Marketable b )
    {
        this.a = a;
        this.b = b;
    }
    
    /**
     * Gets the marketable a.
     * 
     * @return the marketable a
     */
    public Marketable getMarketableA()
    {
        return this.a;
    }
    
    /**
     * Sets the marketable a.
     * 
     * @param a
     *        the new marketable a
     */
    public void setMarketableA( final Marketable a )
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
        return this.b;
    }
    
    /**
     * Sets the marketable b.
     * 
     * @param b
     *        the new marketable b
     */
    public void setMarketableB( final Marketable b )
    {
        this.b = b;
    }
    
    /**
     * Gets the cost.
     * 
     * @param item
     *        the Item
     * @return the cost
     */
    public int getCost( final Item item )
    {
        return ( int ) ( ( item.getBasePrice() + this.a.getBasePrice( item ) + this.b.getBasePrice( item ) ) / 3.0 );
    }
    
    /**
     * Give to b.
     * 
     * @param item
     *        the Item
     * @return true, if successful
     */
    public boolean giveToB( final Item item )
    {
        if ( this.b.getInventory().size() < this.b.getInventory().capacity() )
        {
            final int cost = ( int ) ( ( item.getBasePrice() + this.a.getBasePrice( item ) + this.b
                    .getBasePrice( item ) ) / 3.0 );
            if ( !this.a.getInventory().hasItem( item ) )
            {
                return false;
            }
            if ( this.b.getMoney() < cost )
            {
                return false;
            }
            final Item aItem = this.a.getInventory().getItem( item );
            this.a.getInventory().remove( aItem );
            this.b.getInventory().add( aItem );
            this.a.setMoney( this.a.getMoney() + cost );
            this.b.setMoney( this.b.getMoney() - cost );
            
            return true;
        }
        return false;
    }
    
    /**
     * Give to a.
     * 
     * @param item
     *        the Item
     * @return true, if successful
     */
    public boolean giveToA( final Item item )
    {
        if ( this.a.getInventory().size() < this.a.getInventory().capacity() )
        {
            final int cost = ( int ) ( ( item.getBasePrice() + this.a.getBasePrice( item ) + this.b
                    .getBasePrice( item ) ) / 3.0 );
            if ( !this.b.getInventory().hasItem( item ) )
            {
                return false;
            }
            if ( this.a.getMoney() < cost )
            {
                return false;
            }
            final Item bItem = this.b.getInventory().getItem( item );
            this.b.getInventory().remove( bItem );
            this.a.getInventory().add( bItem );
            this.b.setMoney( this.b.getMoney() + cost );
            this.a.setMoney( this.a.getMoney() - cost );
            return true;
        }
        return false;
    }
    
}

/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.interfaces;

import edu.gatech.cs2340.group29.spacemerchant.model.Inventory;
import edu.gatech.cs2340.group29.spacemerchant.model.Item;

/**
 * The Interface Marketable.
 */
public interface Marketable
{
    
    /**
     * Gets the base price.
     * 
     * @param item
     *        the Item
     * @return the base price
     */
    int getBasePrice( Item item );
    
    /**
     * Gets the inventory.
     * 
     * @return the inventory
     */
    Inventory getInventory();
    
    /**
     * Sets the inventory.
     * 
     * @param newInventory
     *        the new inventory
     */
    void setInventory( Inventory newInventory );
    
    /**
     * Gets the money.
     * 
     * @return the money
     */
    int getMoney();
    
    /**
     * Sets the money.
     * 
     * @param money
     *        the new money
     */
    void setMoney( int money );
    
    /**
     * Gets the name.
     * 
     * @return the name
     */
    String getName();
    
}

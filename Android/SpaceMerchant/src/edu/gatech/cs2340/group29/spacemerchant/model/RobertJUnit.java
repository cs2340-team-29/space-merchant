/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.model;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * The Class RobertJUnit.
 */
public class RobertJUnit extends TestCase
{
    
    /**
     * Test has item.
     */
    public void testHasItem()
    {
        
        final Inventory inventory = new Inventory();
        final Item item = new Item( 1, "Fake item", 1, 1 );
        
        inventory.add( item );
        
        Assert.assertTrue( "success", inventory.hasItem( item ) );
        
    }
    
}

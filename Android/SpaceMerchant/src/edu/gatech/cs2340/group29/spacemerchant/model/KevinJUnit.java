/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.model;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * The Class KevinJUnit.
 */
public class KevinJUnit extends TestCase
{
    
    /**
     * Test remove.
     */
    public void testRemove()
    {
        final int capacity = 10;
        final Inventory inventory = new Inventory( capacity );
        Inventory inventory2 = inventory;
        final Item item = new Item( 0, "Item1", 0, 0 );
        Item ret;
        try
        {
            ret = inventory.remove( item );
            Assert.assertTrue( "Check removing from an empty inventory", ret == null );
        }
        catch ( final Exception e )
        {
            Assert.fail( "Did not check for removing from an empty inventory" );
        }
        inventory.add( item );
        try
        {
            ret = inventory.remove( item );
            Assert.assertTrue( "Check removing item from one-item inventory",
                    ( ret == item ) && inventory.equals( inventory2 ) );
        }
        catch ( final Exception e )
        {
            Assert.fail( "Error when removing item from one-item inventory" );
        }
        
        for ( int i = 0; i < ( capacity - 1 ); i++ )
        {
            inventory.add( item );
        }
        inventory2 = inventory;
        final Item item2 = new Item( 1, "Item2", 1, 1 );
        inventory.add( item2 );
        try
        {
            ret = inventory.remove( item2 );
            Assert.assertTrue( "Check removing item from full inventory",
                    ( ret == item2 ) && inventory.equals( inventory2 ) );
        }
        catch ( final Exception e )
        {
            Assert.fail( "Error when removing item from full inventory" );
        }
        
    }
    
}

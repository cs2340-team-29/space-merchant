/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.model;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * The Class HarrisonJUnit.
 */
public class HarrisonJUnit extends TestCase
{
    
    /**
     * Test add to inventory.
     */
    public void testAddToInventory()
    {
        final Inventory inva = new Inventory( 1 );
        
        final Item item = new Item( 0, "Item", 0, 0 );
        
        try
        {
            inva.add( item );
            Assert.assertTrue( "Checking Size Increased", 1 == inva.size() );
        }
        catch ( final Exception e )
        {
            Assert.fail( "Size did not Increase!" );
        }
        try
        {
            Assert.assertTrue( "Checking Item was Added", inva.hasItem( item ) );
        }
        catch ( final Exception e )
        {
            Assert.fail( "Item wasn't truly added! (Depends on hasItem())" );
        }
        try
        {
            inva.add( item );
            Assert.assertTrue( "Checking Item was Added", inva.hasItem( item ) );
            inva.remove( item );
            Assert.assertTrue( "Checking Item was Added", inva.hasItem( item ) );
        }
        catch ( final Exception e )
        {
            Assert.fail( "Item wasn't added twice!" );
        }
        
        final Inventory invc = new Inventory( -4 );
        invc.add( item );
        invc.add( item );
        try
        {
            final Item itemb = new Item( 0, "ITEMB", 0, 0 );
            invc.add( itemb );
            Assert.assertTrue( "Inventory size did not increase becuase it was at max capacity!",
                    invc.size() == 1 );
        }
        catch ( final Exception e )
        {
            Assert.fail( "Inventory added an item even though it was full!" );
        }
    }
}

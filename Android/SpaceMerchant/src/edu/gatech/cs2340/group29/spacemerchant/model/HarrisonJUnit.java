
package edu.gatech.cs2340.group29.spacemerchant.model;

import junit.framework.TestCase;

public class HarrisonJUnit extends TestCase
{
    public void testAddToInventory()
    {
        Inventory inva = new Inventory( 10 );
        Inventory invb = new Inventory( 10 );
        
        Item item = new Item( 0, "Item", 0, 0 );
        int sizea = inva.size();
        int sizeb = invb.size();
        
        try
        {
            inva.add( item );
            assertTrue( "Checking Size Increased", sizeb + 1 == inva.size() );
        }
        catch ( Exception e )
        {
            fail( "Size did not Increase!" );
        }
        try
        {
            assertTrue( "Checking Item was Added", inva.hasItem( item ) );
        }
        catch ( Exception e )
        {
            fail( "Item wasn't truly added! (Depends on hasItem())" );
        }
        try
        {
            inva.add( item );
            assertTrue( "Checking Item was Added", inva.hasItem( item ) );
            inva.remove( item );
            assertTrue( "Checking Item was Added", inva.hasItem( item ) );
        }
        catch ( Exception e )
        {
            fail( "Item wasn't added twice!" );
        }
        
        Inventory invc = new Inventory( 1 );
        int sizec = invc.size();
        invc.add( item );
        try
        {
            invc.add( item );
            Item itemb = new Item( 0, "ITEMB", 0, 0 );
            invc.add( item );
            assertTrue( "Inventory size did not increase becuase it was at max capacity!", invc.size() == 1 );
        }
        catch ( Exception e )
        {
            fail( "Inventory added an item even though it was full!" );
        }
    }
}

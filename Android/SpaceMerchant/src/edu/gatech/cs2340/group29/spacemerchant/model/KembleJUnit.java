/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.model;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * The Class KembleJUnit.
 */
public class KembleJUnit extends TestCase
{
    
    /**
     * Kemble junit.
     */
    public void KembleJunit()
    {
    }
    
    /**
     * Test item_get base price.
     */
    public void testItem_getBasePrice()
    {
        final Item itm1 = new Item( 1, "widget", 1, 3 );
        
        final int calculated_base_price = 100 + itm1.getType() + ( itm1.getType() * Game.DIFFICULTY );
        
        System.out.println( "Calculated base price of item: " + calculated_base_price );
        System.out.println( "Game method base price: " + calculated_base_price );
        
        Assert.assertTrue( "Base price method works properly!",
                ( itm1.getBasePrice() == calculated_base_price ) );
    }
}

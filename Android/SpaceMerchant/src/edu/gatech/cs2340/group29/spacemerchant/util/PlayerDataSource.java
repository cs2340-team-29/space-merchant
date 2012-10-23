/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import edu.gatech.cs2340.group29.spacemerchant.model.Player;
import edu.gatech.cs2340.group29.spacemerchant.model.Ship;
import edu.gatech.cs2340.group29.spacemerchant.model.Inventory;
import edu.gatech.cs2340.group29.spacemerchant.model.Item;

/**
 * The Class PlayerDataSource.
 */
public class PlayerDataSource
{
    private static String[] ALL_COLUMNS = { "name", "money", "pilotSkillPoints", "fighterSkillPoints",
            "traderSkillPoints", "engineerSkillPoints" };
    
    private SQLiteDatabase  database;
    private DatabaseHelper  databaseHelper;
    
    /**
     * Instantiates a new player data source.
     *
     * @param context the Context
     */
    public PlayerDataSource( Context context )
    {
        databaseHelper = new DatabaseHelper( context );
    }
    
    /**
     * Open.
     *
     * @throws SQLiteException the sQ lite exception
     */
    public void open() throws SQLiteException
    {
        database = databaseHelper.getWritableDatabase();
    }
    
    /**
     * Close.
     */
    public void close()
    {
        databaseHelper.close();
    }
    
    /**
     * Creates the player.
     *
     * @param player the Player
     * @return the long
     */
    public long createPlayer( Player player )
    {
        ContentValues values = new ContentValues();
        
        String name = player.getName();
        int money = player.getMoney();
        int[] stats = player.getStats();
        int head = player.getHead();
        int body = player.getBody();
        int legs = player.getLegs();
        int feet = player.getFeet();
    
        Ship ship = player.getShip();
        
        int fuselage = ship.getFuselage();
        int cabin = ship.getCabin();
        int boosters = ship.getBoosters();
       
        values.put("fuselage", fuselage);
        values.put("cabin", cabin);
        values.put("boosters", boosters);
        
        long shipID = database.insert( "tb_ship", null, values );
        
        values = new ContentValues();
        
        values.put( "name", name );
        values.put( "money", money );
        values.put( "pilotSkillPoints", stats[0] );
        values.put( "fighterSkillPoints", stats[1] );
        values.put( "traderSkillPoints", stats[2] );
        values.put( "engineerSkillPoints", stats[3] );
        values.put( "head", head );
        values.put( "body", body );
        values.put( "legs", legs );
        values.put( "feet", feet );
        values.put( "ship", shipID );
        
        long playerID = database.insert( "tb_player", null, values );

        Inventory inventory = player.getInventory();
       
        LinkedList<Item>[] inventoryItems = inventory.getContents();
       
        int itemType = 0;
        
        for( LinkedList<Item> inventoryItemsByType : inventoryItems )
        {
            
            for( Item item : inventoryItemsByType)
            {
                
               values = new ContentValues();
               
               int basePrice    = item.getBasePrice();
               String itemName  = item.getName();
               int drawable     = item.getDrawable();
               
               values.put( "player", playerID );
               values.put( "type", itemType );
               values.put( "base_price", basePrice );
               values.put( "name", itemName );
               values.put( "drawable", drawable );
                   
               database.insert( "tb_player_inventory", null, values);
            }
            
            itemType++;
        }
        
        return playerID;
    }
    
    /**
     * Delete player.
     *
     * @param playerID the long
     */
    public void deletePlayer( long playerID )
    {
        database.delete( "tb_player", "player=" + playerID, null );
    }
    
    /**
     * Gets the player list.
     *
     * @return the player list
     */
    public List<Player> getPlayerList()
    {
        List<Player> players = new ArrayList<Player>();
        
        Cursor cursor = database.query( "tb_player", ALL_COLUMNS, null, null, null, null, null );
        
        cursor.moveToFirst();
        
        while ( !cursor.isAfterLast() )
        {
            Player player = cursorToPlayer( cursor );
            
            players.add( player );
            
            cursor.moveToNext();
        }
        
        cursor.close();
        return players;
    }
    
    /**
     * Gets the player inventory by player ID
     *
     * @param player ID long
     * @return the inventory by id
     */
    public LinkedList<Item> getPlayerInventoryItemsByPlayerID( long playerID )
    {
        
        LinkedList<Item> playerInventoryItems = null;
       
        String query = "select name, type, base_price, drawable from tb_player_inventory_item";
                
        Cursor cursor = database.rawQuery(query, null);
        
        cursor.moveToFirst();
        
        while( cursor.isLast() )
        {
            
            cursor.moveToNext();
        }
        
        cursor.close();
        
        return playerInventoryItems;
    }
    
    /**
     * Gets the player by id.
     *
     * @param playerID the long
     * @return the player by id
     */
    public Player getPlayerByID( long playerID )
    {
        
        Player player = null;
       
        String query = "select player, name, money, pilotSkillPoints, "
                     + "       fighterSkillPoints, traderSkillPoints, engineerSkillPoints, "
                     + "       head, body, legs, feet, "
                     + "       fuselage, cabin, boosters"
                     + "  from tb_player, tb_ship "
                     + " where tb_player.ship = tb_ship.ship ";
                
        Cursor cursor = database.rawQuery(query, null);
        
        
        if ( cursor.moveToFirst() )
        {
            player = cursorToPlayer( cursor );
            
        }
        
        cursor.close();
        return player;
    }
    
    /**
     * Cursor to player.
     *
     * @param cursor the Cursor
     * @return the player
     */
    public Player cursorToPlayer( Cursor cursor )
    {
        Player player       = new Player();
        Ship ship           = new Ship();
        Inventory inventory = new Inventory();
      
        long playerID           = cursor.getLong(0);
        String name             = cursor.getString(1);
        int money               = cursor.getInt(2);
        int pilotSkillPoints    = cursor.getInt(3);
        int fighterSkillPoints  = cursor.getInt(4);
        int traderSkillPoints   = cursor.getInt(5);
        int engineerSkillPoints = cursor.getInt(6);
        
        int head                = cursor.getInt(7);
        int body                = cursor.getInt(8); 
        int legs                = cursor.getInt(9);
        int feet                = cursor.getInt(10);
        int fuselage            = cursor.getInt(11);
        int cabin               = cursor.getInt(12);
        int boosters            = cursor.getInt(13);
       
        LinkedList<Item> inventoryItems = getPlayerInventoryItemsByPlayerID(playerID);
       
        for( Item item : inventoryItems )
        {
            
        
        }
        
        player.setName( cursor.getString( 1 ) );
        player.setMoney( cursor.getInt( 2 ) );

        int[] stats = {pilotSkillPoints, fighterSkillPoints, traderSkillPoints, engineerSkillPoints};
        player.setStats( stats );
        
        player.setHead(head);
        player.setBody(body);
        player.setLegs(legs);
        player.setFeet(feet);
       
        ship.setFuselage(fuselage);
        ship.setCabin(cabin);
        ship.setBoosters(boosters);
       
        player.setShip(ship);
        player.setInventory(inventory);
        return player;
    }
   
    private Item cursorToItem(Cursor cursor)
    {
       
        
        String name   = cursor.getString(0);
        int type      = cursor.getInt(1);
        int basePrice = cursor.getInt(2);
        int drawable  = cursor.getInt(3);
       
        Item item = new Item(type, basePrice, name, drawable);
        
        return item;
        
    }
    
}

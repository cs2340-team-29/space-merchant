/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.util;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import edu.gatech.cs2340.group29.spacemerchant.model.Player;
import edu.gatech.cs2340.group29.spacemerchant.model.Ship;

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
     * Opens the database for writing.
     *
     * @throws SQLiteException the sQ lite exception
     */
    public void open() throws SQLiteException
    {
        database = databaseHelper.getWritableDatabase();
    }
    
    /**
     * Closes the database.
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
        
        long shipID = ship.getID();
        
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
     * Gets the player by id.
     *
     * @param playerID the long
     * @return the player by id
     */
    public Player getPlayerByID( long playerID )
    {
        
        Player player = null;
        Cursor cursor = database.query( "tb_player", ALL_COLUMNS, "player=" + playerID, null, null, null,
                null );
        
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
        Player player = new Player();
        
        player.setName( cursor.getString( 0 ) );
        player.setMoney( cursor.getInt( 1 ) );
        
        int[] stats = { cursor.getInt( 2 ), cursor.getInt( 3 ), cursor.getInt( 4 ), cursor.getInt( 5 ) };
        
        player.setStats( stats );
        // long shipID = cursor.getLong(7);
        
        // change this to pull actual ship based on ID
        Ship ship = new Ship();
        
        player.setShip( ship );
        
        return player;
    }
}

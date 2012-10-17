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
import edu.gatech.cs2340.group29.spacemerchant.model.Game;
import edu.gatech.cs2340.group29.spacemerchant.model.Player;

/**
 * The Class GameDataSource.
 */
public class GameDataSource
{
    private static String[] ALL_COLUMNS = { "game", "difficultyLevel", "player" };
    
    private SQLiteDatabase  database;
    private DatabaseHelper  databaseHelper;
    private Context         context;
    
    /**
     * Instantiates a new game data source.
     *
     * @param context the Context
     */
    public GameDataSource( Context context )
    {
        this.context = context;
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
     * Creates the game.
     *
     * @param game the Game
     * @return the long
     */
    public long createGame( Game game )
    {
        ContentValues values = new ContentValues();
        
        int difficulty = game.getDifficulty();
        Player player = game.getPlayer();
        
        PlayerDataSource playerDataSource = new PlayerDataSource( context );
        
        playerDataSource.open();
        long playerID = playerDataSource.createPlayer( player );
        playerDataSource.close();
        
        values.put( "player", playerID );
        values.put( "difficultyLevel", difficulty );
        
        long gameID = database.insert( "tb_game", null, values );
        
        game.setID( gameID );
        
        return gameID;
    }
    
    /**
     * Delete game.
     *
     * @param game the Game
     */
    public void deleteGame( Game game )
    {
        long gameID = game.getID();
        
        database.delete( "tb_game", "game=" + gameID, null );
    }
    
    /**
     * Gets the game list.
     *
     * @return the game list
     */
    public List<Game> getGameList()
    {
        List<Game> games = new ArrayList<Game>();
        
        Cursor cursor = database.query( "tb_game", ALL_COLUMNS, null, null, null, null, null );
        
        cursor.moveToFirst();
        
        while ( !cursor.isAfterLast() )
        {
            Game game = cursorToGame( cursor );
            
            games.add( game );
            
            cursor.moveToNext();
            
        }
        
        cursor.close();
        return games;
    }
    
    /**
     * Gets the game by id.
     *
     * @param gameID the long
     * @return the game by id
     */
    public Game getGameByID( long gameID )
    {
        
        Cursor cursor = database.query( "tb_game", ALL_COLUMNS, "game=" + gameID, null, null, null, null );
        
        cursor.moveToFirst();
        
        Game game = cursorToGame( cursor );
        
        cursor.close();
        
        return game;
    }
    
    /**
     * Cursor to game.
     *
     * @param cursor the Cursor
     * @return the game
     */
    public Game cursorToGame( Cursor cursor )
    {
        
        Game game = new Game();
        
        game.setID( cursor.getLong( 0 ) );
        game.setDifficulty( cursor.getInt( 1 ) );
        
        PlayerDataSource dataSource = new PlayerDataSource( context );
        
        dataSource.open();
        Player player = dataSource.getPlayerByID( cursor.getInt( 2 ) );
        dataSource.close();
        
        game.setPlayer( player );
        
        return game;
    }
}

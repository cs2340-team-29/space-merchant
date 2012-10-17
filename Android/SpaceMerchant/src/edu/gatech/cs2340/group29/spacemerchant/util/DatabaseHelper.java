/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * The Class DatabaseHelper.
 */
public class DatabaseHelper extends SQLiteOpenHelper
{
    
    /**
     * Instantiates a new database helper.
     *
     * @param context the Context
     */
    public DatabaseHelper( Context context )
    {
        super( context, "spaceMerchant", null, 1 );
        
    }
    
    /** 
     *
     * Override:
     * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
     */
    @Override
    public void onCreate( SQLiteDatabase db )
    {
        final String createShipTableQuery = "" + "create table tb_ship " + "( "
                + "   ship integer primary key autoincrement," + "   fuselage integer not null,"
                + "   cabin integer not null," + "   boosters integer not null" + ")";
        
        db.execSQL( createShipTableQuery );
        
        final String createPlayerTableQuery = "" + "create table tb_player( "
                + "    player integer primary key autoincrement, " 
                + "    name string not null,"
                + "    money integer not null, " 
                + "    pilotSkillPoints integer not null, "
                + "    fighterSkillPoints integer not null," 
                + "    traderSkillPoints integer not null,"
                + "    engineerSkillPoints integer not null, " 
                + "    head integer not null,"
                + "    body integer not null," 
                + "    legs integer not null," 
                + "    feet integer not null,"
                + "    ship integer not null" 
                + " )";
        
        db.execSQL( createPlayerTableQuery );
        
        final String createGameTableQuery = "" + "create table tb_game( "
                + "    game integer primary key autoincrement, " + "    difficultyLevel integer not null,"
                + "    player integer not null" + " )";
        
        db.execSQL( createGameTableQuery );
        
    }
    
    /** 
     *
     * Override:
     * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
     */
    @Override
    public void onUpgrade( SQLiteDatabase arg0, int arg1, int arg2 )
    {
        
    }
    
}

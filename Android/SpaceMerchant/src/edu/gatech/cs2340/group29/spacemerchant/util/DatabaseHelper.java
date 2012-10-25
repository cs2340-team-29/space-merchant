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
        
        final String createGameTableQuery = "" 
            + "create table tb_game( "
            + "    game integer primary key autoincrement, " 
            + "    difficulty integer not null,"
            + "    planet integer not null, "
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
            + "    fuselage integer not null,"
            + "    cabin integer not null," 
            + "    boosters integer not null" 
            + " )";
        
        db.execSQL( createGameTableQuery );

        final String createPlanetTableQuery = ""
            + "create table tb_planet( "
            + "     planet integer primary key autoincrement, "
            + "     game integer null, "
            + "     techLevel integer not null, "
            + "     resourceType integer not null, "
            + "     name string not null, "
            + "     xCoord int not null, "
            + "     yCoord int not null, "
            + "     money int not null, "
            + "     base int not null, "
            + "     land int not null, "
            + " 	cloud int not null "
            + " )";

        db.execSQL( createPlanetTableQuery );
       
        final String createItemTableQuery = ""
            + "create table tb_item( "
            + "     item integer primary key autoincrement, "
            + "     game integer not null, "
            + "     type integer not null, " 
            + "     name string not null, " 
            + "     drawable integer not null, "
            + "     techLevel integer not null "
            + " )";
        
        db.execSQL( createItemTableQuery );
        
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

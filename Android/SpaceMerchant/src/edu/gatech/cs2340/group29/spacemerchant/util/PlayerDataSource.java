package edu.gatech.cs2340.group29.spacemerchant.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteException;

public class PlayerDataSource
{
    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;
    
    public PlayerDataSource(Context context)
    {
        databaseHelper = new DatabaseHelper(context);
    }
    
    public void open() throws SQLiteException
    {
        database = databaseHelper.getWritableDatabase();
    }
   
    public void close()
    {
        databaseHelper.close();
    }
    
    public Player createPlayer(String name, 
    {
        
    }
    
    public void 
    
    
}

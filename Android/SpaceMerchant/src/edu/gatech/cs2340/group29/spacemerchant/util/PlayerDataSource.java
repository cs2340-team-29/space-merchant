
package edu.gatech.cs2340.group29.spacemerchant.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import edu.gatech.cs2340.group29.spacemerchant.model.*;

public class PlayerDataSource
{
    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;
    
    public PlayerDataSource( Context context )
    {
        databaseHelper = new DatabaseHelper( context );
    }
    
    public void open() throws SQLiteException
    {
        database = databaseHelper.getWritableDatabase();
    }
    
    public void close()
    {
        databaseHelper.close();
    }
    
    public void createPlayer(Player player) 
    {
        ContentValues values = new ContentValues();
       
        String name = player.getName();
        int money = player.getMoney();
        int[] stats = player.getStats();
        
        values.put("name", name);
        values.put("money", money);
        values.put("pilotSkillPoints", stats[0]);
        values.put("fighterSkillPoints", stats[1]);
        values.put("traderSkillPoints", stats[2]);
        values.put("engineerSkillPoints", stats[3]);
        
        long playerID = database.insert("tb_player", null, values);

        player.setID(playerID);
        return;
    }
    
    public void deletePlayer(Player player)
    {
        long playerID = player.getID();
        
        database.delete("tb_player", "player=" + playerID, null);
    }
    
}

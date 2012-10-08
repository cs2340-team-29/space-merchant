package edu.gatech.cs2340.group29.spacemerchant.model;

import edu.gatech.cs2340.group29.spacemerchant.util.DatabaseHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
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

    public Player createPlayer(String name, Integer pilotSkillPoints,
            Integer fighterSkillPoints, Integer traderSkillPoints,
            Integer engineerSkillPoints)
    {

        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("pilotSkillPoints", pilotSkillPoints);
        values.put("fighterSkillPoints", fighterSkillPoints);
        values.put("traderSkillPoints", traderSkillPoints);
        values.put("engineerSkillPoints", engineerSkillPoints);
        
        database.insert("tb_player", null, values);
        return null;

    }

}

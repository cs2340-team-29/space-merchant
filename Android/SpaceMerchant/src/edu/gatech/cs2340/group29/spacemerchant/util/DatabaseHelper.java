package edu.gatech.cs2340.group29.spacemerchant.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper
{
       
		public DatabaseHelper(Context context)
		{			
			super(context, "spaceMerchant" , null, 1);
			
		}

	
		public void onCreate(SQLiteDatabase db) 
		{
			final String createGameTableQuery = ""                     
				+ "create table tb_game( "
			    + "    game integer primary key autoincrement, "
			    + "    difficultyLevel integer not null"
			    + "    currentPlanet integer not null"
			    + " )";
			
			db.execSQL(createGameTableQuery);
			
			final String createPlayerTableQuery = ""                     
				+ "create table tb_player( "
			    + "    player integer primary key autoincrement, "
				+ "    name string not null," 
			    + "    pilotSkillPoints integer not null, "
			    + "    fighterSkillPoints integer not null,"
			    + "    traderSkillPoints integer not null," 
			    + "    engineerSkillPoints integer not null, "
			    + " )";
			
			db.execSQL(createPlayerTableQuery);
			
		}


		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) 
		{
	
			
		}
		
	
}


package edu.gatech.cs2340.group29.spacemerchant.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import edu.gatech.cs2340.group29.spacemerchant.R;
import edu.gatech.cs2340.group29.spacemerchant.adapter.TradingItemsAdapter;
import edu.gatech.cs2340.group29.spacemerchant.model.Game;
import edu.gatech.cs2340.group29.spacemerchant.model.Player;
import edu.gatech.cs2340.group29.spacemerchant.model.StatGroup.Stat;
import edu.gatech.cs2340.group29.spacemerchant.util.GameDataSource;

public class InventoryActivity extends Activity
{
    public static final String GAME_ID = "GAME_ID_EXTRA";
    
    private Game               game;
    private Player             player;
    private ListView           inventory;
    
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_inventory );
        
        Intent i = getIntent();
        long gameID = i.getLongExtra( GAME_ID, -1 );
        
        GameDataSource gds = new GameDataSource( getApplicationContext() );
        gds.open();
        game = gds.getGameByID( gameID );
        player = game.getPlayer();
        gds.close();
        
        ( ( TextView ) findViewById( R.id.pilot ) ).setText( "Pilot: " + player.getStats().get( Stat.PILOT )
                + " / 16" );
        ( ( TextView ) findViewById( R.id.fighter ) ).setText( "Fighter: "
                + player.getStats().get( Stat.FIGHTER ) + " / 16" );
        ( ( TextView ) findViewById( R.id.trader ) ).setText( "Trader: "
                + player.getStats().get( Stat.TRADER ) + " / 16" );
        ( ( TextView ) findViewById( R.id.engineer ) ).setText( "Engineer: "
                + player.getStats().get( Stat.ENGINEER ) + " / 16" );
        
        TradingItemsAdapter inventoryAdapter = new TradingItemsAdapter( getApplicationContext(),
                R.layout.trading_item_row, player.getInventory() );
        inventory = ( ListView ) this.findViewById( R.id.inventory );
        inventory.setAdapter( inventoryAdapter );
    }
}

/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import edu.gatech.cs2340.group29.spacemerchant.R;
import edu.gatech.cs2340.group29.spacemerchant.adapter.InventoryAdapter;
import edu.gatech.cs2340.group29.spacemerchant.model.Game;
import edu.gatech.cs2340.group29.spacemerchant.model.Player;
import edu.gatech.cs2340.group29.spacemerchant.model.StatGroup.Stat;
import edu.gatech.cs2340.group29.spacemerchant.util.GameDataSource;

/**
 * The Class InventoryActivity.
 */
public class InventoryActivity extends Activity
{
    
    /** The Constant GAME_ID. */
    public static final String GAME_ID = "GAME_ID_EXTRA";
    
    /** The game. */
    private Game               game;
    
    /** The player. */
    private Player             player;
    
    /** The inventory. */
    private ListView           inventory;
    
    /**
     * Override:
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate( final Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        this.setContentView( R.layout.activity_inventory );
        
        final Intent i = this.getIntent();
        final long gameID = i.getLongExtra( InventoryActivity.GAME_ID, -1 );
        
        final GameDataSource gds = new GameDataSource( this.getApplicationContext() );
        try
        {
            gds.open();
            this.game = gds.getGameByID( gameID );
            this.player = this.game.getPlayer();
        }
        finally
        {
            gds.close();
        }
        
        ( ( TextView ) this.findViewById( R.id.pilot ) ).setText( "Pilot: "
                + this.player.getStats().get( Stat.PILOT ) + " / 16" );
        ( ( TextView ) this.findViewById( R.id.fighter ) ).setText( "Fighter: "
                + this.player.getStats().get( Stat.FIGHTER ) + " / 16" );
        ( ( TextView ) this.findViewById( R.id.trader ) ).setText( "Trader: "
                + this.player.getStats().get( Stat.TRADER ) + " / 16" );
        ( ( TextView ) this.findViewById( R.id.engineer ) ).setText( "Engineer: "
                + this.player.getStats().get( Stat.ENGINEER ) + " / 16" );
        ( ( TextView ) this.findViewById( R.id.money ) ).setText( "$" + this.player.getMoney() + " -- "
                + this.player.getInventory().size() + "/" + this.player.getInventory().capacity() );
        
        final InventoryAdapter inventoryAdapter = new InventoryAdapter( this, R.layout.trading_item_row,
                this.player );
        this.inventory = ( ListView ) this.findViewById( R.id.inventory );
        this.inventory.setAdapter( inventoryAdapter );
    }
}

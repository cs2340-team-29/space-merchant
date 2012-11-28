/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import edu.gatech.cs2340.group29.spacemerchant.R;
import edu.gatech.cs2340.group29.spacemerchant.model.Game;
import edu.gatech.cs2340.group29.spacemerchant.model.Inventory;
import edu.gatech.cs2340.group29.spacemerchant.model.Item;
import edu.gatech.cs2340.group29.spacemerchant.model.Planet;
import edu.gatech.cs2340.group29.spacemerchant.model.Player;
import edu.gatech.cs2340.group29.spacemerchant.model.Ship;
import edu.gatech.cs2340.group29.spacemerchant.model.StatGroup.Stat;
import edu.gatech.cs2340.group29.spacemerchant.util.GameDataSource;

/**
 * The Class GameActivity.
 */
public class GameActivity extends Activity implements OnClickListener
{
    
    /** The Constant GAME_ID_EXTRA. */
    public static final String GAME_ID_EXTRA      = "GAME_ID_EXTRA";
    
    /** The Constant HELP_OVERLAY_EXTRA. */
    public static final String HELP_OVERLAY_EXTRA = "HELP_OVERLAY_EXTRA";
    
    /** The Constant PIRATE_EXTRA. */
    public static final String PIRATE_EXTRA       = "PIRATE_EXTRA";
    
    /** The show help overlay. */
    private boolean            showHelpOverlay;
    
    /** The pirate event. */
    private boolean            pirateEvent;
    
    /** The v. */
    private View               v;
    
    /** The game. */
    Game                       game;
    
    /** The player. */
    Player                     player;
    
    /** The ship. */
    Ship                       ship;
    
    /** The planet. */
    Planet                     planet;
    
    /**
     * Override:
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate( final Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        this.setContentView( R.layout.activity_game );
        
        final Intent i = this.getIntent();
        final long gameID = i.getLongExtra( GameActivity.GAME_ID_EXTRA, -1 );
        this.showHelpOverlay = i.getBooleanExtra( GameActivity.HELP_OVERLAY_EXTRA, false );
        this.pirateEvent = i.getBooleanExtra( GameActivity.PIRATE_EXTRA, false );
        final GameDataSource gds = new GameDataSource( this.getApplicationContext() );
        try
        {
            gds.open();
            this.game = gds.getGameByID( gameID );
            this.player = this.game.getPlayer();
            this.ship = this.player.getShip();
            this.planet = this.game.getPlanet();
        }
        finally
        {
            gds.close();
        }
        
        // Setting images for player and ship
        ( ( ImageView ) this.findViewById( R.id.head ) ).setImageResource( this.player.getHead() );
        ( ( ImageView ) this.findViewById( R.id.body_player ) ).setImageResource( this.player.getBody() );
        ( ( ImageView ) this.findViewById( R.id.legs ) ).setImageResource( this.player.getLegs() );
        ( ( ImageView ) this.findViewById( R.id.feet ) ).setImageResource( this.player.getFeet() );
        ( ( ImageView ) this.findViewById( R.id.cabin ) ).setImageResource( this.ship.getCabin() );
        ( ( ImageView ) this.findViewById( R.id.fuselage ) ).setImageResource( this.ship.getFuselage() );
        ( ( ImageView ) this.findViewById( R.id.boosters ) ).setImageResource( this.ship.getBoosters() );
        ( ( ImageView ) this.findViewById( R.id.planetBase ) ).setImageResource( this.planet.getBase() );
        ( ( ImageView ) this.findViewById( R.id.planetLand ) ).setImageResource( this.planet.getLand() );
        ( ( ImageView ) this.findViewById( R.id.planetCloud ) ).setImageResource( this.planet.getCloud() );
        
        final Resources res = this.getResources();
        final String[] techLevels = res.getStringArray( R.array.TechLevels );
        final String[] resourceTypes = res.getStringArray( R.array.ResourceTypes );
        
        // set up planet image and info
        ( ( TextView ) this.findViewById( R.id.planetInfo ) ).setText( "Name: " + this.planet.getName()
                + "\nTech Level: " + techLevels[this.planet.getTechLevel()] + "\nResources: "
                + resourceTypes[this.planet.getResourceType() + 5] );
        
        this.v = this.findViewById( R.id.helpOverlay );
        this.showHelpOverlay();
        this.pirateEvent();
    }
    
    /**
     * Override:
     * 
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu( final Menu menu )
    {
        this.getMenuInflater().inflate( R.menu.activity_game, menu );
        return true;
    }
    
    /**
     * Override:
     * 
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected( final MenuItem item )
    {
        switch ( item.getItemId() )
        {
            case R.id.menu_travel :
                this.gotoTravelActivity( null );
                return super.onOptionsItemSelected( item );
            case R.id.menu_help :
                this.showHelpOverlay = true;
                this.showHelpOverlay();
                return super.onOptionsItemSelected( item );
            default :
                return super.onOptionsItemSelected( item );
        }
    }
    
    /**
     * Show help overlay.
     */
    private void showHelpOverlay()
    {
        if ( this.showHelpOverlay )
        {
            this.v.setVisibility( View.VISIBLE );
            this.v.invalidate();
        }
    }
    
    /**
     * Pirate event.
     */
    private void pirateEvent()
    {
        if ( this.pirateEvent )
        {
            final List<Item> items = new ArrayList<Item>();
            final Inventory inv = this.player.getInventory();
            final List<Item>[] inventoryItems = inv.getContents();
            
            for ( final List<Item> inventoryItemsByType : inventoryItems )
            {
                
                for ( final Item item : inventoryItemsByType )
                {
                    items.add( item );
                }
            }
            final Random r = new Random();
            int amount = r.nextInt( 10 );
            amount -= this.player.getStats().get( Stat.FIGHTER ) / 3;
            if ( ( amount > 0 ) && ( items.size() > amount ) )
            {
                for ( int i = 0; i < amount; i++ )
                {
                    final int it = r.nextInt( items.size() );
                    this.player.getInventory().remove( items.get( it ) );
                }
                this.game.setPlayer( this.player );
                Toast.makeText( this.getApplicationContext(), "A Pirate stole items from you!",
                        Toast.LENGTH_LONG ).show();
            }
        }
    }
    
    /**
     * Goes to the inventory.
     * 
     * @param v
     *        the View
     */
    public void gotoInventory( final View v )
    {
        final GameDataSource gds = new GameDataSource( this.getApplicationContext() );
        try
        {
            gds.open();
            gds.updateGame( this.game );
        }
        finally
        {
            gds.close();
        }
        final Intent intent = new Intent( GameActivity.this, InventoryActivity.class );
        intent.putExtra( InventoryActivity.GAME_ID, this.game.getGameID() );
        GameActivity.this.startActivity( intent );
    }
    
    /**
     * Goes to the trading.
     * 
     * @param v
     *        the View
     */
    public void gotoTrading( final View v )
    {
        final GameDataSource gds = new GameDataSource( this.getApplicationContext() );
        try
        {
            gds.open();
            gds.updateGame( this.game );
        }
        finally
        {
            gds.close();
        }
        final Intent intent = new Intent( GameActivity.this, TradeActivity.class );
        intent.putExtra( TradeActivity.GAME_ID, this.game.getGameID() );
        GameActivity.this.startActivity( intent );
    }
    
    /**
     * Goes to the travel activity.
     * 
     * @param v
     *        the View
     */
    public void gotoTravelActivity( final View v )
    {
        final GameDataSource gds = new GameDataSource( this.getApplicationContext() );
        try
        {
            gds.open();
            gds.updateGame( this.game );
        }
        finally
        {
            gds.close();
        }
        final Intent intent = new Intent( GameActivity.this, TravelActivity.class );
        intent.putExtra( TravelActivity.GAME_ID, this.game.getGameID() );
        GameActivity.this.startActivity( intent );
    }
    
    /**
     * Override:
     * 
     * @see android.app.Activity#onBackPressed()
     */
    @Override
    public void onBackPressed()
    {
        this.gotoSelectGame( null );
    }
    
    /**
     * Goes to the select game.
     * 
     * @param view
     *        the View
     */
    public void gotoSelectGame( final View view )
    {
        // launch SelectGame activity
        final Intent selectGameIntent = new Intent( GameActivity.this, SelectGame.class );
        GameActivity.this.startActivity( selectGameIntent );
    }
    
    /**
     * Override:
     * 
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    public void onClick( final View v )
    {
        this.removeHelpOverlay( null );
    }
    
    /**
     * Removes the help overlay.
     * 
     * @param v
     *        the View
     */
    public void removeHelpOverlay( final View v )
    {
        v.setVisibility( View.GONE );
        this.showHelpOverlay = false;
        v.invalidate();
    }
}

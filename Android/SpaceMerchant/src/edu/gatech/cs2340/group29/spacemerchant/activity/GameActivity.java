/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.activity;

import java.util.ArrayList;
import java.util.LinkedList;
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
    public static final String GAME_ID_EXTRA      = "GAME_ID_EXTRA";
    public static final String HELP_OVERLAY_EXTRA = "HELP_OVERLAY_EXTRA";
    public static final String PIRATE_EXTRA       = "PIRATE_EXTRA";
    
    private boolean            showHelpOverlay;
    private boolean            pirateEvent;
    private View               v;
    
    Game                       game;
    Player                     player;
    Ship                       ship;
    Planet                     planet;
    
    /**
     * Override:
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_game );
        
        Intent i = getIntent();
        long gameID = i.getLongExtra( GAME_ID_EXTRA, -1 );
        showHelpOverlay = i.getBooleanExtra( HELP_OVERLAY_EXTRA, false );
        pirateEvent = i.getBooleanExtra( PIRATE_EXTRA, false );
        
        GameDataSource gds = new GameDataSource( getApplicationContext() );
        gds.open();
        game = gds.getGameByID( gameID );
        player = game.getPlayer();
        ship = player.getShip();
        planet = game.getPlanet();
        gds.close();
        
        // Setting images for player and ship
        ( ( ImageView ) findViewById( R.id.head ) ).setImageResource( player.getHead() );
        ( ( ImageView ) findViewById( R.id.body_player ) ).setImageResource( player.getBody() );
        ( ( ImageView ) findViewById( R.id.legs ) ).setImageResource( player.getLegs() );
        ( ( ImageView ) findViewById( R.id.feet ) ).setImageResource( player.getFeet() );
        ( ( ImageView ) findViewById( R.id.cabin ) ).setImageResource( ship.getCabin() );
        ( ( ImageView ) findViewById( R.id.fuselage ) ).setImageResource( ship.getFuselage() );
        ( ( ImageView ) findViewById( R.id.boosters ) ).setImageResource( ship.getBoosters() );
        ( ( ImageView ) findViewById( R.id.planetBase ) ).setImageResource( planet.getBase() );
        ( ( ImageView ) findViewById( R.id.planetLand ) ).setImageResource( planet.getLand() );
        ( ( ImageView ) findViewById( R.id.planetCloud ) ).setImageResource( planet.getCloud() );
        
        Resources res = getResources();
        String[] techLevels = res.getStringArray( R.array.TechLevels );
        String[] resourceTypes = res.getStringArray( R.array.ResourceTypes );
        
        // set up planet image and info
        ( ( TextView ) findViewById( R.id.planetInfo ) ).setText( "Name: " + planet.getName()
                + "\nTech Level: " + techLevels[planet.getTechLevel()] + "\nResources: "
                + resourceTypes[planet.getResourceType() + 5] );
        
        v = findViewById( R.id.helpOverlay );
        showHelpOverlay();
        pirateEvent();
    }
    
    /**
     * Override:
     * 
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        getMenuInflater().inflate( R.menu.activity_game, menu );
        return true;
    }
    
    /**
     * Override:
     * 
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        switch ( item.getItemId() )
        {
            case R.id.menu_travel :
                gotoTravelActivity( null );
                return super.onOptionsItemSelected( item );
            case R.id.menu_help :
                showHelpOverlay = true;
                showHelpOverlay();
                return super.onOptionsItemSelected( item );
            default :
                return super.onOptionsItemSelected( item );
        }
    }
    
    private void showHelpOverlay()
    {
        if ( showHelpOverlay )
        {
            v.setVisibility( View.VISIBLE );
            v.invalidate();
        }
    }
    
    private void pirateEvent()
    {
        if ( pirateEvent )
        {
            ArrayList<Item> items = new ArrayList<Item>();
            Inventory inv = player.getInventory();
            LinkedList<Item>[] inventoryItems = inv.getContents();
            
            for ( LinkedList<Item> inventoryItemsByType : inventoryItems )
            {
                
                for ( Item item : inventoryItemsByType )
                {
                    items.add( item );
                }
            }
            Random r = new Random();
            int amount = r.nextInt(10);
            amount -= player.getStats().get( Stat.FIGHTER ) / 3;
            if ( amount > 0 && items.size() > amount )
            {
                for ( int i = 0; i < amount; i++ )
                {
                    int it = r.nextInt( items.size() );
                    player.getInventory().remove( items.get( it ) );
                }
                game.setPlayer( player );
                Toast.makeText( this.getApplicationContext(), "A Pirate stole items from you!",
                        Toast.LENGTH_LONG ).show();
            }
        }
    }
    
    /**
     * Goes to the player info.
     * 
     * @param v
     *            the View
     */
    public void gotoInventory( View v )
    {
        GameDataSource gds = new GameDataSource( getApplicationContext() );
        gds.open();
        gds.updateGame( game );
        gds.close();
        Intent intent = new Intent( GameActivity.this, InventoryActivity.class );
        intent.putExtra( InventoryActivity.GAME_ID, game.getGameID() );
        GameActivity.this.startActivity( intent );
    }
    
    /**
     * Goes to the trading.
     * 
     * @param v
     *            the View
     */
    public void gotoTrading( View v )
    {
        GameDataSource gds = new GameDataSource( getApplicationContext() );
        gds.open();
        gds.updateGame( game );
        gds.close();
        Intent intent = new Intent( GameActivity.this, TradeActivity.class );
        intent.putExtra( TradeActivity.GAME_ID, game.getGameID() );
        GameActivity.this.startActivity( intent );
    }
    
    /**
     * Goes to the travel activity.
     * 
     * @param v
     *            the View
     */
    public void gotoTravelActivity( View v )
    {
        GameDataSource gds = new GameDataSource( getApplicationContext() );
        gds.open();
        gds.updateGame( game );
        gds.close();
        Intent intent = new Intent( GameActivity.this, TravelActivity.class );
        intent.putExtra( TravelActivity.GAME_ID, game.getGameID() );
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
        gotoSelectGame( null );
    }
    
    /**
     * Goes to the select game.
     * 
     * @param view
     *            the View
     */
    public void gotoSelectGame( View view )
    {
        // launch SelectGame activity
        Intent selectGameIntent = new Intent( GameActivity.this, SelectGame.class );
        GameActivity.this.startActivity( selectGameIntent );
    }
    
    /**
     * Override:
     * 
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    public void onClick( View v )
    {
        removeHelpOverlay( null );
    }
    
    /**
     * Removes the help overlay.
     * 
     * @param v
     *            the View
     */
    public void removeHelpOverlay( View v )
    {
        v.setVisibility( View.GONE );
        showHelpOverlay = false;
        v.invalidate();
    }
}

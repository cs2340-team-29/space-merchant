/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import edu.gatech.cs2340.group29.spacemerchant.R;
import edu.gatech.cs2340.group29.spacemerchant.model.Game;
import edu.gatech.cs2340.group29.spacemerchant.model.Planet;
import edu.gatech.cs2340.group29.spacemerchant.model.Player;
import edu.gatech.cs2340.group29.spacemerchant.model.Ship;
import edu.gatech.cs2340.group29.spacemerchant.util.GameDataSource;

/**
 * The Class GameActivity.
 */
public class GameActivity extends Activity
{
    public static final String GAME_ID_EXTRA = "GAME_ID_EXTRA";
    
    private boolean            showHelpOverlay;
    
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
        
        GameDataSource gds = new GameDataSource( getApplicationContext() );
        gds.open();
        game = gds.getGameByID( gameID );
        player = game.getPlayer();
        showHelpOverlay = player.isJustCreated();
        ship = player.getShip();
        planet = game.getPlanet();
        gds.close();
        
        System.out.println( player.getInventory().capacity() );
        
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
        
        if ( showHelpOverlay )
        {
            // show overlay
        }
        
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
            case R.id.menu_help :
                // load the help overlay
                return true;
            default :
                return super.onOptionsItemSelected( item );
        }
    }
    
    /**
     * Goes to the player info.
     * 
     * @param v
     *            the View
     */
    public void gotoPlayerInfo( View v )
    {
        // do stuff later!
    }
    
    /**
     * Goes to the trading.
     * 
     * @param v
     *            the View
     */
    public void gotoTrading( View v )
    {
        Intent intent = new Intent( GameActivity.this, TradeActivity.class );
        intent.putExtra( TradeActivity.GAME_ID, game.getGameID() );
        GameActivity.this.startActivity( intent );
    }
    
    public void gotoTravelActivity( View v )
    {
        Intent intent = new Intent( GameActivity.this, TravelActivity.class );
        intent.putExtra( TravelActivity.GAME_ID, game.getGameID() );
        GameActivity.this.startActivity( intent );
    }
    
    @Override
    protected void onStop()
    {
        GameDataSource gds = new GameDataSource( getApplicationContext() );
        gds.open();
        gds.updateGame( game );
        gds.close();
        super.onStop();
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
}

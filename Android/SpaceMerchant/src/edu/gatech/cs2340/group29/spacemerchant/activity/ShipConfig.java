/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Gallery;
import android.widget.Toast;
import edu.gatech.cs2340.group29.spacemerchant.R;
import edu.gatech.cs2340.group29.spacemerchant.adapter.SelectGalleryAdapter;
import edu.gatech.cs2340.group29.spacemerchant.model.Game;
import edu.gatech.cs2340.group29.spacemerchant.model.Player;
import edu.gatech.cs2340.group29.spacemerchant.model.Ship;
import edu.gatech.cs2340.group29.spacemerchant.util.GameDataSource;

/**
 * The Class ShipConfig.
 */
public class ShipConfig extends Activity
{
    public static final String player_extra     = "PLAYER_EXTRA";
    public static final String difficulty_extra = "DIFFICULTY_EXTRA";
    
    private Player             player;
    private int                difficulty;
    
    /**
     * Override:
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        
        player = ( Player ) getIntent().getParcelableExtra( player_extra );
        difficulty = getIntent().getIntExtra( difficulty_extra, -1 );
        
        if ( difficulty <= 0 )
        {
            Toast.makeText(
                    this.getApplicationContext(),
                    "There was a problem retrieving your selected difficulty, your difficulty has been set to Medium.",
                    Toast.LENGTH_LONG ).show();
            difficulty = 3;
        }
        
        setContentView( R.layout.activity_ship_config );
        
        // Set up drawable lists
        ArrayList<Integer> fuselages = new ArrayList<Integer>();
        fuselages.add( R.drawable.ic_fuselage_1 );
        fuselages.add( R.drawable.ic_fuselage_2 );
        fuselages.add( R.drawable.ic_fuselage_3 );
        
        ArrayList<Integer> cabins = new ArrayList<Integer>();
        cabins.add( R.drawable.ic_cabin_1 );
        cabins.add( R.drawable.ic_cabin_2 );
        cabins.add( R.drawable.ic_cabin_3 );
        
        ArrayList<Integer> boosters = new ArrayList<Integer>();
        boosters.add( R.drawable.ic_boosters_1 );
        boosters.add( R.drawable.ic_boosters_2 );
        boosters.add( R.drawable.ic_boosters_3 );
        
        // Set up Galleries
        SelectGalleryAdapter sgaFuselage = new SelectGalleryAdapter( this, R.layout.gallery_row_view,
                fuselages );
        SelectGalleryAdapter sgaCabin = new SelectGalleryAdapter( this, R.layout.gallery_row_view, cabins );
        SelectGalleryAdapter sgaBooster = new SelectGalleryAdapter( this, R.layout.gallery_row_view, boosters );
        
        ( ( Gallery ) findViewById( R.id.galleryFuselage ) ).setAdapter( sgaFuselage );
        ( ( Gallery ) findViewById( R.id.galleryCabin ) ).setAdapter( sgaCabin );
        ( ( Gallery ) findViewById( R.id.galleryBoosters ) ).setAdapter( sgaBooster );
        
    }
    
    /**
     * Done button clicked.
     * 
     * @param v
     *            the View
     */
    public void doneButtonClicked( View v )
    {
        // do stuff here, send to main screen, save game, etc...
        
        GameDataSource gds = new GameDataSource( getApplicationContext() );
        gds.open();
        
        Game g = new Game( getApplicationContext() );
        Ship s = new Ship();
        player.setShip( s );
        g.setDifficulty( difficulty );
        g.setPlayer( player );
        long gameID = gds.createGame( g );
        gds.close();
        
        Intent intent = new Intent( ShipConfig.this, GameActivity.class );
        intent.putExtra( GameActivity.GAME_ID_EXTRA, gameID );
        this.startActivity( intent );
    }
}

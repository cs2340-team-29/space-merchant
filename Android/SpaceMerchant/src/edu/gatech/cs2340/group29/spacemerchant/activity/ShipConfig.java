/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
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
    
    /** The Constant PLAYER_EXTRA. */
    public static final String     PLAYER_EXTRA     = "PLAYER_EXTRA";
    
    /** The Constant DIFFICULTY_EXTRA. */
    public static final String     DIFFICULTY_EXTRA = "DIFFICULTY_EXTRA";
    
    /** The player. */
    private Player                 player;
    
    /** The difficulty. */
    private int                    difficulty;
    
    /** The working. */
    protected Dialog               working;
    
    /** The s. */
    protected Ship                 s;
    
    /** The cabins. */
    protected Gallery              cabins;
    
    /** The fuselages. */
    protected Gallery              fuselages;
    
    /** The boosters. */
    protected Gallery              boosters;
    
    /** The sga fuselage. */
    protected SelectGalleryAdapter sgaFuselage;
    
    /** The sga cabin. */
    protected SelectGalleryAdapter sgaCabin;
    
    /** The sga booster. */
    protected SelectGalleryAdapter sgaBooster;
    
    /**
     * Override:
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate( final Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        
        this.player = ( Player ) this.getIntent().getParcelableExtra( ShipConfig.PLAYER_EXTRA );
        this.difficulty = this.getIntent().getIntExtra( ShipConfig.DIFFICULTY_EXTRA, -1 );
        
        if ( ( this.difficulty <= 0 ) || ( this.difficulty >= 5 ) )
        {
            Toast.makeText(
                    this.getApplicationContext(),
                    "There was a problem retrieving your selected difficulty, your difficulty has been set to Medium.",
                    Toast.LENGTH_LONG ).show();
            this.difficulty = 3;
        }
        
        this.setContentView( R.layout.activity_ship_config );
        
        // Set up drawable lists
        final ArrayList<Integer> fuselages = new ArrayList<Integer>();
        fuselages.add( R.drawable.ic_fuselage_1 );
        fuselages.add( R.drawable.ic_fuselage_2 );
        fuselages.add( R.drawable.ic_fuselage_3 );
        
        final ArrayList<Integer> cabins = new ArrayList<Integer>();
        cabins.add( R.drawable.ic_cabin_1 );
        cabins.add( R.drawable.ic_cabin_2 );
        cabins.add( R.drawable.ic_cabin_3 );
        
        final ArrayList<Integer> boosters = new ArrayList<Integer>();
        boosters.add( R.drawable.ic_boosters_1 );
        boosters.add( R.drawable.ic_boosters_2 );
        boosters.add( R.drawable.ic_boosters_3 );
        
        // Set up Galleries
        this.sgaFuselage = new SelectGalleryAdapter( this, R.layout.gallery_row_view, fuselages );
        this.sgaCabin = new SelectGalleryAdapter( this, R.layout.gallery_row_view, cabins );
        this.sgaBooster = new SelectGalleryAdapter( this, R.layout.gallery_row_view, boosters );
        
        this.fuselages = ( ( Gallery ) this.findViewById( R.id.galleryFuselage ) );
        this.fuselages.setAdapter( this.sgaFuselage );
        this.cabins = ( ( Gallery ) this.findViewById( R.id.galleryCabin ) );
        this.cabins.setAdapter( this.sgaCabin );
        this.boosters = ( ( Gallery ) this.findViewById( R.id.galleryBoosters ) );
        this.boosters.setAdapter( this.sgaBooster );
        
    }
    
    /**
     * Done button clicked.
     * 
     * @param v
     *        the View
     */
    public void doneButtonClicked( final View v )
    {
        // do stuff here, send to main screen, save game, etc...
        this.working = new Dialog( this );
        this.working.setContentView( R.layout.loading_view );
        this.working.setTitle( "Working" );
        this.working.show();
        
        final CreateUniverseTask cut = new CreateUniverseTask();
        cut.execute( ( Void ) null );
        
    }
    
    /**
     * The Class CreateUniverseTask.
     */
    public class CreateUniverseTask extends AsyncTask<Void, Void, Long>
    {
        
        /**
         * Override:
         * 
         * @see android.os.AsyncTask#onPreExecute()
         */
        @Override
        protected void onPreExecute()
        {
            ShipConfig.this.s = new Ship();
            ShipConfig.this.s.setCabin( ShipConfig.this.sgaCabin.getItemAtPosition( ShipConfig.this.cabins
                    .getSelectedItemPosition() ) );
            ShipConfig.this.s.setFuselage( ShipConfig.this.sgaFuselage
                    .getItemAtPosition( ShipConfig.this.fuselages.getSelectedItemPosition() ) );
            ShipConfig.this.s.setBoosters( ShipConfig.this.sgaBooster
                    .getItemAtPosition( ShipConfig.this.boosters.getSelectedItemPosition() ) );
            
            super.onPreExecute();
        }
        
        /**
         * Override:
         * 
         * @see android.os.AsyncTask#doInBackground(Params[])
         */
        @Override
        protected Long doInBackground( final Void ... params )
        {
            final GameDataSource gds = new GameDataSource( ShipConfig.this.getApplicationContext() );
            final long gameID;
            try
            {
                gds.open();
                
                final Game g = new Game( ShipConfig.this.getApplicationContext() );
                ShipConfig.this.player.setShip( ShipConfig.this.s );
                g.setDifficulty( ShipConfig.this.difficulty );
                g.setPlayer( ShipConfig.this.player );
                gameID = gds.createGame( g );
            }
            finally
            {
                gds.close();
            }
            return gameID;
        }
        
        /**
         * Override:
         * 
         * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
         */
        @Override
        protected void onPostExecute( final Long gameID )
        {
            ShipConfig.this.working.dismiss();
            
            final Intent intent = new Intent( ShipConfig.this, GameActivity.class );
            intent.putExtra( GameActivity.GAME_ID_EXTRA, gameID );
            intent.putExtra( GameActivity.HELP_OVERLAY_EXTRA, true );
            ShipConfig.this.startActivity( intent );
        }
    }
}

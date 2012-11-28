/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import edu.gatech.cs2340.group29.spacemerchant.R;
import edu.gatech.cs2340.group29.spacemerchant.adapter.SelectGalleryAdapter;
import edu.gatech.cs2340.group29.spacemerchant.model.Inventory;
import edu.gatech.cs2340.group29.spacemerchant.model.Player;

/**
 * The Class PlayerConfig.
 */
public class PlayerConfig extends Activity
{
    
    /** The Constant MAX_STARTING_MONEY. */
    private static final int MAX_STARTING_MONEY   = 4000;
    
    /** The Constant STARTING_STAT_POINTS. */
    private static final int STARTING_STAT_POINTS = 16;
    
    /**
     * The Enum Difficulty.
     */
    public enum Difficulty
    {
        
        /** The easy. */
        EASY,
        /** The medium. */
        MEDIUM,
        /** The hard. */
        HARD,
        /** The impossible. */
        IMPOSSIBLE
    }
    
    /** The stat points. */
    public int       statPoints = 0;
    
    /** The player. */
    private Player   player;
    
    /** The stats. */
    private int[]    stats;
    
    /** The player name. */
    private EditText playerName;
    
    /** The stat pilot bar. */
    private SeekBar  statPilotBar;
    
    /** The stat fighter bar. */
    private SeekBar  statFighterBar;
    
    /** The stat trader bar. */
    private SeekBar  statTraderBar;
    
    /** The stat engineer bar. */
    private SeekBar  statEngineerBar;
    
    /**
     * Override:
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate( final Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        this.setContentView( R.layout.activity_player_config );
        
        // gets the views that determine the values
        this.playerName = ( EditText ) this.findViewById( R.id.playerName );
        this.statPilotBar = ( SeekBar ) this.findViewById( R.id.stat1 );
        this.statFighterBar = ( SeekBar ) this.findViewById( R.id.stat2 );
        this.statTraderBar = ( SeekBar ) this.findViewById( R.id.stat3 );
        this.statEngineerBar = ( SeekBar ) this.findViewById( R.id.stat4 );
        
        this.statPilotBar.setOnSeekBarChangeListener( new SeekBarListener() );
        this.statFighterBar.setOnSeekBarChangeListener( new SeekBarListener() );
        this.statTraderBar.setOnSeekBarChangeListener( new SeekBarListener() );
        this.statEngineerBar.setOnSeekBarChangeListener( new SeekBarListener() );
        
        // Set up choosing difficulty
        final List<String> difficultyOptions = new ArrayList<String>();
        difficultyOptions.add( "Choose Difficulty" );
        for ( final Difficulty difficulty : Difficulty.values() )
        {
            difficultyOptions.add( difficulty.toString() );
        }
        
        final ArrayAdapter<String> adap = new ArrayAdapter<String>( this,
                android.R.layout.simple_spinner_item, difficultyOptions );
        adap.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        ( ( Spinner ) this.findViewById( R.id.chooseDifficulty ) ).setAdapter( adap );
        
        // Set up drawable lists
        final ArrayList<Integer> heads = new ArrayList<Integer>();
        heads.add( R.drawable.ic_head_1 );
        heads.add( R.drawable.ic_head_2 );
        heads.add( R.drawable.ic_head_3 );
        
        final ArrayList<Integer> bodys = new ArrayList<Integer>();
        bodys.add( R.drawable.ic_body_1 );
        bodys.add( R.drawable.ic_body_2 );
        bodys.add( R.drawable.ic_body_3 );
        
        final ArrayList<Integer> legs = new ArrayList<Integer>();
        legs.add( R.drawable.ic_pants_1 );
        legs.add( R.drawable.ic_pants_2 );
        legs.add( R.drawable.ic_pants_3 );
        
        final ArrayList<Integer> feets = new ArrayList<Integer>();
        feets.add( R.drawable.ic_feet_1 );
        feets.add( R.drawable.ic_feet_2 );
        feets.add( R.drawable.ic_feet_3 );
        
        // Set up Galleries
        final SelectGalleryAdapter sgaHead = new SelectGalleryAdapter( this, R.layout.gallery_row_view, heads );
        final SelectGalleryAdapter sgaBody = new SelectGalleryAdapter( this, R.layout.gallery_row_view, bodys );
        final SelectGalleryAdapter sgaLegs = new SelectGalleryAdapter( this, R.layout.gallery_row_view, legs );
        final SelectGalleryAdapter sgaFeet = new SelectGalleryAdapter( this, R.layout.gallery_row_view, feets );
        
        ( ( Gallery ) this.findViewById( R.id.galleryHead ) ).setAdapter( sgaHead );
        ( ( Gallery ) this.findViewById( R.id.galleryBody ) ).setAdapter( sgaBody );
        ( ( Gallery ) this.findViewById( R.id.galleryLegs ) ).setAdapter( sgaLegs );
        ( ( Gallery ) this.findViewById( R.id.galleryFeet ) ).setAdapter( sgaFeet );
    }
    
    /**
     * Done button clicked.
     * 
     * @param v
     *        the View
     */
    @SuppressWarnings ( "unchecked")
    public void doneButtonClicked( final View v )
    {
        
        String problem = null;
        
        if ( this.statPoints < PlayerConfig.STARTING_STAT_POINTS )
        {
            problem = "You haven't assigned all your stats! Don't make this game too hard!";
        }
        
        final AdapterView<SpinnerAdapter> chooseDSA = ( AdapterView<SpinnerAdapter> ) this
                .findViewById( R.id.chooseDifficulty );
        if ( chooseDSA.getSelectedItem().toString().equals( "Choose Difficulty" ) )
        {
            problem = "Please select a difficulty!";
        }
        
        if ( this.playerName.getText().toString().equals( "" ) )
        {
            problem = "Please fill out your player name!";
        }
        
        // if there is a problem creating the player present a message,
        // else create the player and send them to ShipConfig!
        if ( problem != null )
        {
            Toast.makeText( this.getApplicationContext(), problem, Toast.LENGTH_LONG ).show();
        }
        else
        {
            // create a player and an array of stats
            this.player = new Player();
            this.stats = new int[4];
            
            // fill the stats array
            this.stats[0] = this.statPilotBar.getProgress();
            this.stats[1] = this.statFighterBar.getProgress();
            this.stats[2] = this.statTraderBar.getProgress();
            this.stats[3] = this.statEngineerBar.getProgress();
            
            final Inventory inv = new Inventory( this.stats[3] );
            this.player.setInventory( inv );
            
            final int difficulty = ( ( AdapterView<SpinnerAdapter> ) this
                    .findViewById( R.id.chooseDifficulty ) ).getSelectedItemPosition();
            
            // set the player
            this.player.setName( this.playerName.getText().toString() );
            this.player.setStats( this.stats );
            this.player.setMoney( PlayerConfig.MAX_STARTING_MONEY / difficulty );
            Gallery tempGallery = ( ( Gallery ) this.findViewById( R.id.galleryHead ) );
            this.player.setHead( ( ( SelectGalleryAdapter ) tempGallery.getAdapter() )
                    .getItemAtPosition( tempGallery.getSelectedItemPosition() ) );
            tempGallery = ( ( Gallery ) this.findViewById( R.id.galleryBody ) );
            this.player.setBody( ( ( SelectGalleryAdapter ) tempGallery.getAdapter() )
                    .getItemAtPosition( tempGallery.getSelectedItemPosition() ) );
            tempGallery = ( ( Gallery ) this.findViewById( R.id.galleryLegs ) );
            this.player.setLegs( ( ( SelectGalleryAdapter ) tempGallery.getAdapter() )
                    .getItemAtPosition( tempGallery.getSelectedItemPosition() ) );
            tempGallery = ( ( Gallery ) this.findViewById( R.id.galleryFeet ) );
            this.player.setFeet( ( ( SelectGalleryAdapter ) tempGallery.getAdapter() )
                    .getItemAtPosition( tempGallery.getSelectedItemPosition() ) );
            
            final Intent intent = new Intent( PlayerConfig.this, ShipConfig.class );
            intent.putExtra( ShipConfig.PLAYER_EXTRA, this.player );
            intent.putExtra( ShipConfig.DIFFICULTY_EXTRA, difficulty );
            PlayerConfig.this.startActivity( intent );
        }
    }
    
    // Create a SeekBarListener, Beautiful logic for disabling more than 16 pts,
    /**
     * The listener interface for receiving seekBar events. The class that is
     * interested in processing a seekBar event implements this interface, and
     * the object created with that class is registered with a component using
     * the component's <code>addSeekBarListener<code> method. When
     * the seekBar event occurs, that object's appropriate
     * method is invoked.
     * 
     * @see SeekBarEvent
     */
    private class SeekBarListener implements OnSeekBarChangeListener
    {
        
        /**
         * Override:
         * 
         * @see android.widget.SeekBar.OnSeekBarChangeListener#onProgressChanged(android.widget.SeekBar,
         *      int, boolean)
         */
        public void onProgressChanged( final SeekBar bar, final int value, final boolean fromUser )
        {
            PlayerConfig.this.statPoints = PlayerConfig.this.statPilotBar.getProgress()
                    + PlayerConfig.this.statEngineerBar.getProgress()
                    + PlayerConfig.this.statTraderBar.getProgress()
                    + PlayerConfig.this.statFighterBar.getProgress();
            if ( PlayerConfig.this.statPoints > PlayerConfig.STARTING_STAT_POINTS )
            {
                bar.setProgress( bar.getProgress() - 1 );
            }
        }
        
        /**
         * Override:
         * 
         * @see android.widget.SeekBar.OnSeekBarChangeListener#onStartTrackingTouch(android.widget.SeekBar)
         */
        public void onStartTrackingTouch( final SeekBar bar )
        {
        }
        
        /**
         * Override:
         * 
         * @see android.widget.SeekBar.OnSeekBarChangeListener#onStopTrackingTouch(android.widget.SeekBar)
         */
        public void onStopTrackingTouch( final SeekBar bar )
        {
        }
        
    }
}

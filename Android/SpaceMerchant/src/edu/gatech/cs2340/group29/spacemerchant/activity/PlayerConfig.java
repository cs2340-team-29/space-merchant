
package edu.gatech.cs2340.group29.spacemerchant.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import edu.gatech.cs2340.group29.spacemerchant.R;
import edu.gatech.cs2340.group29.spacemerchant.model.Player;

public class PlayerConfig extends Activity
{
    public int       statpts = 0;
    private Player   player;
    private int[]    stats;
    private EditText player_name;
    private SeekBar  stat1;
    private SeekBar  stat2;
    private SeekBar  stat3;
    private SeekBar  stat4;
    private int head;
    private int body;
    private int legs;
    private int feet;
    
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_player_config );
        
        // gets the views that determine the values
        player_name = ( EditText ) findViewById( R.id.playerName );
        stat1 = ( SeekBar ) findViewById( R.id.stat1 );
        stat2 = ( SeekBar ) findViewById( R.id.stat2 );
        stat3 = ( SeekBar ) findViewById( R.id.stat3 );
        stat4 = ( SeekBar ) findViewById( R.id.stat4 );
        
        stat1.setOnSeekBarChangeListener( new SeekBarListener() );
        stat2.setOnSeekBarChangeListener( new SeekBarListener() );
        stat3.setOnSeekBarChangeListener( new SeekBarListener() );
        stat4.setOnSeekBarChangeListener( new SeekBarListener() );
        
        // Set up choosing difficulty
        ArrayList<String> difficulties = new ArrayList<String>();
        difficulties.add( "Choose Difficulty" );
        difficulties.add( "Easy" );
        difficulties.add( "Medium" );
        difficulties.add( "Hard" );
        difficulties.add( "Impossible" );
        
        ArrayAdapter<String> adap = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item,
                difficulties );
        adap.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        ( ( Spinner ) findViewById( R.id.chooseDifficulty ) ).setAdapter( adap );
        
    }
    
    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        getMenuInflater().inflate( R.menu.activity_player_config, menu );
        return true;
    }
    
    @SuppressWarnings ( "unchecked")
    public void doneButtonClicked( View v )
    {
        
        String problem = null;
        
        if ( statpts < 16 )
        {
            problem = "You haven't assigned all your stats! Don't make this game too hard!";
        }
        
        if ( ( ( AdapterView<SpinnerAdapter> ) findViewById( R.id.chooseDifficulty ) ).getSelectedItem()
                .toString().equals( "Choose Difficulty" ) )
        {
            problem = "Please select a difficulty!";
        }
        
        if ( player_name.getText().toString().equals( "" ) )
        {
            problem = "Please fill out your player name!";
        }
        
        // if there is a problem creating the player present a message,
        // else create the player and send them to ShipConfig!
        if ( problem != null )
        {
            Toast.makeText( getApplicationContext(), problem, Toast.LENGTH_LONG ).show();
        }
        else
        {
            // create a player and an array of stats
            player = new Player();
            stats = new int[4];
            
            // fill the stats array
            stats[0] = ( int ) stat1.getProgress();
            stats[1] = ( int ) stat2.getProgress();
            stats[2] = ( int ) stat3.getProgress();
            stats[3] = ( int ) stat4.getProgress();
            
            // set the player
            player.setName( player_name.getText().toString() );
            player.setStats( stats );
            player.setMoney( 1000 );
            
            int difficulty = ( ( AdapterView<SpinnerAdapter> ) findViewById( R.id.chooseDifficulty ) ).getSelectedItemPosition();
            
            Intent intent = new Intent( PlayerConfig.this, ShipConfig.class );
            intent.putExtra( ShipConfig.player_extra, player );
            intent.putExtra( ShipConfig.difficulty_extra, difficulty );
            PlayerConfig.this.startActivity( intent );
        }
    }
    
    // Create a SeekBarListener, Beautiful logic for disabling more than 16 pts,
    // ummm confirmed bug, one stat can only go to max of 15, not sure why...
    private class SeekBarListener implements OnSeekBarChangeListener
    {
        
        public void onProgressChanged( SeekBar bar, int value, boolean fromUser )
        {
            statpts = stat1.getProgress() + stat4.getProgress() + stat3.getProgress() + stat2.getProgress();
            if ( statpts > 16 )
                bar.setProgress( bar.getProgress() - 1 );
        }
        
        public void onStartTrackingTouch( SeekBar bar )
        {
        }
        
        public void onStopTrackingTouch( SeekBar bar )
        {
        }
        
    }
}

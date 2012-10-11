
package edu.gatech.cs2340.group29.spacemerchant.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import edu.gatech.cs2340.group29.spacemerchant.GameActivity;
import edu.gatech.cs2340.group29.spacemerchant.R;
import edu.gatech.cs2340.group29.spacemerchant.model.Game;
import edu.gatech.cs2340.group29.spacemerchant.model.Player;
import edu.gatech.cs2340.group29.spacemerchant.model.Ship;
import edu.gatech.cs2340.group29.spacemerchant.util.GameDataSource;

public class ShipConfig extends Activity
{
    public static final String player_extra     = "PLAYER_EXTRA";
    public static final String difficulty_extra = "DIFFICULTY_EXTRA";
    
    private Player             player;
    private int                difficulty;
    
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
    }
    
    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        getMenuInflater().inflate( R.menu.activity_ship_config, menu );
        return true;
    }
    
    public void doneButtonClicked( View v )
    {
        // do stuff here, send to main screen, save game, etc...
        
        GameDataSource gds = new GameDataSource( getApplicationContext() );
        gds.open();
        
        Game g = new Game();
        Ship s = new Ship();
        player.setShip( s );
        g.setDifficulty( difficulty );
        g.setPlayer( player );
        gds.createGame( g );
        gds.close();
        
        Intent intent = new Intent(ShipConfig.this, GameActivity.class);
        this.startActivity( intent );
    }
}

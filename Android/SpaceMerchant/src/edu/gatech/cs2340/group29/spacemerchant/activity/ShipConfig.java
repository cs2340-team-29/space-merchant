
package edu.gatech.cs2340.group29.spacemerchant.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import edu.gatech.cs2340.group29.spacemerchant.R;
import edu.gatech.cs2340.group29.spacemerchant.model.Player;

public class ShipConfig extends Activity
{
    public static final String player_extra = "PLAYER_EXTRA";
    
    private Player player;
    
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        
        this.player = ( Player ) getIntent().getSerializableExtra( player_extra );
        
        Toast.makeText( this.getApplicationContext(), player.getName(), Toast.LENGTH_LONG ).show();
        
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
    }
}

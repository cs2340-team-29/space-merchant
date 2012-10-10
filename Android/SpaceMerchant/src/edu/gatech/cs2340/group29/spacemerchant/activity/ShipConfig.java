
package edu.gatech.cs2340.group29.spacemerchant.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import edu.gatech.cs2340.group29.spacemerchant.R;

public class ShipConfig extends Activity
{
    
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
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

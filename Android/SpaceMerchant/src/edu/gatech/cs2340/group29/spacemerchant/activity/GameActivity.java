
package edu.gatech.cs2340.group29.spacemerchant.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import edu.gatech.cs2340.group29.spacemerchant.R;
import edu.gatech.cs2340.group29.spacemerchant.model.Game;
import edu.gatech.cs2340.group29.spacemerchant.model.Player;
import edu.gatech.cs2340.group29.spacemerchant.model.Ship;
import edu.gatech.cs2340.group29.spacemerchant.util.GameDataSource;

public class GameActivity extends Activity
{
    public static final String GAME_ID_EXTRA = "GAME_ID_EXTRA";
    
    private boolean showHelpOverlay;
    
    Game                       g;
    Player                     p;
    Ship                       s;
    
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_game );
        
        Intent i = getIntent();
        long gameID = i.getLongExtra( GAME_ID_EXTRA, -1 );
        
        GameDataSource gds = new GameDataSource( getApplicationContext() );
        gds.open();
        g = gds.getGameByID( gameID );
        p = g.getPlayer();
        s = p.getShip();
        gds.close();
        
        //Setting images for player and ship
        ( ( ImageView ) findViewById( R.id.head ) ).setImageResource(p.getHead());
        ( ( ImageView ) findViewById( R.id.body_player ) ).setImageResource(p.getBody());
        ( ( ImageView ) findViewById( R.id.legs ) ).setImageResource(p.getLegs());
        ( ( ImageView ) findViewById( R.id.feet ) ).setImageResource(p.getFeet());
        ( ( ImageView ) findViewById( R.id.cabin ) ).setImageResource(s.getCabin());
        ( ( ImageView ) findViewById( R.id.fuselage ) ).setImageResource(s.getFuselage());
        ( ( ImageView ) findViewById( R.id.boosters ) ).setImageResource(s.getBoosters());
        
        if (showHelpOverlay)
        {
            //show overlay
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        getMenuInflater().inflate( R.menu.activity_game, menu );
        return true;
    }
    
    public void gotoPlayerInfo( View v )
    {
        // do stuff later!
    }
}

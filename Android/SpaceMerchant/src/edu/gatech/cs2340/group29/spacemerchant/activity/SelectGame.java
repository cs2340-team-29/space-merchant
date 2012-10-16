
package edu.gatech.cs2340.group29.spacemerchant.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import edu.gatech.cs2340.group29.spacemerchant.R;
import edu.gatech.cs2340.group29.spacemerchant.adapter.SelectGameAdapter;
import edu.gatech.cs2340.group29.spacemerchant.model.Game;
import edu.gatech.cs2340.group29.spacemerchant.model.Player;
import edu.gatech.cs2340.group29.spacemerchant.util.GameDataSource;

public class SelectGame extends Activity
{
    
    private ArrayList<Game> alg;
    
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_select_game );
        GameDataSource gds = new GameDataSource( getApplicationContext() );
        gds.open();
        alg = ( ArrayList<Game> ) gds.getGameList();
        gds.close();
        
        alg.trimToSize();
        SelectGameAdapter s = new SelectGameAdapter( this, R.layout.activity_select_game, alg );
        ( ( ListView ) this.findViewById( R.id.gameList ) ).setAdapter( s );
        ( ( ListView ) this.findViewById( R.id.gameList ) ).setOnItemClickListener( ( OnItemClickListener ) new SelectGameListener() );
    }
    
    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        getMenuInflater().inflate( R.menu.activity_select_game, menu );
        return true;
    }
    
    public void gotoPlayerConfig( View v )
    {
        // launch SelectGame activity
        Intent intent = new Intent( SelectGame.this, PlayerConfig.class );
        SelectGame.this.startActivity( intent );
    }
    
    class SelectGameListener implements OnItemClickListener
    {
        
        public void onItemClick( AdapterView<?> parent, View view, int position, long id )
        {
            Game g = alg.get( position );
            long gameID = g.getID();
            
            Intent intent = new Intent( SelectGame.this, GameActivity.class );
            intent.putExtra( GameActivity.GAME_ID_EXTRA, gameID );
            SelectGame.this.startActivity( intent );
        }
        
    }
}

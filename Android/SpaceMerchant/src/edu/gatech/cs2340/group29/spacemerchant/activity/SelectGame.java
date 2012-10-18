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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import edu.gatech.cs2340.group29.spacemerchant.R;
import edu.gatech.cs2340.group29.spacemerchant.adapter.SelectGameAdapter;
import edu.gatech.cs2340.group29.spacemerchant.model.Game;
import edu.gatech.cs2340.group29.spacemerchant.util.GameDataSource;

/**
 * The Class SelectGame.
 */
public class SelectGame extends Activity
{
    
    private ArrayList<Game> alg;
    
    /**
     * Override:
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
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
        ( ( ListView ) this.findViewById( R.id.gameList ) ).setOnItemClickListener( new SelectGameListener() );
    }
    
    /**
     * Goes to the player config.
     *
     * @param v the View
     */
    public void gotoPlayerConfig( View v )
    {
        // launch SelectGame activity
        Intent intent = new Intent( SelectGame.this, PlayerConfig.class );
        SelectGame.this.startActivity( intent );
    }
    
    /**
     * The listener interface for receiving selectGame events.
     * The class that is interested in processing a selectGame
     * event implements this interface, and the object created
     * with that class is registered with a component using the
     * component's <code>addSelectGameListener<code> method. When
     * the selectGame event occurs, that object's appropriate
     * method is invoked.
     *
     * @see SelectGameEvent
     */
    class SelectGameListener implements OnItemClickListener
    {
        
        /**
         * Override:
         * 
         * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView,
         *      android.view.View, int, long)
         */
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

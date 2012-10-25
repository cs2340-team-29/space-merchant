
package edu.gatech.cs2340.group29.spacemerchant.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import edu.gatech.cs2340.group29.spacemerchant.R;
import edu.gatech.cs2340.group29.spacemerchant.adapter.TradingItemsAdapter;
import edu.gatech.cs2340.group29.spacemerchant.interfaces.Marketable;
import edu.gatech.cs2340.group29.spacemerchant.model.Game;
import edu.gatech.cs2340.group29.spacemerchant.model.Item;
import edu.gatech.cs2340.group29.spacemerchant.model.Market;
import edu.gatech.cs2340.group29.spacemerchant.model.Planet;
import edu.gatech.cs2340.group29.spacemerchant.model.Player;
import edu.gatech.cs2340.group29.spacemerchant.util.GameDataSource;

public class TradeActivity extends Activity
{
    
    private Marketable         a;
    private Marketable         b;
    public static Market       market;
    private Game               g;
    
    protected ListView         aItems;
    protected ListView         bItems;
    
    public static final String GAME_ID = "GAME_ID_EXTRA";
    
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        
        Intent i = getIntent();
        long gameID = i.getLongExtra( GAME_ID, -1 );
        GameDataSource gds = new GameDataSource( getApplicationContext() );
        gds.open();
        g = gds.getGameByID( gameID );
        a = g.getPlanet();
        b = g.getPlayer();
        gds.close();
        
        market = new Market( a, b );
        
        setContentView( R.layout.activity_trade );
        
        updateLists( null );
    }
    
    public void updateLists( View v )
    {
        ( ( TextView ) this.findViewById( R.id.entity1Name ) ).setText( a.getName() );
        ( ( TextView ) this.findViewById( R.id.entity2Name ) ).setText( b.getName() );
        ( ( TextView ) this.findViewById( R.id.entity1Money ) ).setText( "$" + a.getMoney() );
        ( ( TextView ) this.findViewById( R.id.entity2Money ) ).setText( "$" + b.getMoney() );
        
        TradingItemsAdapter t1 = new TradingItemsAdapter( this, R.layout.trading_item_row, a.getInventory() );
        TradingItemsAdapter t2 = new TradingItemsAdapter( this, R.layout.trading_item_row, b.getInventory() );
        aItems = ( ( ListView ) this.findViewById( R.id.entity1Items ) );
        aItems.setAdapter( t1 );
        ( ( ListView ) this.findViewById( R.id.entity1Items ) )
                .setOnItemClickListener( new ASelectItemListener() );
        ( ( ListView ) this.findViewById( R.id.entity2Items ) ).setAdapter( t2 );
        bItems = ( ( ListView ) this.findViewById( R.id.entity2Items ) );
        bItems.setOnItemClickListener( new BSelectItemListener() );
    }
    
    @Override
    protected void onStop()
    {
        GameDataSource gds = new GameDataSource( getApplicationContext() );
        gds.open();
        g.setPlanet( ( Planet ) a );
        g.setPlayer( ( Player ) b );
        gds.updateGame( g );
        gds.close();
        super.onStop();
    }
    
    class ASelectItemListener implements OnItemClickListener
    {
        
        public void onItemClick( AdapterView<?> parent, View view, int position, long arg3 )
        {
            if ( market.giveToB( ( Item ) ( ( ( TradingItemsAdapter ) parent.getAdapter() )
                    .getItem( position ) ) ) )
            {
                a = market.getMarketableA();
                b = market.getMarketableB();
                updateLists( null );
                aItems.invalidate();
                bItems.invalidate();
            }
            else
            {
                Toast.makeText( getApplicationContext(), "Couldn't Trade! Insufficient Funds",
                        Toast.LENGTH_LONG ).show();
            }
        }
    }
    
    class BSelectItemListener implements OnItemClickListener
    {
        
        public void onItemClick( AdapterView<?> parent, View view, int position, long arg3 )
        {
            if ( market.giveToA( ( Item ) ( ( ( TradingItemsAdapter ) parent.getAdapter() )
                    .getItem( position ) ) ) )
            {
                a = market.getMarketableA();
                b = market.getMarketableB();
                updateLists( null );
                aItems.invalidate();
                bItems.invalidate();
            }
            else
            {
                Toast.makeText( getApplicationContext(), "Couldn't Trade! Insufficient Funds",
                        Toast.LENGTH_LONG ).show();
            }
        }
        
    }
    
    public void done( View v )
    {
        updateLists( null );
        Intent intent = new Intent( TradeActivity.this, GameActivity.class );
        intent.putExtra( TradeActivity.GAME_ID, g.getGameID() );
        TradeActivity.this.startActivity( intent );
    }
}

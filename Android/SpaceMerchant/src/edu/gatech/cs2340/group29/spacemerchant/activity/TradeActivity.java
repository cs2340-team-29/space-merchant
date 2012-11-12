/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
import edu.gatech.cs2340.group29.spacemerchant.model.Market;
import edu.gatech.cs2340.group29.spacemerchant.model.Planet;
import edu.gatech.cs2340.group29.spacemerchant.model.Player;
import edu.gatech.cs2340.group29.spacemerchant.util.GameDataSource;

/**
 * The Class TradeActivity.
 */
public class TradeActivity extends Activity
{
    
    private Marketable         a;
    private Marketable         b;
    public static Market       market;
    private Game               g;
    
    protected ListView         aItems;
    protected ListView         bItems;
    
    public static final String GAME_ID = "GAME_ID_EXTRA";
    
    /**
     * Override:
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
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
    
    /**
     * Update lists.
     * 
     * @param v
     *            the View
     */
    public void updateLists( View v )
    {
        ( ( TextView ) this.findViewById( R.id.entity1Name ) ).setText( a.getName() );
        ( ( TextView ) this.findViewById( R.id.entity2Name ) ).setText( b.getName() );
        ( ( TextView ) this.findViewById( R.id.entity1Money ) ).setText( "$" + a.getMoney() + " -- "
                + a.getInventory().size() + "/" + a.getInventory().capacity() );
        ( ( TextView ) this.findViewById( R.id.entity2Money ) ).setText( "$" + b.getMoney() + " -- "
                + b.getInventory().size() + "/" + b.getInventory().capacity() );
        
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
    
    /**
     * The listener interface for receiving ASelectItem events. The class that
     * is interested in processing a ASelectItem event implements this
     * interface, and the object created with that class is registered with a
     * component using the component's
     * <code>addASelectItemListener<code> method. When
     * the ASelectItem event occurs, that object's appropriate
     * method is invoked.
     * 
     * @see ASelectItemEvent
     */
    class ASelectItemListener implements OnItemClickListener
    {
        
        /**
         * Override:
         * 
         * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView,
         *      android.view.View, int, long)
         */
        public void onItemClick( AdapterView<?> parent, View view, int position, long arg3 )
        {
            if ( market.giveToB( ( ( ( TradingItemsAdapter ) parent.getAdapter() ).getItem( position ) ) ) )
            {
                a = market.getMarketableA();
                b = market.getMarketableB();
                updateLists( null );
                aItems.invalidate();
                bItems.invalidate();
            }
            else
            {
                Toast.makeText( getApplicationContext(),
                        "Couldn't Trade!\nInsufficient Funds / Not Enough Cargo Space", Toast.LENGTH_LONG )
                        .show();
            }
        }
    }
    
    /**
     * The listener interface for receiving BSelectItem events. The class that
     * is interested in processing a BSelectItem event implements this
     * interface, and the object created with that class is registered with a
     * component using the component's
     * <code>addBSelectItemListener<code> method. When
     * the BSelectItem event occurs, that object's appropriate
     * method is invoked.
     * 
     * @see BSelectItemEvent
     */
    class BSelectItemListener implements OnItemClickListener
    {
        
        /**
         * Override:
         * 
         * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView,
         *      android.view.View, int, long)
         */
        public void onItemClick( AdapterView<?> parent, View view, int position, long arg3 )
        {
            if ( market.giveToA( ( ( ( TradingItemsAdapter ) parent.getAdapter() ).getItem( position ) ) ) )
            {
                a = market.getMarketableA();
                b = market.getMarketableB();
                updateLists( null );
                aItems.invalidate();
                bItems.invalidate();
            }
            else
            {
                Toast.makeText( getApplicationContext(),
                        "Couldn't Trade!\nInsufficient Funds / Not Enough Cargo Space", Toast.LENGTH_LONG )
                        .show();
            }
        }
        
    }
    
    /**
     * Done.
     * 
     * @param v
     *            the View
     */
    public void done( View v )
    {
        updateLists( null );
        GameDataSource gds = new GameDataSource( getApplicationContext() );
        gds.open();
        g.setPlanet( ( Planet ) a );
        g.setPlayer( ( Player ) b );
        gds.updateGame( g );
        gds.close();
        Intent intent = new Intent( TradeActivity.this, GameActivity.class );
        intent.putExtra( TradeActivity.GAME_ID, g.getGameID() );
        TradeActivity.this.startActivity( intent );
    }
}

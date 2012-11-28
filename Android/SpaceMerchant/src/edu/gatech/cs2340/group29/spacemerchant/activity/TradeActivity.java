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
    
    /** The a. */
    private Marketable         a;
    
    /** The b. */
    private Marketable         b;
    
    /** The market. */
    public static Market       MARKET  = null;
    
    /** The game. */
    private Game               game;
    
    /** The a items. */
    protected ListView         aItems;
    
    /** The b items. */
    protected ListView         bItems;
    
    /** The Constant GAME_ID. */
    public static final String GAME_ID = "GAME_ID_EXTRA";
    
    /**
     * Override:
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate( final Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        
        final Intent i = this.getIntent();
        final long gameID = i.getLongExtra( TradeActivity.GAME_ID, -1 );
        final GameDataSource gds = new GameDataSource( this.getApplicationContext() );
        try
        {
            gds.open();
            this.game = gds.getGameByID( gameID );
            this.a = this.game.getPlanet();
            this.b = this.game.getPlayer();
        }
        finally
        {
            gds.close();
        }
        
        TradeActivity.MARKET = new Market( this.a, this.b );
        
        this.setContentView( R.layout.activity_trade );
        
        this.updateLists( null );
    }
    
    /**
     * Update lists.
     * 
     * @param v
     *        the View
     */
    public void updateLists( final View v )
    {
        ( ( TextView ) this.findViewById( R.id.entity1Name ) ).setText( this.a.getName() );
        ( ( TextView ) this.findViewById( R.id.entity2Name ) ).setText( this.b.getName() );
        ( ( TextView ) this.findViewById( R.id.entity1Money ) ).setText( "$" + this.a.getMoney() + " -- "
                + this.a.getInventory().size() + "/" + this.a.getInventory().capacity() );
        ( ( TextView ) this.findViewById( R.id.entity2Money ) ).setText( "$" + this.b.getMoney() + " -- "
                + this.b.getInventory().size() + "/" + this.b.getInventory().capacity() );
        
        final TradingItemsAdapter t1 = new TradingItemsAdapter( this, R.layout.trading_item_row,
                this.a.getInventory() );
        final TradingItemsAdapter t2 = new TradingItemsAdapter( this, R.layout.trading_item_row,
                this.b.getInventory() );
        this.aItems = ( ( ListView ) this.findViewById( R.id.entity1Items ) );
        this.aItems.setAdapter( t1 );
        ( ( ListView ) this.findViewById( R.id.entity1Items ) )
                .setOnItemClickListener( new ASelectItemListener() );
        ( ( ListView ) this.findViewById( R.id.entity2Items ) ).setAdapter( t2 );
        this.bItems = ( ( ListView ) this.findViewById( R.id.entity2Items ) );
        this.bItems.setOnItemClickListener( new BSelectItemListener() );
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
        public void onItemClick( final AdapterView<?> parent, final View view, final int position,
                final long arg3 )
        {
            if ( TradeActivity.MARKET.giveToB( ( ( ( TradingItemsAdapter ) parent.getAdapter() )
                    .getItem( position ) ) ) )
            {
                TradeActivity.this.a = TradeActivity.MARKET.getMarketableA();
                TradeActivity.this.b = TradeActivity.MARKET.getMarketableB();
                TradeActivity.this.updateLists( null );
                TradeActivity.this.aItems.invalidate();
                TradeActivity.this.bItems.invalidate();
            }
            else
            {
                Toast.makeText( TradeActivity.this.getApplicationContext(),
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
        public void onItemClick( final AdapterView<?> parent, final View view, final int position,
                final long arg3 )
        {
            if ( TradeActivity.MARKET.giveToA( ( ( ( TradingItemsAdapter ) parent.getAdapter() )
                    .getItem( position ) ) ) )
            {
                TradeActivity.this.a = TradeActivity.MARKET.getMarketableA();
                TradeActivity.this.b = TradeActivity.MARKET.getMarketableB();
                TradeActivity.this.updateLists( null );
                TradeActivity.this.aItems.invalidate();
                TradeActivity.this.bItems.invalidate();
            }
            else
            {
                Toast.makeText( TradeActivity.this.getApplicationContext(),
                        "Couldn't Trade!\nInsufficient Funds / Not Enough Cargo Space", Toast.LENGTH_LONG )
                        .show();
            }
        }
        
    }
    
    /**
     * Done.
     * 
     * @param v
     *        the View
     */
    public void done( final View v )
    {
        this.updateLists( null );
        final GameDataSource gds = new GameDataSource( this.getApplicationContext() );
        try
        {
            gds.open();
            this.game.setPlanet( ( Planet ) this.a );
            this.game.setPlayer( ( Player ) this.b );
            gds.updateGame( this.game );
        }
        finally
        {
            gds.close();
        }
        final Intent intent = new Intent( TradeActivity.this, GameActivity.class );
        intent.putExtra( TradeActivity.GAME_ID, this.game.getGameID() );
        TradeActivity.this.startActivity( intent );
    }
}

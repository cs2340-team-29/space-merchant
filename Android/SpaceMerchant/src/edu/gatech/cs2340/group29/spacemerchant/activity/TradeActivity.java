
package edu.gatech.cs2340.group29.spacemerchant.activity;

import android.app.Activity;
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
import edu.gatech.cs2340.group29.spacemerchant.model.Item;
import edu.gatech.cs2340.group29.spacemerchant.model.Market;

public class TradeActivity extends Activity
{
    
    private Marketable a;
    private Marketable b;
    private Market     market;
    
    protected ListView aItems;
    protected ListView bItems;
    
    public static final String MARKETABLE_A = "MARKETABLE_A_EXTRA";
    public static final String MARKETABLE_B = "MARKETABLE_B_EXTRA";
    
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        
        market = new Market( a, b );
        
        setContentView( R.layout.activity_trade );
        
        ( ( TextView ) this.findViewById( R.id.entity1Name ) ).setText( a.getName() );
        ( ( TextView ) this.findViewById( R.id.entity2Name ) ).setText( b.getName() );
        ( ( TextView ) this.findViewById( R.id.entity1Money ) ).setText( a.getMoney() );
        ( ( TextView ) this.findViewById( R.id.entity2Money ) ).setText( b.getMoney() );
        
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
    
    class ASelectItemListener implements OnItemClickListener
    {
        
        public void onItemClick( AdapterView<?> parent, View view, int position, long arg3 )
        {
            if ( market.giveToB( ( Item ) ( ( ( TradingItemsAdapter ) parent.getAdapter() )
                    .getItem( position ) ) ) )
            {
                aItems.invalidate();
                bItems.invalidate();
            }
            else
            {
                Toast.makeText( getApplicationContext(), "Couldn't Trade! Insufficient Funds", Toast.LENGTH_LONG ).show();
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
                aItems.invalidate();
                bItems.invalidate();
            }
            else
            {
                Toast.makeText( getApplicationContext(), "Couldn't Trade! Insufficient Funds", Toast.LENGTH_LONG ).show();
            }
        }
        
    }
    
    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        getMenuInflater().inflate( R.menu.activity_trade, menu );
        return true;
    }
}

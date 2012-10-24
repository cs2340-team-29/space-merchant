package edu.gatech.cs2340.group29.spacemerchant.activity;

import java.util.ArrayList;
import java.util.LinkedList;

import edu.gatech.cs2340.group29.spacemerchant.R;
import edu.gatech.cs2340.group29.spacemerchant.activity.SelectGame.SelectGameListener;
import edu.gatech.cs2340.group29.spacemerchant.adapter.SelectGameAdapter;
import edu.gatech.cs2340.group29.spacemerchant.adapter.TradingItemsAdapter;
import edu.gatech.cs2340.group29.spacemerchant.model.Game;
import edu.gatech.cs2340.group29.spacemerchant.model.Inventory;
import edu.gatech.cs2340.group29.spacemerchant.model.Item;
import edu.gatech.cs2340.group29.spacemerchant.model.Market;
import edu.gatech.cs2340.group29.spacemerchant.model.Planet;
import edu.gatech.cs2340.group29.spacemerchant.model.Player;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class TradeActivity extends Activity {
	
	
	private Player player;
	private Planet planet;
	private Market market;
	private ArrayList<Item> items1;
	private ArrayList<Item> items2;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        player = (Player) getIntent().getParcelableExtra("inventory 1");
        planet = (Planet) getIntent().getParcelableExtra("inventory 2");
        LinkedList<Item>[] inv1 = player.getInventory().getContents();
        LinkedList<Item>[] inv2 = player.getInventory().getContents();
        items1 = new ArrayList<Item>();
        items2 = new ArrayList<Item>();
        
        // fill items
        for(int i=0; i<inv1.length; i++)
        	for(int k=0; k<inv1[i].size(); k++)
        		items1.add(inv1[i].get(k));
        for(int i=0; i<inv2.length; i++)
        	for(int k=0; k<inv2[i].size(); k++)
        		items2.add(inv2[i].get(k));
        
        market = (Market) getIntent().getParcelableExtra("the market");
        
        setContentView(R.layout.activity_trade);
        

        ( ( TextView ) this.findViewById( R.id.entity1Name ) ).setText( items1.get(0).getName() );
        ( ( TextView ) this.findViewById( R.id.entity2Name ) ).setText( items2.get(0).getName() );
        ( ( TextView ) this.findViewById( R.id.entity1Money ) ).setText( items1.get(0).getBasePrice() );
        ( ( TextView ) this.findViewById( R.id.entity2Money ) ).setText( items2.get(0).getBasePrice() );
        
        TradingItemsAdapter t1 = new TradingItemsAdapter( this, R.layout.trading_item_row,  items1);
        TradingItemsAdapter t2 = new TradingItemsAdapter( this, R.layout.trading_item_row,  items2);
        ( ( ListView ) this.findViewById( R.id.entity1Items ) ).setAdapter( t1 );
        ( ( ListView ) this.findViewById( R.id.entity1Items ) ).setOnItemClickListener( new SelectItemListener() );
        ( ( ListView ) this.findViewById( R.id.entity2Items ) ).setAdapter( t2 );
        ( ( ListView ) this.findViewById( R.id.entity2Items ) ).setOnItemClickListener( new SelectItemListener() );
    
    }

    class SelectItemListener implements OnItemClickListener
    {

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	  //      ( ( TextView ) this.findViewById( R.id.entity1Name ) ).setText( items1.get(0).getName() );
	   //     ( ( TextView ) this.findViewById( R.id.entity2Name ) ).setText( items2.get(0).getName() );
	  //      ( ( TextView ) this.findViewById( R.id.entity1Money ) ).setText( items1.get(0).getBasePrice() );
	  //      ( ( TextView ) this.findViewById( R.id.entity2Money ) ).setText( items2.get(0).getBasePrice() );
		}
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_trade, menu);
        return true;
    }
}

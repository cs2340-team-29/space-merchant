/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.adapter;

import java.util.ArrayList;
import java.util.LinkedList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import edu.gatech.cs2340.group29.spacemerchant.R;
import edu.gatech.cs2340.group29.spacemerchant.model.Item;
import edu.gatech.cs2340.group29.spacemerchant.model.Player;

/**
 * The Class InventoryAdapter.
 */
public class InventoryAdapter extends BaseAdapter
{
    private ArrayList<Item> items;
    private Context         context;
    private Player          player;
    
    /**
     * Instantiates a new inventory adapter.
     * 
     * @param context
     *            the Context
     * @param layoutResourceId
     *            the int
     * @param items
     *            the Inventory
     */
    public InventoryAdapter( Context context, int layoutResourceId, Player player )
    {
        super();
        this.context = context;
        
        this.player = player;
        this.items = new ArrayList<Item>();
        LinkedList<Item>[] inventoryItems = player.getInventory().getContents();
        
        for ( LinkedList<Item> inventoryItemsByType : inventoryItems )
        {
            
            for ( Item item : inventoryItemsByType )
            {
                this.items.add( item );
            }
        }
    }
    
    /**
     * The Class ViewHolder.
     */
    static class ViewHolder
    {
        protected ImageView iv;
        protected TextView  name;
        protected TextView  price;
    }
    
    /**
     * Override:
     * 
     * @see android.widget.Adapter#getView(int, android.view.View,
     *      android.view.ViewGroup)
     */
    public View getView( int position, View convertView, ViewGroup parent )
    {
        ViewHolder viewHolder;
        
        if ( convertView == null )
        {
            LayoutInflater inf = ( ( Activity ) context ).getLayoutInflater();
            convertView = inf.inflate( R.layout.trading_item_row, parent, false );
            viewHolder = new ViewHolder();
        }
        else
        {
            viewHolder = ( ViewHolder ) convertView.getTag();
        }
        
        viewHolder.iv = ( ImageView ) convertView.findViewById( R.id.itemImage );
        viewHolder.name = ( TextView ) convertView.findViewById( R.id.itemName );
        viewHolder.price = ( TextView ) convertView.findViewById( R.id.itemCost );
        
        convertView.setTag( viewHolder );
        
        viewHolder.iv.setImageResource( items.get( position ).getDrawable() );
        viewHolder.name.setText( items.get( position ).getName() );
        viewHolder.price.setText( "$" + player.getBasePrice( items.get( position ) ) );
        
        return convertView;
    }
    
    /**
     * Override:
     * 
     * @see android.widget.Adapter#getCount()
     */
    public int getCount()
    {
        return items.size();
    }
    
    /**
     * Override:
     * 
     * @see android.widget.Adapter#getItem(int)
     */
    public Item getItem( int position )
    {
        return items.get( position );
    }
    
    /**
     * Override:
     * 
     * @see android.widget.Adapter#getItemId(int)
     */
    public long getItemId( int arg0 )
    {
        return 0;
    }
    
    /**
     * Gets the item at position.
     * 
     * @param position
     *            the int
     * @return the item at position
     */
    public Item getItemAtPosition( int position )
    {
        return items.get( position );
    }
}

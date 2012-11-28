/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.adapter;

import java.util.ArrayList;
import java.util.List;

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
    
    /** The items. */
    private final List<Item> items;
    
    /** The context. */
    private final Context    context;
    
    /** The player. */
    private final Player     player;
    
    /**
     * Instantiates a new inventory adapter.
     * 
     * @param context
     *        the Context
     * @param layoutResourceId
     *        the int
     * @param player
     *        the Player
     */
    public InventoryAdapter( final Context context, final int layoutResourceId, final Player player )
    {
        this.context = context;
        
        this.player = player;
        this.items = new ArrayList<Item>();
        final List<Item>[] inventoryItems = player.getInventory().getContents();
        
        for ( final List<Item> inventoryItemsByType : inventoryItems )
        {
            
            for ( final Item item : inventoryItemsByType )
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
        
        /** The iv. */
        protected ImageView iv;
        
        /** The name. */
        protected TextView  name;
        
        /** The price. */
        protected TextView  price;
    }
    
    /**
     * Override:
     * 
     * @see android.widget.Adapter#getView(int, android.view.View,
     *      android.view.ViewGroup)
     */
    public View getView( final int position, View convertView, final ViewGroup parent )
    {
        ViewHolder viewHolder;
        
        if ( convertView == null )
        {
            final LayoutInflater inf = ( ( Activity ) this.context ).getLayoutInflater();
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
        
        viewHolder.iv.setImageResource( this.items.get( position ).getDrawable() );
        viewHolder.name.setText( this.items.get( position ).getName() );
        viewHolder.price.setText( "$" + this.player.getBasePrice( this.items.get( position ) ) );
        
        return convertView;
    }
    
    /**
     * Override:
     * 
     * @see android.widget.Adapter#getCount()
     */
    public int getCount()
    {
        return this.items.size();
    }
    
    /**
     * Override:
     * 
     * @see android.widget.Adapter#getItem(int)
     */
    public Item getItem( final int position )
    {
        return this.items.get( position );
    }
    
    /**
     * Override:
     * 
     * @see android.widget.Adapter#getItemId(int)
     */
    public long getItemId( final int arg0 )
    {
        return 0;
    }
    
    /**
     * Gets the item at position.
     * 
     * @param position
     *        the int
     * @return the item at position
     */
    public Item getItemAtPosition( final int position )
    {
        return this.items.get( position );
    }
}

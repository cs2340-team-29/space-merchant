/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import edu.gatech.cs2340.group29.spacemerchant.R;

/**
 * The Class SelectGalleryAdapter.
 */
public class SelectGalleryAdapter extends BaseAdapter
{
    private ArrayList<Integer> drawables;
    private Context            context;
    
    /**
     * Instantiates a new select gallery adapter.
     *
     * @param context the Context
     * @param layoutResourceId the int
     * @param drawables the ArrayList<Integer>
     */
    public SelectGalleryAdapter( Context context, int layoutResourceId, ArrayList<Integer> drawables )
    {
        super();
        this.context = context;
        this.drawables = drawables;
    }
    
    /**
     * The Class ViewHolder.
     */
    static class ViewHolder
    {
        protected ImageView iv;
    }
    
    /** 
     *
     * Override:
     * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
     */
    public View getView( int position, View convertView, ViewGroup parent )
    {
        ViewHolder viewHolder;
        
        if ( convertView == null )
        {
            LayoutInflater inf = ( ( Activity ) context ).getLayoutInflater();
            convertView = inf.inflate( R.layout.gallery_row_view, parent, false );
            viewHolder = new ViewHolder();
        }
        else
        {
            viewHolder = ( ViewHolder ) convertView.getTag();
        }
        
        viewHolder.iv = ( ImageView ) convertView.findViewById( R.id.galleryImage );
        
        convertView.setTag( viewHolder );
        
        viewHolder.iv.setImageResource( drawables.get( position ) );
        
        return convertView;
    }
    
    /** 
     *
     * Override:
     * @see android.widget.Adapter#getCount()
     */
    public int getCount()
    {
        return drawables.size();
    }
    
    /** 
     *
     * Override:
     * @see android.widget.Adapter#getItem(int)
     */
    public Object getItem( int arg0 )
    {
        return null;
    }
    
    /** 
     *
     * Override:
     * @see android.widget.Adapter#getItemId(int)
     */
    public long getItemId( int arg0 )
    {
        return 0;
    }
    
    /**
     * Gets the item at position.
     *
     * @param position the int
     * @return the item at position
     */
    public int getItemAtPosition( int position )
    {
        return drawables.get( position );
    }
}

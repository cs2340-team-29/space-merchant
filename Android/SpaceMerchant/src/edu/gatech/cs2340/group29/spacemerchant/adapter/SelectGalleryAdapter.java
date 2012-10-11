
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

public class SelectGalleryAdapter extends BaseAdapter
{
    private ArrayList<Integer> drawables;
    private Context            context;
    
    public SelectGalleryAdapter( Context context, int layoutResourceId, ArrayList<Integer> drawables )
    {
        super();
        this.context = context;
        this.drawables = drawables;
    }
    
    static class ViewHolder
    {
        protected ImageView iv;
    }
    
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
    
    public int getCount()
    {
        return drawables.size();
    }
    
    public Object getItem( int arg0 )
    {
        return null;
    }
    
    public long getItemId( int arg0 )
    {
        return 0;
    }
    
    public int getItemAtPosition(int position)
    {
        return drawables.get( position );
    }
}


package edu.gatech.cs2340.group29.spacemerchant.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import edu.gatech.cs2340.group29.spacemerchant.R;
import edu.gatech.cs2340.group29.spacemerchant.model.Player;

public class SelectGalleryAdapter extends ArrayAdapter<Player>
{
    private ArrayList<Player> players;
    private Context           context;
    
    public SelectGalleryAdapter( Context context, int layoutResourceId, ArrayList<Player> players )
    {
        super( context, layoutResourceId, players );
        this.context = context;
        this.players = players;
    }
    
    static class ViewHolder
    {
        protected ImageView player_component;
 
    }
    
    public View getView( int position, View convertView, ViewGroup parent )
    {
        ViewHolder viewHolder;
        
        if ( convertView == null )
        {
            LayoutInflater inf = ( ( Activity ) context ).getLayoutInflater();
            convertView = inf.inflate( R.layout.custom_select_game, parent, false );
            viewHolder = new ViewHolder();
        }
        else
        {
            viewHolder = ( ViewHolder ) convertView.getTag();
        }
        
        viewHolder.player_component = ( ImageView ) convertView.findViewById( R.id.hat );

        convertView.setTag( viewHolder );
        
        viewHolder.player_component.setImageResource( players.get( position ).getHat() );
  
        return convertView;
    }
}

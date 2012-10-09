
package edu.gatech.cs2340.group29.spacemerchant.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import edu.gatech.cs2340.group29.spacemerchant.R;

public class SelectGameAdapter<Player> extends ArrayAdapter<Player>
{
    
    private ArrayList<Player> players;
    private Context           context;
    
    public SelectGameAdapter( Context context, int layoutResourceId, ArrayList<Player> players )
    {
        super( context, layoutResourceId, players );
        this.context = context;
        this.players = players;
    }
    
    static class ViewHolder
    {
        protected ImageView player_hat;
        protected ImageView player_head;
        protected ImageView player_body;
        protected ImageView player_legs;
        protected ImageView player_feet;
        protected ImageView ship_nose;
        protected ImageView ship_right_wing;
        protected ImageView ship_left_wing;
        protected ImageView ship_body;
        protected ImageView ship_thruster;
        protected String    player_name;
        protected int       money;
        protected int[]     stats;
    }
    
    @Override
    public View getView( int position, View convertView, ViewGroup parent )
    {
        ViewHolder viewHolder;
        
        if ( convertView == null )
        {
            LayoutInflater inf = ( ( Activity ) context ).getLayoutInflater();
            convertView = inf.inflate( R.layout.custom_select_game, parent, false );
        }
        else
        {
            viewHolder = ( ViewHolder ) convertView.getTag();
        }
        
        viewHolder = new ViewHolder();
        viewHolder.player_hat = ( ImageView ) convertView.findViewById( R.id.hat );
        viewHolder.player_head = ( ImageView ) convertView.findViewById( R.id.head );
        convertView.setTag( viewHolder );
        
        viewHolder.player_hat.setImageResource( players.get( position ).getName() );
        return convertView;
    }
}


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
        protected ImageView player_hat;
        protected ImageView player_head;
        protected ImageView player_body;
        protected ImageView player_legs;
        protected ImageView player_feet;
        protected ImageView ship_nose;
        protected ImageView ship_left_wing;
        protected ImageView ship_right_wing;
        protected ImageView ship_body;
        protected ImageView ship_thruster;
        protected TextView  player_name;
        protected TextView  money;
        protected TextView  stat1;
        protected TextView  stat2;
        protected TextView  stat3;
        protected TextView  stat4;
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
        
        viewHolder.player_hat = ( ImageView ) convertView.findViewById( R.id.hat );
        viewHolder.player_head = ( ImageView ) convertView.findViewById( R.id.head );
        viewHolder.player_body = ( ImageView ) convertView.findViewById( R.id.body_player );
        viewHolder.player_legs = ( ImageView ) convertView.findViewById( R.id.legs );
        viewHolder.player_feet = ( ImageView ) convertView.findViewById( R.id.feet );
        viewHolder.ship_nose = ( ImageView ) convertView.findViewById( R.id.nose );
        viewHolder.ship_body = ( ImageView ) convertView.findViewById( R.id.body_ship );
        viewHolder.ship_left_wing = ( ImageView ) convertView.findViewById( R.id.leftWing );
        viewHolder.ship_right_wing = ( ImageView ) convertView.findViewById( R.id.rightWing );
        viewHolder.ship_thruster = ( ImageView ) convertView.findViewById( R.id.thruster );
        viewHolder.player_name = ( TextView ) convertView.findViewById( R.id.playerName );
        viewHolder.money = ( TextView ) convertView.findViewById( R.id.money );
        viewHolder.stat1 = ( TextView ) convertView.findViewById( R.id.stat1 );
        viewHolder.stat2 = ( TextView ) convertView.findViewById( R.id.stat2 );
        viewHolder.stat3 = ( TextView ) convertView.findViewById( R.id.stat3 );
        viewHolder.stat4 = ( TextView ) convertView.findViewById( R.id.stat4 );
        convertView.setTag( viewHolder );
        
        viewHolder.player_hat.setImageDrawable( players.get( position ).getHat() );
        viewHolder.player_head.setImageDrawable( players.get( position ).getHead() );
        viewHolder.player_body.setImageDrawable( players.get( position ).getBody() );
        viewHolder.player_legs.setImageDrawable( players.get( position ).getLegs() );
        viewHolder.player_feet.setImageDrawable( players.get( position ).getFeet() );
        viewHolder.ship_nose.setImageDrawable( players.get( position ).getShip().getNose() );
        viewHolder.ship_body.setImageDrawable( players.get( position ).getShip().getBody() );
        viewHolder.ship_left_wing.setImageDrawable( players.get( position ).getShip().getLeft_wing() );
        viewHolder.ship_right_wing.setImageDrawable( players.get( position ).getShip().getRight_wing() );
        viewHolder.ship_thruster.setImageDrawable( players.get( position ).getShip().getThruster() );
        viewHolder.player_name.setText( players.get( position ).getName() );
        viewHolder.money.setText( Integer.toString( players.get( position ).getMoney() ) );
        viewHolder.stat1.setText( Integer.toString( players.get( position ).getStats()[0] ) );
        viewHolder.stat2.setText( Integer.toString( players.get( position ).getStats()[1] ) );
        viewHolder.stat3.setText( Integer.toString( players.get( position ).getStats()[2] ) );
        viewHolder.stat4.setText( Integer.toString( players.get( position ).getStats()[3] ) );
        return convertView;
    }
}

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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import edu.gatech.cs2340.group29.spacemerchant.R;
import edu.gatech.cs2340.group29.spacemerchant.model.Game;
import edu.gatech.cs2340.group29.spacemerchant.model.Player;
import edu.gatech.cs2340.group29.spacemerchant.model.StatGroup.Stat;

/**
 * The Class SelectGameAdapter.
 */
public class SelectGameAdapter extends ArrayAdapter<Game>
{
    
    /** The players. */
    private final List<Player> players;
    
    /** The context. */
    private final Context      context;
    
    /**
     * Instantiates a new select game adapter.
     * 
     * @param context
     *        the Context
     * @param layoutResourceId
     *        the int
     * @param games
     *        the List<Game>
     */
    public SelectGameAdapter( final Context context, final int layoutResourceId, final List<Game> games )
    {
        super( context, layoutResourceId, games );
        this.context = context;
        
        final List<Player> p = new ArrayList<Player>();
        for ( final Game game : games )
        {
            p.add( game.getPlayer() );
        }
        
        this.players = p;
    }
    
    /**
     * The Class ViewHolder.
     */
    static class ViewHolder
    {
        
        /** The player_head. */
        protected ImageView player_head;
        
        /** The player_body. */
        protected ImageView player_body;
        
        /** The player_legs. */
        protected ImageView player_legs;
        
        /** The player_feet. */
        protected ImageView player_feet;
        
        /** The ship_fuselage. */
        protected ImageView ship_fuselage;
        
        /** The ship_cabin. */
        protected ImageView ship_cabin;
        
        /** The ship_boosters. */
        protected ImageView ship_boosters;
        
        /** The player_name. */
        protected TextView  player_name;
        
        /** The money. */
        protected TextView  money;
        
        /** The stat1. */
        protected TextView  stat1;
        
        /** The stat2. */
        protected TextView  stat2;
        
        /** The stat3. */
        protected TextView  stat3;
        
        /** The stat4. */
        protected TextView  stat4;
    }
    
    /**
     * Override:
     * 
     * @see android.widget.ArrayAdapter#getView(int, android.view.View,
     *      android.view.ViewGroup)
     */
    @Override
    public View getView( final int position, View convertView, final ViewGroup parent )
    {
        ViewHolder viewHolder;
        
        if ( convertView == null )
        {
            final LayoutInflater inf = ( ( Activity ) this.context ).getLayoutInflater();
            convertView = inf.inflate( R.layout.custom_select_game, parent, false );
            viewHolder = new ViewHolder();
        }
        else
        {
            viewHolder = ( ViewHolder ) convertView.getTag();
        }
        
        viewHolder.player_head = ( ImageView ) convertView.findViewById( R.id.head );
        viewHolder.player_body = ( ImageView ) convertView.findViewById( R.id.body_player );
        viewHolder.player_legs = ( ImageView ) convertView.findViewById( R.id.legs );
        viewHolder.player_feet = ( ImageView ) convertView.findViewById( R.id.feet );
        viewHolder.ship_fuselage = ( ImageView ) convertView.findViewById( R.id.fuselage );
        viewHolder.ship_cabin = ( ImageView ) convertView.findViewById( R.id.cabin );
        viewHolder.ship_boosters = ( ImageView ) convertView.findViewById( R.id.boosters );
        viewHolder.player_name = ( TextView ) convertView.findViewById( R.id.playerName );
        viewHolder.money = ( TextView ) convertView.findViewById( R.id.money );
        viewHolder.stat1 = ( TextView ) convertView.findViewById( R.id.stat1 );
        viewHolder.stat2 = ( TextView ) convertView.findViewById( R.id.stat2 );
        viewHolder.stat3 = ( TextView ) convertView.findViewById( R.id.stat3 );
        viewHolder.stat4 = ( TextView ) convertView.findViewById( R.id.stat4 );
        convertView.setTag( viewHolder );
        
        viewHolder.player_head.setImageResource( this.players.get( position ).getHead() );
        viewHolder.player_body.setImageResource( this.players.get( position ).getBody() );
        viewHolder.player_legs.setImageResource( this.players.get( position ).getLegs() );
        viewHolder.player_feet.setImageResource( this.players.get( position ).getFeet() );
        viewHolder.ship_fuselage.setImageResource( this.players.get( position ).getShip().getFuselage() );
        viewHolder.ship_cabin.setImageResource( this.players.get( position ).getShip().getCabin() );
        viewHolder.ship_boosters.setImageResource( this.players.get( position ).getShip().getBoosters() );
        viewHolder.player_name.setText( this.players.get( position ).getName() );
        viewHolder.money.setText( "$ " + this.players.get( position ).getMoney() );
        viewHolder.stat1.setText( "Pilot: " + this.players.get( position ).getStats().get( Stat.PILOT ) );
        viewHolder.stat2.setText( "Fighter: " + this.players.get( position ).getStats().get( Stat.FIGHTER ) );
        viewHolder.stat3.setText( "Trader: " + this.players.get( position ).getStats().get( Stat.TRADER ) );
        viewHolder.stat4
                .setText( "Engineer: " + this.players.get( position ).getStats().get( Stat.ENGINEER ) );
        return convertView;
    }
}

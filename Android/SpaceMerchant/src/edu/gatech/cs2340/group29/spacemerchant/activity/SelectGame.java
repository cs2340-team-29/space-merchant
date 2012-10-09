
package edu.gatech.cs2340.group29.spacemerchant.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import edu.gatech.cs2340.group29.spacemerchant.R;
import edu.gatech.cs2340.group29.spacemerchant.adapter.SelectGameAdapter;
import edu.gatech.cs2340.group29.spacemerchant.model.Player;

public class SelectGame extends Activity
{
    
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_select_game );
        
        ArrayList<Player> p = new ArrayList<Player>();
        p.add(new Player());
        p.add(new Player());
        SelectGameAdapter s = new SelectGameAdapter(this, R.layout.activity_select_game, p);
        ((ListView) this.findViewById(R.id.gameList)).setAdapter(s);
    }
    
    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        getMenuInflater().inflate( R.menu.activity_select_game, menu );
        return true;
    }
}

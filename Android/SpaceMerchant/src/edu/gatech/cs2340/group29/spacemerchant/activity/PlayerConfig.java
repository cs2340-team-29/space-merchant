
package edu.gatech.cs2340.group29.spacemerchant.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import edu.gatech.cs2340.group29.spacemerchant.R;
import edu.gatech.cs2340.group29.spacemerchant.model.Player;

public class PlayerConfig extends Activity
{
	public int statpts = 16;
 //   private TextView stat1View = (TextView)findViewById(R.id.stat1TextView);
    
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_player_config );
        
        // create a player and an array of stats
        Player player = new Player();
        int [] stats = new int[5];
        
        // gets the views that determine the values
        EditText name = (EditText)findViewById(R.id.playerName);
        SeekBar stat1 = (SeekBar)findViewById(R.id.stat1);
        SeekBar stat2 = (SeekBar)findViewById(R.id.stat2);
        SeekBar stat3 = (SeekBar)findViewById(R.id.stat3);
        SeekBar stat4 = (SeekBar)findViewById(R.id.stat4);
        SeekBar stat5 = (SeekBar)findViewById(R.id.stat5);
        
        stat1.setOnSeekBarChangeListener(new SeekBarListener());
        
        // fill the stats array
        stats[0] = (int)stat1.getProgress();
        stats[1] = (int)stat2.getProgress();
        stats[2] = (int)stat3.getProgress();
        stats[3] = (int)stat4.getProgress();
        stats[4] = (int)stat5.getProgress();
        
        // set the player
        player.setName(name.getText().toString());
        player.setStats(stats);
        player.setMoney(1000);
    }
    
    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        getMenuInflater().inflate( R.menu.activity_player_config, menu );
        return true;
    }
    
    // Create a SeekBarListener
    private class SeekBarListener implements OnSeekBarChangeListener{

		public void onProgressChanged(SeekBar bar, int value, boolean arg2) {
		//	stat1View.setText(value);
			statpts = statpts - bar.getProgress();
			if(statpts == 0)
				bar.setEnabled(false);
		}

		public void onStartTrackingTouch(SeekBar bar) {
		}

		public void onStopTrackingTouch(SeekBar bar) {
		}
    	
    }
}

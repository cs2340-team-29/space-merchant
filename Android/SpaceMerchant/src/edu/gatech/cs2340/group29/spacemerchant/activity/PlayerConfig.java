package edu.gatech.cs2340.group29.spacemerchant.activity;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import edu.gatech.cs2340.group29.spacemerchant.R;
import edu.gatech.cs2340.group29.spacemerchant.adapter.ChooseDifficultyAdapter;
import edu.gatech.cs2340.group29.spacemerchant.model.Player;

public class PlayerConfig extends Activity {
	public int statpts = 16;

	// private TextView stat1View = (TextView)findViewById(R.id.stat1TextView);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player_config);

		// create a player and an array of stats
		Player player = new Player();
		int[] stats = new int[4];

		// gets the views that determine the values
		EditText name = (EditText) findViewById(R.id.playerName);
		SeekBar stat1 = (SeekBar) findViewById(R.id.stat1);
		SeekBar stat2 = (SeekBar) findViewById(R.id.stat2);
		SeekBar stat3 = (SeekBar) findViewById(R.id.stat3);
		SeekBar stat4 = (SeekBar) findViewById(R.id.stat4);

		stat1.setOnSeekBarChangeListener(new SeekBarListener());

		// fill the stats array
		stats[0] = (int) stat1.getProgress();
		stats[1] = (int) stat2.getProgress();
		stats[2] = (int) stat3.getProgress();
		stats[3] = (int) stat4.getProgress();

		// set the player
		player.setName(name.getText().toString());
		player.setStats(stats);
		player.setMoney(1000);

		ArrayList<String> difficulties = (ArrayList<String>) Arrays
				.asList(new String[] { "Choose Difficulty", "Easy", "Medium",
						"Hard", "Impossible" });

		ChooseDifficultyAdapter cda = new ChooseDifficultyAdapter(
				getApplicationContext(), R.id.chooseDifficulty, difficulties);
		((Spinner) findViewById(R.id.chooseDifficulty)).setAdapter(cda);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_player_config, menu);
		return true;
	}

	public void doneButtonClicked(View v) {

		String problem = null;

		
		
		if (problem != null) {
			Toast.makeText(getApplicationContext(), problem, Toast.LENGTH_SHORT)
					.show();
		} else {
			Intent intent = new Intent(PlayerConfig.this, ShipConfig.class);
			PlayerConfig.this.startActivity(intent);
		}
	}

	// Create a SeekBarListener
	private class SeekBarListener implements OnSeekBarChangeListener {

		public void onProgressChanged(SeekBar bar, int value, boolean arg2) {
			// stat1View.setText(value);
			statpts = statpts - bar.getProgress();
			if (statpts == 0)
				bar.setEnabled(false);
		}

		public void onStartTrackingTouch(SeekBar bar) {
		}

		public void onStopTrackingTouch(SeekBar bar) {
		}

	}
}

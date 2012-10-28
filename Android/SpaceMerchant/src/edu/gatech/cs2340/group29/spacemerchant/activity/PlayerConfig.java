/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import edu.gatech.cs2340.group29.spacemerchant.R;
import edu.gatech.cs2340.group29.spacemerchant.adapter.SelectGalleryAdapter;
import edu.gatech.cs2340.group29.spacemerchant.model.Inventory;
import edu.gatech.cs2340.group29.spacemerchant.model.Player;

/**
 * The Class PlayerConfig.
 */
public class PlayerConfig extends Activity {

	private static final int MAX_STARTING_MONEY = 4000;
	private static final int STARTING_STAT_POINTS = 16;

	public enum Difficulty {
		EASY, MEDIUM, HARD, IMPOSSIBLE
	}

	public int statPoints = 0;
	private Player player;
	private int[] stats;
	private EditText playerName;
	private SeekBar statPilotBar;
	private SeekBar statFighterBar;
	private SeekBar statTraderBar;
	private SeekBar statEngineerBar;

	/**
	 * Override:
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player_config);

		// gets the views that determine the values
		playerName = (EditText) findViewById(R.id.playerName);
		statPilotBar = (SeekBar) findViewById(R.id.stat1);
		statFighterBar = (SeekBar) findViewById(R.id.stat2);
		statTraderBar = (SeekBar) findViewById(R.id.stat3);
		statEngineerBar = (SeekBar) findViewById(R.id.stat4);

		statPilotBar.setOnSeekBarChangeListener(new SeekBarListener());
		statFighterBar.setOnSeekBarChangeListener(new SeekBarListener());
		statTraderBar.setOnSeekBarChangeListener(new SeekBarListener());
		statEngineerBar.setOnSeekBarChangeListener(new SeekBarListener());

		// Set up choosing difficulty
		ArrayList<String> difficultyOptions = new ArrayList<String>();
		difficultyOptions.add("Choose Difficulty");
		for (Difficulty difficulty : Difficulty.values()) {
			difficultyOptions.add(difficulty.toString());
		}

		ArrayAdapter<String> adap = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, difficultyOptions);
		adap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		((Spinner) findViewById(R.id.chooseDifficulty)).setAdapter(adap);

		// Set up drawable lists
		ArrayList<Integer> heads = new ArrayList<Integer>();
		heads.add(R.drawable.ic_head_1);
		heads.add(R.drawable.ic_head_2);
		heads.add(R.drawable.ic_head_3);

		ArrayList<Integer> bodys = new ArrayList<Integer>();
		bodys.add(R.drawable.ic_body_1);
		bodys.add(R.drawable.ic_body_2);
		bodys.add(R.drawable.ic_body_3);

		ArrayList<Integer> legs = new ArrayList<Integer>();
		legs.add(R.drawable.ic_pants_1);
		legs.add(R.drawable.ic_pants_2);
		legs.add(R.drawable.ic_pants_3);

		ArrayList<Integer> feets = new ArrayList<Integer>();
		feets.add(R.drawable.ic_feet_1);
		feets.add(R.drawable.ic_feet_2);
		feets.add(R.drawable.ic_feet_3);

		// Set up Galleries
		SelectGalleryAdapter sgaHead = new SelectGalleryAdapter(this,
				R.layout.gallery_row_view, heads);
		SelectGalleryAdapter sgaBody = new SelectGalleryAdapter(this,
				R.layout.gallery_row_view, bodys);
		SelectGalleryAdapter sgaLegs = new SelectGalleryAdapter(this,
				R.layout.gallery_row_view, legs);
		SelectGalleryAdapter sgaFeet = new SelectGalleryAdapter(this,
				R.layout.gallery_row_view, feets);

		((Gallery) findViewById(R.id.galleryHead)).setAdapter(sgaHead);
		((Gallery) findViewById(R.id.galleryBody)).setAdapter(sgaBody);
		((Gallery) findViewById(R.id.galleryLegs)).setAdapter(sgaLegs);
		((Gallery) findViewById(R.id.galleryFeet)).setAdapter(sgaFeet);
	}

	/**
	 * Done button clicked.
	 * 
	 * @param v
	 *            the View
	 */
	@SuppressWarnings("unchecked")
	public void doneButtonClicked(View v) {

		String problem = null;

		if (statPoints < STARTING_STAT_POINTS) {
			problem = "You haven't assigned all your stats! Don't make this game too hard!";
		}

		if (((AdapterView<SpinnerAdapter>) findViewById(R.id.chooseDifficulty))
				.getSelectedItem().toString().equals("Choose Difficulty")) {
			problem = "Please select a difficulty!";
		}

		if (playerName.getText().toString().equals("")) {
			problem = "Please fill out your player name!";
		}

		// if there is a problem creating the player present a message,
		// else create the player and send them to ShipConfig!
		if (problem != null) {
			Toast.makeText(getApplicationContext(), problem, Toast.LENGTH_LONG)
					.show();
		} else {
			// create a player and an array of stats
			player = new Player();
			stats = new int[4];

			// fill the stats array
			stats[0] = statPilotBar.getProgress();
			stats[1] = statFighterBar.getProgress();
			stats[2] = statTraderBar.getProgress();
			stats[3] = statEngineerBar.getProgress();

			Inventory inv = new Inventory(stats[3]);
			player.setInventory(inv);

			int difficulty = ((AdapterView<SpinnerAdapter>) findViewById(R.id.chooseDifficulty))
					.getSelectedItemPosition();

			// set the player
			player.setName(playerName.getText().toString());
			player.setStats(stats);
			player.setMoney(MAX_STARTING_MONEY / difficulty);
			Gallery tempGallery = ((Gallery) findViewById(R.id.galleryHead));
			player.setHead(((SelectGalleryAdapter) tempGallery.getAdapter())
					.getItemAtPosition(tempGallery.getSelectedItemPosition()));
			tempGallery = ((Gallery) findViewById(R.id.galleryBody));
			player.setBody(((SelectGalleryAdapter) tempGallery.getAdapter())
					.getItemAtPosition(tempGallery.getSelectedItemPosition()));
			tempGallery = ((Gallery) findViewById(R.id.galleryLegs));
			player.setLegs(((SelectGalleryAdapter) tempGallery.getAdapter())
					.getItemAtPosition(tempGallery.getSelectedItemPosition()));
			tempGallery = ((Gallery) findViewById(R.id.galleryFeet));
			player.setFeet(((SelectGalleryAdapter) tempGallery.getAdapter())
					.getItemAtPosition(tempGallery.getSelectedItemPosition()));

			Intent intent = new Intent(PlayerConfig.this, ShipConfig.class);
			intent.putExtra(ShipConfig.player_extra, player);
			intent.putExtra(ShipConfig.difficulty_extra, difficulty);
			PlayerConfig.this.startActivity(intent);
		}
	}

	// Create a SeekBarListener, Beautiful logic for disabling more than 16 pts,
	private class SeekBarListener implements OnSeekBarChangeListener {

		public void onProgressChanged(SeekBar bar, int value, boolean fromUser) {
			statPoints = statPilotBar.getProgress()
					+ statEngineerBar.getProgress()
					+ statTraderBar.getProgress()
					+ statFighterBar.getProgress();
			if (statPoints > STARTING_STAT_POINTS) {
				bar.setProgress(bar.getProgress() - 1);
			}
		}

		public void onStartTrackingTouch(SeekBar bar) {
		}

		public void onStopTrackingTouch(SeekBar bar) {
		}

	}
}

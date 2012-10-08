package edu.gatech.cs2340.group29.spacemerchant.activity;

import java.util.ArrayList;
import edu.gatech.cs2340.group29.spacemerchant.R;
import edu.gatech.cs2340.group29.spacemerchant.model.Difficulty;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class ChooseDifficulty extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_difficulty);
        
        
        // Create difficulties
        ArrayList<Difficulty> difficultyList = new ArrayList<Difficulty>();
        difficultyList.add(new Difficulty(0));
        difficultyList.add(new Difficulty(1));
        difficultyList.add(new Difficulty(2));
        difficultyList.add(new Difficulty(3));
        difficultyList.add(new Difficulty(4));
        
        // Set up view in the ListView gameList
        ListView difficultyListView = (ListView)findViewById(R.id.difficultiesListView);
        ChooseDifficultyAdapter adapter = new ChooseDifficultyAdapter(this, R.layout.activity_choose_difficulty, difficultyList);
        difficultyListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_choose_difficulty, menu);
        return true;
    }
}

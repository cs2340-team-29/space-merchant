package edu.gatech.cs2340.group29.spacemerchant.activity;

import edu.gatech.cs2340.group29.spacemerchant.R;
import edu.gatech.cs2340.group29.spacemerchant.R.layout;
import edu.gatech.cs2340.group29.spacemerchant.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class TradeActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_trade, menu);
        return true;
    }
}

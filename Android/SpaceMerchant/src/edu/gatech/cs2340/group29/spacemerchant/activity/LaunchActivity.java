
package edu.gatech.cs2340.group29.spacemerchant.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import edu.gatech.cs2340.group29.spacemerchant.R;

public class LaunchActivity extends Activity
{
    
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_launch );
    }
    
    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        getMenuInflater().inflate( R.menu.launch_activity, menu );
        return true;
    }
    
    public void gotoSelectGame(View v)
    {
        //launch SelectGame activity
        Intent selectGameIntent = new Intent( LaunchActivity.this, SelectGame.class );
        LaunchActivity.this.startActivity( selectGameIntent );
    }
    
    public void gotoAboutPage(View v)
    {
        //goto About Activity
        Intent aboutIntent = new Intent( LaunchActivity.this, AboutPage.class );
        LaunchActivity.this.startActivity( aboutIntent );
    }

}

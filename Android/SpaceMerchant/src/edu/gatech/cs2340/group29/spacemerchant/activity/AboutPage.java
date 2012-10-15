
package edu.gatech.cs2340.group29.spacemerchant.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import edu.gatech.cs2340.group29.spacemerchant.R;

public class AboutPage extends Activity
{
    
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_about_page );
    }
    
    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        getMenuInflater().inflate( R.menu.activity_about_page, menu );
        return true;
    }
}

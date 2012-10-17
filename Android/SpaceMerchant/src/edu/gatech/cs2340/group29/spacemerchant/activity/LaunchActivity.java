/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import edu.gatech.cs2340.group29.spacemerchant.R;

/**
 * The Class LaunchActivity.
 */
public class LaunchActivity extends Activity
{
    
    /**
     * Override:
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_launch );
    }
    
    /**
     * Goes to the select game.
     * 
     * @param v
     *            the View
     */
    public void gotoSelectGame( View v )
    {
        // launch SelectGame activity
        Intent selectGameIntent = new Intent( LaunchActivity.this, SelectGame.class );
        LaunchActivity.this.startActivity( selectGameIntent );
    }
    
    /**
     * Goes to the about page.
     * 
     * @param v
     *            the View
     */
    public void gotoAboutPage( View v )
    {
        // goto About Activity
        Intent aboutIntent = new Intent( LaunchActivity.this, AboutPage.class );
        LaunchActivity.this.startActivity( aboutIntent );
    }
    
}

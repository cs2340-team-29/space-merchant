/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.activity;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.widget.Toast;
import edu.gatech.cs2340.group29.spacemerchant.R;
import edu.gatech.cs2340.group29.spacemerchant.model.Game;
import edu.gatech.cs2340.group29.spacemerchant.model.Planet;
import edu.gatech.cs2340.group29.spacemerchant.model.Player;
import edu.gatech.cs2340.group29.spacemerchant.model.StatGroup.Stat;
import edu.gatech.cs2340.group29.spacemerchant.model.Universe;
import edu.gatech.cs2340.group29.spacemerchant.util.GameDataSource;

/**
 * The Class TravelActivity.
 */
public class TravelActivity extends Activity implements SurfaceHolder.Callback, OnTouchListener
{
    public static final String GAME_ID = "GAME_ID_EXTRA";
    
    private Game               game;
    private Universe           universe;
    private Planet             selected;
    private Planet             current;
    private Player             player;
    
    private TextView           cpName;
    private TextView           cpTechLevel;
    private TextView           cpResource;
    private TextView           spName;
    private TextView           spTechLevel;
    private TextView           spResource;
    
    private Planet[][]         workingUniverse;
    
    private int                canvasWidth, paddingY;
    
    private String[]           techLevels;
    private String[]           resourceTypes;
    
    /** 
     *
     * Override:
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_travel );
        
        Intent i = getIntent();
        long gameID = i.getLongExtra( GAME_ID, -1 );
        
        GameDataSource gds = new GameDataSource( getApplicationContext() );
        gds.open();
        game = gds.getGameByID( gameID );
        player = game.getPlayer();
        universe = game.getUniverse();
        current = game.getPlanet();
        selected = null;
        gds.close();
        
        Resources res = getResources();
        techLevels = res.getStringArray( R.array.TechLevels );
        resourceTypes = res.getStringArray( R.array.ResourceTypes );
        
        cpName = ( TextView ) findViewById( R.id.cpName );
        cpTechLevel = ( TextView ) findViewById( R.id.cpTechLevel );
        cpResource = ( TextView ) findViewById( R.id.cpResource );
        spName = ( TextView ) findViewById( R.id.spName );
        spTechLevel = ( TextView ) findViewById( R.id.spTechLevel );
        spResource = ( TextView ) findViewById( R.id.spResource );
        
        cpName.setText( "Name: " + current.getName() );
        cpTechLevel.setText( "Tech Level: " + techLevels[current.getTechLevel()] );
        cpResource.setText( "Resources: " + resourceTypes[current.getResourceType() + 5] );
        
        updateSelected( null );
        
        int travelDistance = 3 + ( player.getStats().get( Stat.PILOT ) / 3 );
        workingUniverse = new Planet[travelDistance][travelDistance];
        generateWorkingUniverse( universe, current );
        
        SurfaceView sv = ( SurfaceView ) findViewById( R.id.surfaceView );
        sv.getHolder().addCallback( this );
        sv.setOnTouchListener( this );
    }
    
    private void updateSelected( View v )
    {
        if ( selected != null )
        {
            spName.setText( "Name: " + selected.getName() );
            spTechLevel.setText( "Tech Level: " + techLevels[selected.getTechLevel()] );
            spResource.setText( "Resources: " + resourceTypes[selected.getResourceType() + 5] );
        }
    }
    
    /**
     * Generate working universe.
     *
     * @param u the Universe
     * @param p the Planet
     */
    public void generateWorkingUniverse( Universe u, Planet p )
    {
        int x = p.getX();
        int y = p.getY();
        
        for ( int i = -workingUniverse.length / 2; i < ( workingUniverse.length / 2 ); i++ )
        {
            for ( int j = -workingUniverse[0].length / 2; j < ( workingUniverse[0].length / 2 ); j++ )
            {
                for ( Planet planet : universe.getUniverse() )
                {
                    int tempX = planet.getX();
                    int tempY = planet.getY();
                    if ( ( tempX == ( x + i ) ) && ( tempY == ( y + j ) ) )
                    {
                        workingUniverse[i + ( workingUniverse.length / 2 )][j
                                + ( workingUniverse[0].length / 2 )] = planet;
                    }
                }
            }
        }
    }
    
    /** 
     *
     * Override:
     * @see android.view.SurfaceHolder.Callback#surfaceChanged(android.view.SurfaceHolder, int, int, int)
     */
    public void surfaceChanged( SurfaceHolder holder, int format, int width, int height )
    {
        tryToDraw( holder );
    }
    
    /** 
     *
     * Override:
     * @see android.view.SurfaceHolder.Callback#surfaceCreated(android.view.SurfaceHolder)
     */
    public void surfaceCreated( SurfaceHolder holder )
    {
        tryToDraw( holder );
    }
    
    /** 
     *
     * Override:
     * @see android.view.SurfaceHolder.Callback#surfaceDestroyed(android.view.SurfaceHolder)
     */
    public void surfaceDestroyed( SurfaceHolder holder )
    {
    }
    
    private void tryToDraw( SurfaceHolder holder )
    {
        Canvas canvas = holder.lockCanvas();
        if ( canvas == null )
        {
            Toast.makeText( this.getApplicationContext(), "Cannot Draw, Canvas is NULL!", Toast.LENGTH_LONG )
                    .show();
        }
        else
        {
            draw( canvas );
            holder.unlockCanvasAndPost( canvas );
        }
    }
    
    private void draw( final Canvas canvas )
    {
        Resources res = this.getApplicationContext().getResources();
        Rect dst = new Rect();
        
        int planetSize = canvas.getWidth() / workingUniverse.length;
        paddingY = ( canvas.getHeight() - canvas.getWidth() ) / workingUniverse[0].length;
        
        this.canvasWidth = canvas.getWidth();
        
        for ( int i = 0; i < workingUniverse.length; i++ )
        {
            for ( int j = 0; j < workingUniverse[0].length; j++ )
            {
                dst.set( planetSize * i, ( planetSize * j ) + ( paddingY * j ), planetSize * ( i + 1 ),
                        ( planetSize * ( j + 1 ) ) + ( ( j ) * paddingY ) );
                if ( workingUniverse[i][j] != null )
                {
                    Drawable base = res.getDrawable( workingUniverse[i][j].getBase() );
                    Drawable land = res.getDrawable( workingUniverse[i][j].getLand() );
                    Drawable cloud = res.getDrawable( workingUniverse[i][j].getCloud() );
                    Drawable[] layers = { base, land, cloud };
                    LayerDrawable drab = new LayerDrawable( layers );
                    drab.setBounds( dst );
                    drab.draw( canvas );
                }
            }
        }
    }
    
    /**
     * Travel.
     *
     * @param v the View
     */
    public void travel( View v )
    {
        if ( selected != null )
        {
            game.setPlanet( selected );
            GameDataSource gds = new GameDataSource( getApplicationContext() );
            gds.open();
            gds.updateGame( game );
            gds.close();
            Intent intent = new Intent( TravelActivity.this, GameActivity.class );
            intent.putExtra( GameActivity.GAME_ID_EXTRA, game.getGameID() );
            intent.putExtra( GameActivity.PIRATE_EXTRA, pirateEvent() );
            TravelActivity.this.startActivity( intent );
        }
    }
    
    public boolean pirateEvent()
    {
        Random r = new Random();
        if ( r.nextDouble() * 100 <= 20 )
        {
            return true;
        }
        return false;
    }
    
    /** 
     *
     * Override:
     * @see android.view.View.OnTouchListener#onTouch(android.view.View, android.view.MotionEvent)
     */
    public boolean onTouch( View view, MotionEvent event )
    {
        int x = ( int ) event.getX();
        int y = ( int ) event.getY();
        
        Rect dst = new Rect();
        int planetSize = canvasWidth / workingUniverse.length;
        
        for ( int i = 0; i < workingUniverse.length; i++ )
        {
            for ( int j = 0; j < workingUniverse[0].length; j++ )
            {
                dst.set( planetSize * i, ( planetSize * j ) + ( paddingY * j ), planetSize * ( i + 1 ),
                        ( planetSize * ( j + 1 ) ) + ( ( j ) * paddingY ) );
                if ( workingUniverse[i][j] != null )
                {
                    if ( dst.contains( x, y ) )
                    {
                        selected = workingUniverse[i][j];
                        break;
                    }
                }
            }
        }
        
        updateSelected( null );
        return false;
    }
}

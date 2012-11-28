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
    
    /** The Constant GAME_ID. */
    public static final String GAME_ID = "GAME_ID_EXTRA";
    
    /** The game. */
    private Game               game;
    
    /** The universe. */
    private Universe           universe;
    
    /** The selected. */
    private Planet             selected;
    
    /** The current. */
    private Planet             current;
    
    /** The player. */
    private Player             player;
    
    /** The cp name. */
    private TextView           cpName;
    
    /** The cp tech level. */
    private TextView           cpTechLevel;
    
    /** The cp resource. */
    private TextView           cpResource;
    
    /** The sp name. */
    private TextView           spName;
    
    /** The sp tech level. */
    private TextView           spTechLevel;
    
    /** The sp resource. */
    private TextView           spResource;
    
    /** The working universe. */
    private Planet[][]         workingUniverse;
    
    /** The padding y. */
    private int                canvasWidth, paddingY;
    
    /** The tech levels. */
    private String[]           techLevels;
    
    /** The resource types. */
    private String[]           resourceTypes;
    
    /**
     * Override:
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate( final Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        this.setContentView( R.layout.activity_travel );
        
        final Intent i = this.getIntent();
        final long gameID = i.getLongExtra( TravelActivity.GAME_ID, -1 );
        
        final GameDataSource gds = new GameDataSource( this.getApplicationContext() );
        try
        {
            gds.open();
            this.game = gds.getGameByID( gameID );
            this.player = this.game.getPlayer();
            this.universe = this.game.getUniverse();
            this.current = this.game.getPlanet();
            this.selected = null;
        }
        finally
        {
            gds.close();
        }
        
        final Resources res = this.getResources();
        this.techLevels = res.getStringArray( R.array.TechLevels );
        this.resourceTypes = res.getStringArray( R.array.ResourceTypes );
        
        this.cpName = ( TextView ) this.findViewById( R.id.cpName );
        this.cpTechLevel = ( TextView ) this.findViewById( R.id.cpTechLevel );
        this.cpResource = ( TextView ) this.findViewById( R.id.cpResource );
        this.spName = ( TextView ) this.findViewById( R.id.spName );
        this.spTechLevel = ( TextView ) this.findViewById( R.id.spTechLevel );
        this.spResource = ( TextView ) this.findViewById( R.id.spResource );
        
        this.cpName.setText( "Name: " + this.current.getName() );
        this.cpTechLevel.setText( "Tech Level: " + this.techLevels[this.current.getTechLevel()] );
        this.cpResource.setText( "Resources: " + this.resourceTypes[this.current.getResourceType() + 5] );
        
        this.updateSelected( null );
        
        final int travelDistance = 3 + ( this.player.getStats().get( Stat.PILOT ) / 3 );
        this.workingUniverse = new Planet[travelDistance][travelDistance];
        this.generateWorkingUniverse( this.universe, this.current );
        
        final SurfaceView sv = ( SurfaceView ) this.findViewById( R.id.surfaceView );
        sv.getHolder().addCallback( this );
        sv.setOnTouchListener( this );
    }
    
    /**
     * Update selected.
     * 
     * @param v
     *        the View
     */
    private void updateSelected( final View v )
    {
        if ( this.selected != null )
        {
            this.spName.setText( "Name: " + this.selected.getName() );
            this.spTechLevel.setText( "Tech Level: " + this.techLevels[this.selected.getTechLevel()] );
            this.spResource.setText( "Resources: " + this.resourceTypes[this.selected.getResourceType() + 5] );
        }
    }
    
    /**
     * Generate working universe.
     * 
     * @param u
     *        the Universe
     * @param p
     *        the Planet
     */
    public void generateWorkingUniverse( final Universe u, final Planet p )
    {
        final int x = p.getX();
        final int y = p.getY();
        
        for ( int i = -this.workingUniverse.length >> 1; i < ( this.workingUniverse.length >> 1 ); i++ )
        {
            for ( int j = -this.workingUniverse[0].length >> 1; j < ( this.workingUniverse[0].length >> 1 ); j++ )
            {
                for ( final Planet planet : this.universe.getUniverse() )
                {
                    final int tempX = planet.getX();
                    final int tempY = planet.getY();
                    if ( ( tempX == ( x + i ) ) && ( tempY == ( y + j ) ) )
                    {
                        this.workingUniverse[i + ( this.workingUniverse.length >> 1 )][j
                                + ( this.workingUniverse[0].length >> 1 )] = planet;
                    }
                }
            }
        }
    }
    
    /**
     * Override:
     * 
     * @see android.view.SurfaceHolder.Callback#surfaceChanged(android.view.SurfaceHolder,
     *      int, int, int)
     */
    public void surfaceChanged( final SurfaceHolder holder, final int format, final int width,
            final int height )
    {
        this.tryToDraw( holder );
    }
    
    /**
     * Override:
     * 
     * @see android.view.SurfaceHolder.Callback#surfaceCreated(android.view.SurfaceHolder)
     */
    public void surfaceCreated( final SurfaceHolder holder )
    {
        this.tryToDraw( holder );
    }
    
    /**
     * Override:
     * 
     * @see android.view.SurfaceHolder.Callback#surfaceDestroyed(android.view.SurfaceHolder)
     */
    public void surfaceDestroyed( final SurfaceHolder holder )
    {
    }
    
    /**
     * Try to draw.
     * 
     * @param holder
     *        the SurfaceHolder
     */
    private void tryToDraw( final SurfaceHolder holder )
    {
        final Canvas canvas = holder.lockCanvas();
        if ( canvas == null )
        {
            Toast.makeText( this.getApplicationContext(), "Cannot Draw, Canvas is NULL!", Toast.LENGTH_LONG )
                    .show();
        }
        else
        {
            this.draw( canvas );
            holder.unlockCanvasAndPost( canvas );
        }
    }
    
    /**
     * Draw.
     * 
     * @param canvas
     *        the Canvas
     */
    private void draw( final Canvas canvas )
    {
        final Resources res = this.getApplicationContext().getResources();
        final Rect dst = new Rect();
        
        final int planetSize = canvas.getWidth() / this.workingUniverse.length;
        this.paddingY = ( canvas.getHeight() - canvas.getWidth() ) / this.workingUniverse[0].length;
        
        this.canvasWidth = canvas.getWidth();
        
        for ( int i = 0; i < this.workingUniverse.length; i++ )
        {
            for ( int j = 0; j < this.workingUniverse[0].length; j++ )
            {
                dst.set( planetSize * i, ( planetSize * j ) + ( this.paddingY * j ), planetSize * ( i + 1 ),
                        ( planetSize * ( j + 1 ) ) + ( ( j ) * this.paddingY ) );
                if ( this.workingUniverse[i][j] != null )
                {
                    final Drawable base = res.getDrawable( this.workingUniverse[i][j].getBase() );
                    final Drawable land = res.getDrawable( this.workingUniverse[i][j].getLand() );
                    final Drawable cloud = res.getDrawable( this.workingUniverse[i][j].getCloud() );
                    final Drawable[] layers = { base, land, cloud };
                    final LayerDrawable drab = new LayerDrawable( layers );
                    drab.setBounds( dst );
                    drab.draw( canvas );
                }
            }
        }
    }
    
    /**
     * Travel.
     * 
     * @param v
     *        the View
     */
    public void travel( final View v )
    {
        if ( this.selected != null )
        {
            this.game.setPlanet( this.selected );
            final GameDataSource gds = new GameDataSource( this.getApplicationContext() );
            try
            {
                gds.open();
                gds.updateGame( this.game );
            }
            finally
            {
                gds.close();
            }
            final Intent intent = new Intent( TravelActivity.this, GameActivity.class );
            intent.putExtra( GameActivity.GAME_ID_EXTRA, this.game.getGameID() );
            intent.putExtra( GameActivity.PIRATE_EXTRA, this.hasPirateEvent() );
            TravelActivity.this.startActivity( intent );
        }
    }
    
    /**
     * Checks for pirate event.
     * 
     * @return true, if successful
     */
    public boolean hasPirateEvent()
    {
        final Random r = new Random();
        if ( ( r.nextDouble() * 100 ) <= 20 )
        {
            return true;
        }
        return false;
    }
    
    /**
     * Override:
     * 
     * @see android.view.View.OnTouchListener#onTouch(android.view.View,
     *      android.view.MotionEvent)
     */
    public boolean onTouch( final View view, final MotionEvent event )
    {
        final int x = ( int ) event.getX();
        final int y = ( int ) event.getY();
        
        final Rect dst = new Rect();
        final int planetSize = this.canvasWidth / this.workingUniverse.length;
        
        for ( int i = 0; i < this.workingUniverse.length; i++ )
        {
            for ( int j = 0; j < this.workingUniverse[0].length; j++ )
            {
                dst.set( planetSize * i, ( planetSize * j ) + ( this.paddingY * j ), planetSize * ( i + 1 ),
                        ( planetSize * ( j + 1 ) ) + ( ( j ) * this.paddingY ) );
                if ( this.workingUniverse[i][j] != null )
                {
                    if ( dst.contains( x, y ) )
                    {
                        this.selected = this.workingUniverse[i][j];
                        break;
                    }
                }
            }
        }
        
        this.updateSelected( null );
        return false;
    }
}

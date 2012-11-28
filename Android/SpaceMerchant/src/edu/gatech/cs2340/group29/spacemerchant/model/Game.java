/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.model;

import android.content.Context;

/**
 * The Class Game.
 */
public class Game
{
    // Universe is a matrix of size
    /** The Constant UNIVERSE. */
    public static final Planet[][] UNIVERSE   = new Planet[60][60];
    
    /** The game id. */
    long                           gameID;
    
    /** The player. */
    private Player                 player;
    
    /** The difficulty. */
    public static int              DIFFICULTY = 0;
    
    /** The planet. */
    private Planet                 planet;
    
    /** The universe. */
    private Universe               universe;
    
    /**
     * Instantiates a new game.
     * 
     * @param context
     *        the Context
     */
    public Game( final Context context )
    {
        this.gameID = -1;
        this.player = null;
        Game.DIFFICULTY = 3;
        this.universe = new Universe( Game.DIFFICULTY, context );
        this.universe.generatePlanets();
        this.planet = this.universe.getUniverse().get( 0 );
    }
    
    /**
     * Gets the game id.
     * 
     * @return the game id
     */
    public long getGameID()
    {
        return this.gameID;
    }
    
    /**
     * Sets the game id.
     * 
     * @param gameID
     *        the new game id
     */
    public void setGameID( final long gameID )
    {
        this.gameID = gameID;
    }
    
    /**
     * Gets the universe.
     * 
     * @return the universe
     */
    public Universe getUniverse()
    {
        return this.universe;
    }
    
    /**
     * Sets the universe.
     * 
     * @param universe
     *        the new universe
     */
    public void setUniverse( final Universe universe )
    {
        this.universe = universe;
    }
    
    /**
     * Gets the player.
     * 
     * @return the player
     */
    public Player getPlayer()
    {
        return this.player;
    }
    
    /**
     * Sets the player.
     * 
     * @param player
     *        the new player
     */
    public void setPlayer( final Player player )
    {
        this.player = player;
    }
    
    /**
     * Gets the difficulty.
     * 
     * @return the difficulty
     */
    public int getDifficulty()
    {
        return Game.DIFFICULTY;
    }
    
    /**
     * Sets the difficulty.
     * 
     * @param difficulty
     *        the new difficulty
     */
    public void setDifficulty( final int difficulty )
    {
        Game.DIFFICULTY = difficulty;
    }
    
    /**
     * Gets the id.
     * 
     * @return the id
     */
    public long getID()
    {
        return this.gameID;
    }
    
    /**
     * Sets the id.
     * 
     * @param gameID
     *        the new id
     */
    public void setID( final long gameID )
    {
        this.gameID = gameID;
    }
    
    /**
     * Gets the planet.
     * 
     * @return the planet
     */
    public Planet getPlanet()
    {
        return this.planet;
    }
    
    /**
     * Sets the planet.
     * 
     * @param planet
     *        the new planet
     */
    public void setPlanet( final Planet planet )
    {
        this.planet = planet;
    }
}

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
    public static final Planet[][] UNIVERSE = new Planet[60][60];
    
    long                           gameID;
    
    private Player                 player;
    static public int              difficulty;
    private Planet                 planet;
    private Universe               universe;
    
    /**
     * Instantiates a new game.
     * 
     * @param context
     *            the Context
     */
    public Game( Context context )
    {
        this.gameID = -1;
        this.player = null;
        Game.difficulty = 3;
        this.universe = new Universe( difficulty, context );
        this.universe.generatePlanets();
        this.planet = universe.getUniverse().get( 0 );
    }
    
    /**
     * Gets the game id.
     * 
     * @return the game id
     */
    public long getGameID()
    {
        return gameID;
    }
    
    /**
     * Sets the game id.
     * 
     * @param gameID
     *            the new game id
     */
    public void setGameID( long gameID )
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
        return universe;
    }
    
    /**
     * Sets the universe.
     * 
     * @param universe
     *            the new universe
     */
    public void setUniverse( Universe universe )
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
        return player;
    }
    
    /**
     * Sets the player.
     * 
     * @param player
     *            the new player
     */
    public void setPlayer( Player player )
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
        return difficulty;
    }
    
    /**
     * Sets the difficulty.
     * 
     * @param difficulty
     *            the new difficulty
     */
    public void setDifficulty( int difficulty )
    {
        Game.difficulty = difficulty;
    }
    
    /**
     * Gets the id.
     * 
     * @return the id
     */
    public long getID()
    {
        return gameID;
    }
    
    /**
     * Sets the id.
     * 
     * @param gameID
     *            the new id
     */
    public void setID( long gameID )
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
        return planet;
    }
    
    /**
     * Sets the planet.
     * 
     * @param planet
     *            the new planet
     */
    public void setPlanet( Planet planet )
    {
        this.planet = planet;
    }
}

/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.model;

/**
 * The Class Game.
 */
public class Game
{
    
    long           gameID;
    private Player player;
    private int    difficulty;
    
    // private Planet planet;
    
    /**
     * Instantiates a new game.
     */
    public Game()
    {
        this.player = null;
        this.difficulty = 3;
        // this.planet = null;
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
     * @param player the new player
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
     * @param difficulty the new difficulty
     */
    public void setDifficulty( int difficulty )
    {
        this.difficulty = difficulty;
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
     * @param gameID the new id
     */
    public void setID( long gameID )
    {
        this.gameID = gameID;
    }
}


package edu.gatech.cs2340.group29.spacemerchant.model;

public class Game
{
   
    long gameID;
    private Player player;
    private int    difficulty;
    
    // private Planet planet;
    
    public Game()
    {
        this.player = null;
        this.difficulty = 3;
        // this.planet = null;
    }
    
    public Player getPlayer()
    {
        return player;
    }
    
    public void setPlayer( Player player )
    {
        this.player = player;
    }
    
    public int getDifficulty()
    {
        return difficulty;
    }
    
    public void setDifficulty( int difficulty )
    {
        this.difficulty = difficulty;
    }
    
    public long getID()
    {
        return gameID;
    }

    public void setID( long gameID )
    {
        this.gameID = gameID;
    }
}

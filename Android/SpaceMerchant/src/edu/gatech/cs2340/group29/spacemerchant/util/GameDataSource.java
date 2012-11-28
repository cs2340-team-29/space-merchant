/**
 * @author MetaGalactic Merchants
 * @version 1.0
 * 
 */

package edu.gatech.cs2340.group29.spacemerchant.util;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import edu.gatech.cs2340.group29.spacemerchant.model.Game;
import edu.gatech.cs2340.group29.spacemerchant.model.Inventory;
import edu.gatech.cs2340.group29.spacemerchant.model.Item;
import edu.gatech.cs2340.group29.spacemerchant.model.Planet;
import edu.gatech.cs2340.group29.spacemerchant.model.Player;
import edu.gatech.cs2340.group29.spacemerchant.model.Ship;
import edu.gatech.cs2340.group29.spacemerchant.model.StatGroup;
import edu.gatech.cs2340.group29.spacemerchant.model.StatGroup.Stat;
import edu.gatech.cs2340.group29.spacemerchant.model.Universe;

/**
 * The Class GameDataSource.
 */
public class GameDataSource {
	
	/** The all game columns. */
	private static String[] ALL_GAME_COLUMNS = { "game", "planet",
			"difficulty", "name", "money", "pilotSkillPoints",
			"fighterSkillPoints", "traderSkillPoints", "engineerSkillPoints",
			"head", "body", "legs", "feet", "fuselage", "cabin", "boosters" };

	/** The all planet columns. */
	private static String[] ALL_PLANET_COLUMNS = { "planet", "game",
			"techLevel", "resourceType", "name", "xCoord", "yCoord", "money",
			"base", "land", "cloud" };

	/** The all item columns. */
	private static String[] ALL_ITEM_COLUMNS = { "item", "game", "type",
			"name", "drawable", "techLevel" };

	/** The database. */
	private SQLiteDatabase database;
	
	/** The database helper. */
	private DatabaseHelper databaseHelper;
	
	/** The context. */
	private Context context;

	/**
	 * Instantiates a new game data source.
	 *
	 * @param context the Context
	 */
	public GameDataSource(Context context) {
		this.context = context;
		databaseHelper = new DatabaseHelper(context);
	}

	/**
	 * Open.
	 *
	 * @throws SQLiteException the sQ lite exception
	 */
	public void open() throws SQLiteException {
		database = databaseHelper.getWritableDatabase();
	}

	/**
	 * Close.
	 */
	public void close() {
	    try {}
	    finally
	    {
    		database.close();
    		databaseHelper.close();
	    }
	}

	/**
	 * Creates the planet.
	 *
	 * @param planet the Planet
	 * @return the long
	 */
	private long createPlanet(Planet planet) {
		return createPlanet(planet, -1);
	}

	/**
	 * Creates the planet.
	 *
	 * @param planet the Planet
	 * @param gameID the long
	 * @return the long
	 */
	private long createPlanet(Planet planet, long gameID) {
		final int techLevel = planet.getTechLevel();
		final int resourceType = planet.getResourceType();
		final String planetName = planet.getName();
		final int xCoord = planet.getX();
		final int yCoord = planet.getY();
		final int money = planet.getMoney();
		final int base = planet.getBase();
		final int land = planet.getLand();
		final int cloud = planet.getCloud();

		final ContentValues values = new ContentValues();

		if (gameID > 0) {
			values.put("game", gameID);
		}

		values.put("techLevel", techLevel);
		values.put("resourceType", resourceType);
		values.put("name", planetName);
		values.put("xCoord", xCoord);
		values.put("yCoord", yCoord);
		values.put("money", money);
		values.put("base", base);
		values.put("land", land);
		values.put("cloud", cloud);

		final long planetID = database.insert("tb_planet", null, values);

		return planetID;
	}

	/**
	 * Creates the item.
	 *
	 * @param item the Item
	 * @param gameID the long
	 * @return the long
	 */
	private long createItem(Item item, long gameID) {
		final ContentValues values = new ContentValues();

		final String itemName = item.getName();
		final int drawable = item.getDrawable();
		final int itemType = item.getType();
		final int techLevel = item.getTechLevel();

		values.put("game", gameID);
		values.put("type", itemType);
		values.put("name", itemName);
		values.put("drawable", drawable);
		values.put("techLevel", techLevel);

		final long itemID = database.insert("tb_item", null, values);

		return itemID;

	}

	/**
	 * Update game.
	 *
	 * @param game the Game
	 * @return the long
	 */
	public long updateGame(Game game) {
		long gameID = game.getGameID();

		// remove currentPlanet from database
		String query = "" + "delete from tb_planet where planet in "
				+ "		(select planet from tb_game where game = ? ) ";

		database.rawQuery(query, new String[] { Long.toString(gameID) });

		// remove all saved inventory from database

		database.delete("tb_item", "game = ?",
				new String[] { Long.toString(gameID) });

		Planet currentPlanet = game.getPlanet();
		final Player player = game.getPlayer();
		Ship ship = player.getShip();
		Inventory inventory = player.getInventory();

		// insert currentPlanet into database

		long currentPlanetID = createPlanet(currentPlanet);

		// insert game, player, ship into database

		int money = player.getMoney();
		StatGroup stats = player.getStats();
		int head = player.getHead();
		final int body = player.getBody();
		int legs = player.getLegs();
		int feet = player.getFeet();
		int fuselage = ship.getFuselage();
		final int cabin = ship.getCabin();
		int boosters = ship.getBoosters();

		ContentValues values = new ContentValues();

		values.put("planet", currentPlanetID);
		values.put("money", money);
		values.put("pilotSkillPoints", stats.get(Stat.PILOT));
		values.put("fighterSkillPoints", stats.get(Stat.FIGHTER));
		values.put("traderSkillPoints", stats.get(Stat.TRADER));
		values.put("engineerSkillPoints", stats.get(Stat.ENGINEER));
		values.put("head", head);
		values.put("body", body);
		values.put("legs", legs);
		values.put("feet", feet);
		values.put("fuselage", fuselage);
		values.put("cabin", cabin);
		values.put("boosters", boosters);

		database.update("tb_game", values, "game = ?",
				new String[] { Long.toString(gameID) });

		// insert inventory into database

		List<Item>[] inventoryItems = inventory.getContents();

		for (List<Item> inventoryItemsByType : inventoryItems) {

			for (Item item : inventoryItemsByType) {
				createItem(item, gameID);
			}
		}

		return gameID;
	}

	/**
	 * Creates the game.
	 *
	 * @param game the Game
	 * @return the long
	 */
	public long createGame(Game game) {

		Planet currentPlanet = game.getPlanet();
		Universe universe = game.getUniverse();
		Player player = game.getPlayer();
		Ship ship = player.getShip();

		// insert planet into database

		ContentValues values = new ContentValues();
		long currentPlanetID = createPlanet(currentPlanet);

		// insert game, player, ship into database

		int difficulty = game.getDifficulty();
		String name = player.getName();
		int money = player.getMoney();
		StatGroup stats = player.getStats();
		int head = player.getHead();
		int body = player.getBody();
		int legs = player.getLegs();
		int feet = player.getFeet();
		int fuselage = ship.getFuselage();
		int cabin = ship.getCabin();
		int boosters = ship.getBoosters();

		values = new ContentValues();

		values.put("difficulty", difficulty);
		values.put("planet", currentPlanetID);
		values.put("name", name);
		values.put("money", money);
		values.put("pilotSkillPoints", stats.get(Stat.PILOT));
		values.put("fighterSkillPoints", stats.get(Stat.FIGHTER));
		values.put("traderSkillPoints", stats.get(Stat.TRADER));
		values.put("engineerSkillPoints", stats.get(Stat.ENGINEER));
		values.put("head", head);
		values.put("body", body);
		values.put("legs", legs);
		values.put("feet", feet);
		values.put("fuselage", fuselage);
		values.put("cabin", cabin);
		values.put("boosters", boosters);

		long gameID = database.insert("tb_game", null, values);

		game.setID(gameID);

		// insert universe into database

		List<Planet> universePlanets = universe.getUniverse();

		for (Planet universePlanet : universePlanets) {
			createPlanet(universePlanet, gameID);
		}

		return gameID;
	}

	/**
	 * Delete game.
	 *
	 * @param game the Game
	 */
	public void deleteGame(Game game) {
		long gameID = game.getID();

		// remove currentPlanet from database
		String query = "" + "delete from tb_planet where planet in "
				+ "		(select planet from tb_game where game = ? ) ";

		database.rawQuery(query, new String[] { Long.toString(gameID) });

		database.delete("tb_game", "game=" + gameID, null);
		database.delete("tb_item", "game=" + gameID, null);
		database.delete("tb_planet", "game=" + gameID, null);
	}

	/**
	 * Gets the game list.
	 *
	 * @return the game list
	 */
	public List<Game> getGameList() {
		List<Game> games = new ArrayList<Game>();

		Cursor cursor = database.query("tb_game", ALL_GAME_COLUMNS, null, null,
				null, null, null);

		cursor.moveToFirst();

		while (!cursor.isAfterLast()) {
			Game game = cursorToGame(cursor);

			games.add(game);

			cursor.moveToNext();

		}

		try {}
		finally
		{
		    cursor.close();
		}
		return games;
	}

	/**
	 * Gets the game by id.
	 *
	 * @param gameID the long
	 * @return the game by id
	 */
	public Game getGameByID(long gameID) {

		Cursor cursor = database.query("tb_game", ALL_GAME_COLUMNS, "game="
				+ gameID, null, null, null, null);

		cursor.moveToFirst();

		Game game = cursorToGame(cursor);

		try {}
		finally
		{
		    cursor.close();
		}

		return game;
	}

	/**
	 * Gets the planet by id.
	 *
	 * @param planetID the long
	 * @return the planet by id
	 */
	public Planet getPlanetByID(long planetID) {

		Cursor cursor = database.query("tb_planet", ALL_PLANET_COLUMNS,
				"planet=" + planetID, null, null, null, null);

		cursor.moveToFirst();

		Planet planet = cursorToPlanet(cursor);

		try {}
        finally
        {
            cursor.close();
        }

		return planet;
	}

	/**
	 * Gets the planets by game id.
	 *
	 * @param gameID the long
	 * @return the planets by game id
	 */
	public List<Planet> getPlanetsByGameID(long gameID) {

		List<Planet> planets = new ArrayList<Planet>();

		Cursor cursor = database.query("tb_planet", ALL_PLANET_COLUMNS, "game="
				+ gameID, null, null, null, null);

		cursor.moveToFirst();

		while (!cursor.isAfterLast()) {
			Planet planet = cursorToPlanet(cursor);

			planets.add(planet);

			cursor.moveToNext();

		}

		try {}
        finally
        {
            cursor.close();
        }
		return planets;
	}

	/**
	 * Cursor to game.
	 *
	 * @param cursor the Cursor
	 * @return the game
	 */
	public Game cursorToGame(Cursor cursor) {

		int gameID = cursor.getInt(0);
		long currentPlanetID = cursor.getInt(1);
		int difficulty = cursor.getInt(2);
		String name = cursor.getString(3);
		int money = cursor.getInt(4);
		int pilotSkillPoints = cursor.getInt(5);
		int fighterSkillPoints = cursor.getInt(6);
		int traderSkillPoints = cursor.getInt(7);
		int engineerSkillPoints = cursor.getInt(8);
		int head = cursor.getInt(9);
		int body = cursor.getInt(10);
		int legs = cursor.getInt(11);
		int feet = cursor.getInt(12);
		int fuselage = cursor.getInt(13);
		int cabin = cursor.getInt(14);
		int boosters = cursor.getInt(15);

		// instantiate all the objects that are nested in the game object

		Ship ship = new Ship();
		Inventory inventory = new Inventory(engineerSkillPoints);
		Player player = new Player();
		Universe universe = new Universe(difficulty, context);
		Planet currentPlanet = getPlanetByID(currentPlanetID);
		Game game = new Game(context);

		// set up ship object

		ship.setFuselage(fuselage);
		ship.setCabin(cabin);
		ship.setBoosters(boosters);

		// set up inventory object

		List<Item> items = getItemsByGameID(gameID);
		Item[] playerInventoryItems = items.toArray(new Item[items.size()]);

		inventory.addAll(playerInventoryItems);

		// set up player object

		player.setName(name);
		player.setMoney(money);

		int[] stats = { pilotSkillPoints, fighterSkillPoints,
				traderSkillPoints, engineerSkillPoints };
		player.setStats(stats);

		player.setHead(head);
		player.setBody(body);
		player.setLegs(legs);
		player.setFeet(feet);

		player.setShip(ship);
		player.setInventory(inventory);

		// set up universe object

		List<Planet> planets = getPlanetsByGameID(gameID);

		universe.setUniverse(planets);

		// set up game object

		game.setID(gameID);
		game.setDifficulty(difficulty);
		game.setPlayer(player);
		game.setPlanet(currentPlanet);
		game.setUniverse(universe);

		return game;
	}

	/**
	 * Cursor to planet.
	 *
	 * @param cursor the Cursor
	 * @return the planet
	 */
	public Planet cursorToPlanet(Cursor cursor) {

		Planet planet = new Planet(cursor.getString(4), cursor.getInt(5),
				cursor.getInt(6), context);

		planet.setTechLevel(cursor.getInt(2));
		planet.setResourceType(cursor.getInt(3));
		planet.setMoney(cursor.getInt(7));
		planet.setBase(cursor.getInt(8));
		planet.setLand(cursor.getInt(9));
		planet.setCloud(cursor.getInt(10));

		return planet;
	}

	/**
	 * Cursor to item.
	 *
	 * @param cursor the Cursor
	 * @return the item
	 */
	private Item cursorToItem(Cursor cursor) {
		int type = cursor.getInt(2);
		String name = cursor.getString(3);
		int drawable = cursor.getInt(4);
		int techLevel = cursor.getInt(5);

		Item item = new Item(type, name, drawable, techLevel);

		return item;

	}

	/**
	 * Gets the items by game id.
	 *
	 * @param gameID the long
	 * @return the items by game id
	 */
	private List<Item> getItemsByGameID(long gameID) {

		List<Item> items = new ArrayList<Item>();

		Cursor cursor = database.query("tb_item", ALL_ITEM_COLUMNS, "game="
				+ gameID, null, null, null, null);

		cursor.moveToFirst();

		while (!cursor.isAfterLast()) {
			items.add((cursorToItem(cursor)));
			cursor.moveToNext();
		}

		try {}
        finally
        {
            cursor.close();
        }

		return items;
	}
}

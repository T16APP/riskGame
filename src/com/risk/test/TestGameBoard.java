package com.risk.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.risk.model.Country;
import com.risk.model.FactoryLand;
import com.risk.model.GameBoard;
import com.risk.model.Map;
import com.risk.model.Player;
import com.risk.utility.LoggingWindow;
import com.risk.utility.MapParser;
import com.risk.utility.TurnPhases;
import com.risk.utility.staticApplicationVariables;

/**
 * The class <code>TestGameBoard</code> contains tests for the class
 * <code> {@link GameBoard}</code>
 * 
 * @author Ranjitha Shetty
 * @version 1.0
 */

public class TestGameBoard {
	GameBoard gameBoard;

	/**
	 * Test case Initialization for TestGameBoard
	 */

	@Before
	public void BeforeTestGameBoard() {
		gameBoard = new GameBoard();
	}

	/**
	 * This testGetGameBoard checks the single instance creation (singleton)
	 */
	@Test
	public void TestGetGameBoard() {

		assertNotNull(gameBoard.GetGameBoard());
	}

	/**
	 * this method verfies the game startup by checking the number of armies
	 * 
	 * @throws Exception
	 *             if the logging window file does not exist
	 * 
	 */
	@Test
	public void TestStartupPhase() throws Exception {
		gameBoard = GameBoard.GetGameBoard();
		gameBoard.LoadMap("Earth.map");
		gameBoard.turnOrganizer.SetCurrentPhase(TurnPhases.Startup);
		;
		gameBoard.BuildDeck(gameBoard.map.GetCountries().size());
		gameBoard.SetupPlayers(3);
		gameBoard.turnOrganizer.players = gameBoard.players;
		gameBoard.AssignCountriesRandom();
		gameBoard.AllocateInitialArmies();
		int armies = gameBoard.players.get(0).GetArmies();
		System.out.println("Current player armies:" + armies);
		assertEquals(35, armies);
	}
	/**this thests the save game
	 * @throws Exception file not exist
	 */
	@Test
	public void TestSaveGame() throws Exception{
		gameBoard = GameBoard.GetGameBoard();
		gameBoard.LoadMap("Earth.map");
		String result=gameBoard.SaveGameToFile("testDB.jason");
		assertTrue(result.equalsIgnoreCase("Successful"));
	}
	/**this tests load the game from a file
	 * @throws FileNotFoundException file not exist
	 * @throws JsonSyntaxException jason issues
	 * @throws JsonIOException jason file not exist
	 * 
	 */
	@Test
	public void TestLoadGame() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
		GameBoard gameBoardL = GameBoard.LoadGameFromFile("testDB.json");
		assertNotEquals(gameBoardL,null);
	}
	/**this tests the map entities after loading
	 * @throws FileNotFoundException file not exist
	 * @throws JsonSyntaxException jason syntax error
	 * @throws JsonIOException jason file not exist
	 * 
	 */
	@Test
	public void TestLoadGameMap() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
		GameBoard gameBoardL = GameBoard.LoadGameFromFile("testDB.json");
		assertNotEquals(gameBoardL.map,null);
	}
	/**this tests the player entities after loading
	 * @throws FileNotFoundException file not exist
	 * @throws JsonSyntaxException jason errors
	 * @throws JsonIOException jason file not exist
	 * 
	 */
	@Test
	public void TestLoadGamePlayer() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
		GameBoard gameBoardL = GameBoard.LoadGameFromFile("testDB.json");
		assertNotEquals(gameBoardL.players,null);
	}
	/**this test the single mode
	 * @throws Exception 
	 * 
	 */
	/**this tests the countries entities after loading
	 * @throws FileNotFoundException file not exist
	 * @throws JsonSyntaxException jason syntax error
	 * @throws JsonIOException json file not exist
	 * 
	 */
	@Test
	public void TestLoadGameCountries() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
		GameBoard gameBoardL = GameBoard.LoadGameFromFile("testDB.json");
		assertNotEquals(gameBoardL.map.GetCountries(),null);
	}
	/**this tests the continent connectivity
	 * @throws Exception file not exist
	 * 
	 */
	@Test
	public void TestValidConnectedMap() throws Exception{
		gameBoard.LoadMap("TestFile/3D Cliff.map");
		assertTrue(gameBoard.map.ValidateContinentsConnectivity());
	}
	/**this tests the continent connectivity
	 * @throws Exception file not exist
	 * 
	 */
	@Test
	public void TestValidConnectedContinent() throws Exception{
		gameBoard.LoadMap("TestFile/World.map");
		assertTrue(gameBoard.map.ValidateContinentsConnectivity());
	}
	
	/**this tests the turnOrganizer entities after loading
	 * @throws FileNotFoundException file not exist
	 * @throws JsonSyntaxException json syntax error
	 * @throws JsonIOException json file not exist
	 * 
	 */
	@Test
	public void TestLoadGameTurnOrganizer() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
		GameBoard gameBoardL = GameBoard.LoadGameFromFile("testDB.json");
		assertNotEquals(gameBoardL.turnOrganizer,null);
	}
	/**this tests the continents entities after loading
	 * @throws FileNotFoundException file not exist
	 * @throws JsonSyntaxException json syntax error
	 * @throws JsonIOException json file not exist
	 * 
	 */
	@Test
	public void TestLoadGameContinents() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
		GameBoard gameBoardL = GameBoard.LoadGameFromFile("testDB.json");
		assertNotEquals(gameBoardL.map.GetContinents(),null);
	}
	/**this test the single mode
	 * @throws Exception file not exist
	 * 
	 */
	@Test
	public void TestSingleMode() throws Exception{
		String mode = gameBoard.GameEntry(0);
		assertTrue(mode.equalsIgnoreCase("SingleMode"));
	}
	/**this test the single mode
	 * @throws Exception file not exist
	 * 
	 */
	@Test
	public void TestTournament() throws Exception{
		String mode = gameBoard.GameEntry(1);
		assertTrue(mode.equalsIgnoreCase("Tournament"));
	}

}